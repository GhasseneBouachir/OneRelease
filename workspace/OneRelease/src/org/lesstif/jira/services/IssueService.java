package org.lesstif.jira.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import lombok.Data;

import org.apache.commons.configuration.ConfigurationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.node.*;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.lesstif.jira.*;
import org.lesstif.jira.issue.Attachment;
import org.lesstif.jira.issue.Issue;
import org.lesstif.jira.issue.IssueSearchResult;
import org.lesstif.jira.issue.IssueType;
import org.lesstif.jira.issue.Priority;
import org.lesstif.jira.issue.WorklogElement;

import com.atlassian.jira.rest.client.api.*;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import java.net.URLEncoder;
import org.apache.commons.lang.StringUtils;

/**
 * @see
 * <a href="https://docs.atlassian.com/software/jira/docs/api/REST/latest/#d2e86">/rest/api/2/project</a>
 *
 * @author lesstif
 *
 */
@Data
public class IssueService {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    private JIRAHTTPClient client = null;
    
    private Issue issue;
    private Issue resIssue;
    public IssueService() throws ConfigurationException {
        try {
    	client = new JIRAHTTPClient();
        }
    	catch(SyncDisabled s)
        	{logger.info(s.getMessage());}
    }

    public Issue getIssue(String issueKey) throws IOException {
        if (client == null) {
            throw new IllegalStateException("HTTP Client not Initailized");
        }

        client.setResourceName(Constants.JIRA_RESOURCE_ISSUE + "/" + issueKey);

        ClientResponse response = client.get();

        String content = response.getEntity(String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        TypeReference<Issue> ref = new TypeReference<Issue>() {
        };
        issue = mapper.readValue(content, ref);

        return issue;
    }

    
    /*** Create Issue ***/
    
    public Issue createIssue(Issue issue) throws IOException {
    	if(client != null)
    	{
        ObjectMapper mapper = new ObjectMapper();
        
        //to ignore a field if its value is null
        mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        String content = mapper.writeValueAsString(issue);
//        content = "{\"fields\":{\"summary\":\"something's wrong\",\"issu"
//        		+ "etype\":{\"name\":\"Task\"},\"project\":{\"key\":\"HEL\"}}}";
        logger.debug("Content=" + content);
        
        client.setResourceName(Constants.JIRA_RESOURCE_ISSUE);
        
        ClientResponse response = client.post(content);
        content = response.getEntity(String.class);

        mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        TypeReference<Issue> ref = new TypeReference<Issue>() {
        };

        resIssue = mapper.readValue(content, ref);

        if (issue.hasAttachments()) {
            issue.setId(resIssue.getId());
            List<Attachment> attachment = postAttachment(issue);
            resIssue.getFields().setAttachment(attachment);
        }
    	}
        return resIssue;
    }
    
    
    /*** Update Issue ***/
    
    
    public Issue updateIssue(String issueKey,Issue issue) throws IOException {
    	if(client != null)
    	{
    	ObjectMapper mapper = new ObjectMapper();

        //to ignore a field if its value is null
        mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        String content = mapper.writeValueAsString(issue);
//        content = "{\"fields\":{\"summary\":\"something's wrong\",\"issu"
//        		+ "etype\":{\"name\":\"Task\"},\"project\":{\"key\":\"HEL\"}}}";
        logger.debug("Content=" + content);

        client.setResourceName(Constants.JIRA_RESOURCE_ISSUE+ "/" + issueKey);
        


        ClientResponse response = client.put(content);
        try {
        content = response.getEntity(String.class);
        }catch(UniformInterfaceException e)
        {e.printStackTrace();}
        mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        TypeReference<Issue> ref = new TypeReference<Issue>() {
        };

        resIssue = mapper.readValue(content, ref);

//        if (issue.hasAttachments()) {
//            issue.setId(resIssue.getId());
//            List<Attachment> attachment = postAttachment(issue);
//            resIssue.getFields().setAttachment(attachment);
//        }
    	}
        return resIssue;
    }

    
    /*** Assign Issue ***/
    
    public boolean assignIssue(String issueKey,String accountID) throws IOException {
    	ClientResponse response;
    	if(client != null)
    	{
    	ObjectMapper mapper = new ObjectMapper();
        JsonNodeFactory jnf = JsonNodeFactory.instance;
        ObjectNode accountId = jnf.objectNode();
        {
        	accountId.put("accountId", accountID);
        }
        //to ignore a field if its value is null
        mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        
        String content = mapper.writeValueAsString(accountId);
//        content = "{\"fields\":{\"summary\":\"something's wrong\",\"issu"
//        		+ "etype\":{\"name\":\"Task\"},\"project\":{\"key\":\"HEL\"}}}";
        logger.debug("Content=" + content);

        client.setResourceName(Constants.JIRA_RESOURCE_ISSUE+ "/" + issueKey+ "/"+ "assignee");
        


        response = client.put(content);
        return response.getClientResponseStatus().equals(ClientResponse.Status.NO_CONTENT);
    	}
    	return false;
        
    
    }
    

    /**
     * Returns a list of all issue types visible to the user
     *
     * @return List list of IssueType
     *
     * @throws IOException json decoding failed
     */
    public List<IssueType> getAllIssueTypes() throws IOException {
    	if(client != null)
    	{
        client.setResourceName(Constants.JIRA_RESOURCE_ISSUETYPE);

        ClientResponse response = client.get();
        String content = response.getEntity(String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        TypeReference<List<IssueType>> ref = new TypeReference<List<IssueType>>() {
        };
        List<IssueType> issueTypes = mapper.readValue(content, ref);
        return issueTypes;
    	}
    	return null;
    }

    public List<Priority> getAllPriorities() throws IOException {
    	if(client != null)
    	{
        client.setResourceName(Constants.JIRA_RESOURCE_PRIORITY);

        ClientResponse response = client.get();
        String content = response.getEntity(String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        TypeReference<List<Priority>> ref = new TypeReference<List<Priority>>() {
        };
        List<Priority> priorities = mapper.readValue(content, ref);
        return priorities;
    	}
    	return null;
    }

    /**
     * Add one or more attachments to an issue.
     *
     * @param issue Issue object
     * @return List
     * @throws JsonParseException json parsing failed
     * @throws JsonMappingException json mapping failed
     * @throws IOException general IO exception
     */
	public List<Attachment> postAttachment(Issue issue) throws JsonParseException, JsonMappingException, IOException {
		if (client != null) {
			List<File> files = issue.getFields().getFileList();

			if (files == null || files.size() == 0) {
				throw new IllegalStateException("Oops! Attachment Not Found.");
			}

			if ((issue.getId() == null || issue.getId().isEmpty())
					&& (issue.getKey() == null || issue.getKey().isEmpty())) {
				throw new IllegalStateException("Oops! Issue id or Key not set.");
			}

			String idOrKey = issue.getId() == null ? issue.getKey() : issue.getId();

			FormDataMultiPart form = new FormDataMultiPart();
			for (int i = 0; i < files.size(); i++) {
				// The name of the multipart/form-data parameter that contains attachments must
				// be "file"
				FileDataBodyPart fdp = new FileDataBodyPart("file", files.get(i));

				form.bodyPart(fdp);
			}

			client.setResourceName(Constants.JIRA_RESOURCE_ISSUE + "/" + idOrKey + "/attachments");

			ClientResponse response = client.postMultiPart(form);
			String content = response.getEntity(String.class);

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, true);

			TypeReference<List<Attachment>> ref = new TypeReference<List<Attachment>>() {
			};
			List<Attachment> res = mapper.readValue(content, ref);
			return res;
		}
		return null;
	}

    /**
     * Add one or more worklog to an issue.
     *
     * @param worklog Issue object
     * @param issue
     * @return List
     * @throws JsonParseException json parsing failed
     * @throws JsonMappingException json mapping failed
     * @throws IOException general IO exception
     */
    public WorklogElement postWorklog(final WorklogElement worklog, final Issue issue) throws JsonParseException, JsonMappingException, IOException {
    	if(client != null)
    	{
        String issueId = issue.getId();
        
        if (StringUtils.isBlank(issueId)) {
            final String key = issue.getKey();
            
            final Issue issueByKey = getIssue(key);
            
            issueId = issueByKey.getId();
            
        }
        
        ObjectMapper mapper = new ObjectMapper();
       
        //to ignore a field if its value is null
        mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        String content = mapper.writeValueAsString(worklog);
        final WorklogElement result = postWorklog(content, issueId);
        return result;
    	}
    	return null;
    }

    public WorklogElement postWorklog(String content, final String issueId) throws UniformInterfaceException, ClientHandlerException, IOException {
    	if(client != null)
    	{
    	ObjectMapper mapper;
        //content = "{\"comment\":\"I did some work here.\",\"started\":\"2016-03-23T04:22:37.471+0000\",\"timeSpentSeconds\":12000}";
        logger.debug("Content=" + content);
        final String resource = Constants.JIRA_RESOURCE_ISSUE + "/" + issueId + "/worklog";
        client.setResourceName(resource);
        ClientResponse response = client.post(content);
        content = response.getEntity(String.class);
        mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        TypeReference<WorklogElement> ref = new TypeReference<WorklogElement>() {
        };
        WorklogElement resIssue = mapper.readValue(content, ref);
        return resIssue;
    	}
    	return null;
    }

    public IssueSearchResult getIssuesFromQuery(final String query) throws IOException {
    	if(client != null)
    	{
    	ObjectMapper mapper = new ObjectMapper();

        mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);

        String content = URLEncoder.encode(query, "UTF-8");

        logger.debug("Content=" + content);

        final String resource = "search?jql=" + content;
        
        logger.debug("resource=" + resource);

        client.setResourceName(resource);

        ClientResponse response = client.get();

        content = response.getEntity(String.class);

        TypeReference<IssueSearchResult> ref = new TypeReference<IssueSearchResult>() {
        };

        IssueSearchResult issues = mapper.readValue(content, ref);
        
        return issues;
    	}
    	return null;
    }

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public JIRAHTTPClient getClient() {
		return client;
	}

	public void setClient(JIRAHTTPClient client) {
		this.client = client;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}
}