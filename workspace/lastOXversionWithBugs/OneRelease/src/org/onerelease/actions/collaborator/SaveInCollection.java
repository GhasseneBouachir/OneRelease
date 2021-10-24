package org.onerelease.actions.collaborator;

import org.lesstif.jira.issue.*;
import org.lesstif.jira.services.*;
import org.openxava.actions.*;

public class SaveInCollection extends SaveElementInCollectionAction {
	
	User genUser = null;
	
	@Override
	public void execute() throws Exception {
		 User user = new User();
		 user.setActive(true);
		
		 user.setDisplayName(getCollectionElementView().getValueString("firstName")
				 + " " + getCollectionElementView().getValueString("lastName"));
		 user.setEmailAddress(getCollectionElementView().getValueString("emailAddress"));
		 if (getCollectionElementView().getValue("jiraAccountID").toString().isEmpty()) {
		UserService userservice = new UserService();
		genUser = userservice.createUser(user);
		if(genUser != null)
		getCollectionElementView().setValue("jiraAccountID", genUser.getAccountId());
		 
		 }
		 
		 super.execute();
		 
		 
		
	}

}
