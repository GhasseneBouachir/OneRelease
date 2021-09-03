package org.lesstif.jira.services;

import java.io.*;
import java.util.*;

import org.apache.commons.configuration.*;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.annotate.*;
import org.codehaus.jackson.type.*;
import org.lesstif.jira.*;
import org.lesstif.jira.sprint.*;
import org.slf4j.*;

import com.sun.jersey.api.client.*;

import lombok.*;

@Data
public class SprintService {
	
	
	 private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	 private JIRAHTTPClient client = null;
	 private JiraSprint sprint;
	 private JiraSprint resSprint = null;
	 
	 public SprintService() throws ConfigurationException {
	        try {
	        	client = new JIRAHTTPClient();
	            }
	        catch(SyncDisabled s)
        	{logger.info(s.getMessage());}
	    }
	 
	 public JiraSprint createSprint(JiraSprint sprint) throws IOException {
		 if(client != null)
		 {
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
	        String content = mapper.writeValueAsString(sprint);
	        logger.debug("Content=" + content);
	        client.setResourceNameAgileApi(Constants.JIRA_RESOURCE_Sprint);
	        ClientResponse response = client.post(content);
	        content = response.getEntity(String.class);

	        mapper = new ObjectMapper();
	        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, true);
	        TypeReference<JiraSprint> ref = new TypeReference<JiraSprint>() {
	        };
	        
	        resSprint = mapper.readValue(content, ref);
		 }
         return resSprint;
	 
	 
	 }

}
