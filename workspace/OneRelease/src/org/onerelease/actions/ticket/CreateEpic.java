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
		super.validateViewValues();
		IssueService issueService = new IssueService();
		Issue genIssue;
		Issue issue = new Issue();
		boolean creationEpic = false;
		IssueFields fields = new IssueFields();
		
		fields.setProjectKey("HEL");
		fields.setSummary(getCollectionElementView().getValueString("summary"));
		fields.setIssueTypeName(IssueType.ISSUE_TYPE_EPIC);
//		 fields.setCustomfield("customfield_10011", getCollectionElementView().getValueString("name"));
		if(getCollectionElementView().getValue("reference") == null)
    		{
    		creationEpic = true;
			fields.setDescription("Epic created from one release");
           
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
            
            fields.setDescription("Epic updated from one release");
            issue.setFields(fields);
            genIssue = issueService.
            		updateIssue(getCollectionElementView().getValueString("reference").toString(), issue);
    	}
		super.execute();
		
		
		
		
//		if(creationEpic ) {
//			Query query = XPersistence.getManager().createQuery(
//					"from Ticket as t where t.reference = :reference ");
//					 query.setParameter("reference", genIssue.getKey());
//					 Ticket ticket = (Ticket) query.getSingleResult();
//					 ticket.setJiraId(genIssue.getId());
//					 XPersistence.getManager().persist(ticket);
//		}
//		XPersistence.commit();
		getCollectionElementView().refresh();
		getView().refresh();
	}

}
