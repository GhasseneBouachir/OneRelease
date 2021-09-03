package org.onerelease.actions.ticket;

import java.util.*;

import org.lesstif.jira.issue.*;
import org.lesstif.jira.services.*;
import org.openxava.actions.*;

public class SaveUserStory extends SaveElementInCollectionAction{

	@Override
	public void execute() throws Exception {
		Issue genIssue = null;
		IssueService issueService = new IssueService();
		Issue issue;
		if (getCollectionElementView().getValue("reference").toString().isEmpty()) {
			issue = new Issue();
			IssueFields fields = new IssueFields();
			fields.setProjectKey("HEL");
			fields.setSummary("created from one release");
			fields.setIssueTypeName(IssueType.ISSUE_TYPE_Story);
			fields.setCustomfield("customfield_10014", getParentView().getValueString("reference"));
			issue.setFields(fields);
			genIssue = issueService.createIssue(issue);
			if(genIssue != null)
			getCollectionElementView().setValue("reference", genIssue.getKey());

		} else {
			issue = new Issue();
			IssueFields fields = new IssueFields();
			fields.setProjectKey("HEL");
			fields.setSummary("updated from one release");
			fields.setIssueTypeName(IssueType.ISSUE_TYPE_Story);
			issue.setFields(fields);
			genIssue = issueService.updateIssue(getCollectionElementView().getValueString("reference").toString(),
					issue);
		}
			
			
		super.execute();
	}
	
	
	
}
