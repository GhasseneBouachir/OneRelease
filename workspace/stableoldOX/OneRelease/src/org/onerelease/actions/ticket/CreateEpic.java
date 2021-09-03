package org.onerelease.actions.ticket;

import java.math.*;

import javax.persistence.*;

import org.lesstif.jira.issue.*;
import org.lesstif.jira.services.*;
import org.onerelease.model.*;
import org.openxava.actions.*;
import org.openxava.jpa.*;

public class CreateEpic extends SaveElementInCollectionAction {
	
	@Override
	public void execute() throws Exception {
		IssueService issueService = new IssueService();
		Issue genIssue;
		Issue issue;
		boolean creationEpic = false;
		
    	if(getCollectionElementView().getValue("reference") == null)
    		{
    		creationEpic = true;
			issue = new Issue();
			IssueFields fields = new IssueFields();
			fields.setProjectKey("HEL");
			fields.setSummary(getCollectionElementView().getValueString("summary"));
			fields.setDescription("created from one release");
			fields .setIssueTypeName(IssueType.ISSUE_TYPE_EPIC);
            fields.setCustomfield("customfield_10011", getCollectionElementView().getValueString("name"));
            issue.setFields(fields);
			genIssue = issueService.createIssue(issue);
			if(genIssue != null)
			getCollectionElementView().setValue("reference", genIssue.getKey());
			// RANK
			getCollectionElementView().setValue("rank", 
					BigInteger.valueOf(getCollectionElementView().getCollectionSize())
					);
			
    		}
    	else {
    		issue = new Issue();
            IssueFields fields = new IssueFields();
            fields.setProjectKey("HEL");
            fields.setSummary("Epic updated from one release");
            fields .setIssueTypeName(IssueType.ISSUE_TYPE_EPIC);
            issue.setFields(fields);
            genIssue = issueService.
            		updateIssue(getView().getValueString("reference").toString(), issue);
    	}
		super.execute();
		
		if(creationEpic && genIssue != null) {
			Query query = XPersistence.getManager().createQuery(
					"from Ticket as t where t.reference = :reference ");
					 query.setParameter("reference", genIssue.getKey());
					 Ticket ticket = (Ticket) query.getSingleResult();
//					 ticket.setJiraId(genIssue.getId());
					 XPersistence.getManager().persist(ticket);
		}
	}

}
