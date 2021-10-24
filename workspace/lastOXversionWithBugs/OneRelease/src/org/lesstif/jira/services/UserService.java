package org.lesstif.jira.services;

import java.io.*;

import org.apache.commons.configuration.*;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.annotate.*;
import org.codehaus.jackson.type.*;
import org.lesstif.jira.*;
import org.lesstif.jira.issue.*;
import org.slf4j.*;

import com.sun.jersey.api.client.*;

import lombok.*;

@Data
public class UserService {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	 private JIRAHTTPClient client = null;
	 private User resUser = null;
	 
	 public UserService() throws ConfigurationException {
		 try {
		    	client = new JIRAHTTPClient();
		        }
		 catch(SyncDisabled s)
     	{logger.info(s.getMessage());}
	 }
	 
	 public User createUser(User user) throws IOException {
		 if(client != null) {
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
	        mapper.writeValueAsString("\"notification\":\"true\"");
	        String content = mapper.writeValueAsString(user);
	        content.replace("}",",\"notification\":true}");
	        logger.debug("Content=" + content);
	        client.setResourceName(Constants.JIRA_RESOURCE_User);
	        ClientResponse response = client.post(content);
	        content = response.getEntity(String.class);

	        mapper = new ObjectMapper();
	        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, true);
	        TypeReference<User> ref = new TypeReference<User>() {
	        };
	        
	        resUser = mapper.readValue(content, ref);
		 }
	     return resUser;
	 
	 }

}
