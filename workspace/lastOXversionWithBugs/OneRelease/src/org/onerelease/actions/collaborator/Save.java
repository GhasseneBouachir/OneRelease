package org.onerelease.actions.collaborator;

import org.lesstif.jira.issue.*;
import org.lesstif.jira.services.*;
import org.openxava.actions.*;

public class Save extends SaveAction {
	
User genUser = null;
	
	@Override
	public void execute() throws Exception {
		 User user = new User();
		 user.setActive(true);
		
		 user.setDisplayName(getView().getValueString("firstName")
				 + " " + getView().getValueString("lastName"));
		 user.setEmailAddress(getView().getValueString("emailAddress"));
		 if (getView().getValue("jiraAccountID").toString().isEmpty()) {
		UserService userservice = new UserService();
		genUser = userservice.createUser(user);
		if(genUser != null)
			getView().setValue("jiraAccountID", genUser.getAccountId());
		 
		 }
		 
		 super.execute();
		 
		 
		
	}

}
