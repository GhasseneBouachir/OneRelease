package org.lesstif.test.jira.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.lesstif.jira.services.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.lesstif.jira.*;
import org.lesstif.jira.issue.Attachment;
import org.lesstif.jira.issue.Issue;
import org.lesstif.jira.issue.IssueFields;
import org.lesstif.jira.issue.IssueSearchResult;
import org.lesstif.jira.issue.IssueType;
import org.lesstif.jira.issue.Priority;
import org.lesstif.jira.util.HttpConnectionUtil;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IssueTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

	static String PROJECT_KEY = null;
	static String ISSUE_KEY = null;
	static String REPORTER = null;
	static String ASSIGNEE = null;
	
    @BeforeClass
    public static void beforeClass() {
        HttpConnectionUtil.disableSslVerification();
        
        PROJECT_KEY = System.getProperty("jira.test.project", "HEL");
        ISSUE_KEY = System.getProperty("jira.test.issue", "HEL-10");
        ASSIGNEE = System.getProperty("jira.test.assignee", "gailenepsx");
        REPORTER = System.getProperty("jira.test.reporter", "gailenepsx");
    }

    @Test
    public void aCreateIssue() throws IOException, ConfigurationException {

        Issue issue = new Issue();
        
        IssueFields fields = new IssueFields();
        
        fields.setProjectKey(PROJECT_KEY);
        fields.setSummary("something's wrong");
        fields .setIssueTypeName(IssueType.ISSUE_TYPE_TASK);
        fields .setDescription("Full description for issue");
        fields .setAssigneeName(ASSIGNEE);
        
        // Change Reporter need admin role
        fields.setReporterName(REPORTER);
        fields.setPriorityName(Priority.PRIORITY_CRITICAL);
        fields.setLabels(new String[]{"bugfix","blitz_test"})  ;       
//        fields.setComponentsByStringArray(new String[]{"Component-1", "Component-2"});                    
//        fields.addAttachment("README.md");
//        fields.addAttachment("bug-description.pdf");
//        fields.addAttachment("screen_capture.png");
        
        issue.setFields(fields);
        
        logger.info(issue.toPrettyJsonString());
        
        IssueService issueService = new IssueService();
        
        Issue genIssue = issueService.createIssue(issue);       
        
        ISSUE_KEY = genIssue.getKey();
        
        //Print Generated issue
        logger.info(genIssue.toPrettyJsonString());
    }
    
    @Test
    public void bgetIssue() throws IOException, ConfigurationException {

        IssueService issueService = new IssueService();
        Issue issue =  issueService.getIssue(ISSUE_KEY);

        logger.debug(issue.toPrettyJsonString());

        // attachment info
        List<Attachment> attachs = issue.getFields().getAttachment();
        for ( Attachment a : attachs) {
            logger.debug("Attachment:" + a.toPrettyJsonString());           
        }
        
        IssueFields fields = issue.getFields();
        
        // Project key
        logger.debug("Project Key:" + fields.getProject().getKey());
                
        //issue type
        logger.debug("IssueType:" + fields.getIssuetype().toPrettyJsonString());
        
        // issue description
        logger.debug("Issue Description:" + fields.getDescription());       
    }
        
    @Test
    public void cUploadAttachments() throws IOException, ConfigurationException {
        Issue issue = new Issue();
        
        issue.setKey(ISSUE_KEY);
                
        issue.addAttachment(new File("attachment.png"));
//        issue.addAttachment("test.pdf");
        
        IssueService issueService = new IssueService();
        issueService.postAttachment(issue);
    }
    
    @Test
    public void dgetAllIssueType() throws IOException, ConfigurationException {

        IssueService issueService = new IssueService();
        List<IssueType> issueTypes =  issueService.getAllIssueTypes();

        for(IssueType i : issueTypes) {
            logger.info(i.toPrettyJsonString());
        }
    }
    
    @Test
    public void egetAllPriorities() throws IOException, ConfigurationException {

        IssueService issueService = new IssueService();
        List<Priority> priority =  issueService.getAllPriorities();

        for(Priority p : priority) {
            logger.info(p.toPrettyJsonString());
        }
    }
    
    @Test
    public void fGetCustomeFields() throws IOException, ConfigurationException {
        IssueService issueService = new IssueService();
        Issue issue =  issueService.getIssue(ISSUE_KEY);

        Map<String, Object> fields = issue.getFields().getCustomfield();
        
        logger.info("JSON : " + JsonPrettyString.mapToPrettyJsonString(fields));
        for( String key : fields.keySet() ){
            logger.info("Field Name: " + key + ",value:" + fields.get(key));
        }
    }

    @Test
    public void getIssuesFromQuery() throws IOException, ConfigurationException {
        IssueService issueService = new IssueService();
        final String query = "status=\"To Do\" and assignee = currentUser()";
        
        final IssueSearchResult issues = issueService.getIssuesFromQuery(query);

        assertTrue("Should be at least 1 open issue", !issues.getIssues().isEmpty());

    }

}
