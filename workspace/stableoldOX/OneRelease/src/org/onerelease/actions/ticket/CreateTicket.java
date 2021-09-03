package org.onerelease.actions.ticket;

import com.atlassian.jira.rest.client.*;
import com.atlassian.jira.rest.client.api.domain.input.*;

import org.openxava.actions.*;

import java.util.*;

import org.lesstif.jira.issue.*;
import org.lesstif.jira.services.*;

public class CreateTicket  extends SaveAction {

	@Override
	public void execute() throws Exception {
		IssueService issueService = new IssueService();
		Issue genIssue;
		Issue issue;
    	if(getView().getValue("reference").toString().isEmpty())
    		{
    		issue = new Issue();
            IssueFields fields = new IssueFields();
            fields.setProjectKey("HEL");
            fields.setSummary("crated from one release");
            fields .setIssueTypeName(IssueType.ISSUE_TYPE_TASK);
//            fields .setDescription("Full description for issue");
//            fields .setAssigneeName("gailenepsx");
//            
//            // Change Reporter need admin role
//            fields.setReporterName("gailenepsx");
//            fields.setPriorityName(Priority.PRIORITY_CRITICAL);
//            fields.setLabels(new String[]{"bugfix","blitz_test"})  ;  
//            fields.setDescription("");
//            fields.setComponentsByStringArray(new String[]{"Component-1", "Component-2"});                    
//            fields.addAttachment("README.md");
//            fields.addAttachment("bug-description.pdf");
//            fields.addAttachment("screen_capture.png");
            issue.setFields(fields);
//            logger.info(issue.toPrettyJsonString());
//            
            
            
            genIssue = issueService.createIssue(issue);       
            if(genIssue != null)
            getView().setValue("reference", genIssue.getKey());
    		}
    	else {
    		issue = new Issue();
            IssueFields fields = new IssueFields();
            fields.setProjectKey("HEL");
            fields.setSummary("updated from one release");
            fields .setIssueTypeName(IssueType.ISSUE_TYPE_TASK);
            issue.setFields(fields);
            genIssue = issueService.
            		updateIssue(getView().getValueString("reference").toString(), issue);
    	}
		
         super.execute();
 		
	}
	
}
