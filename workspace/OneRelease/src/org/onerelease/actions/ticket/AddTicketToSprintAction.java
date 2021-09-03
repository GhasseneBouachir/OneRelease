package org.onerelease.actions.ticket;

import java.util.*;

import javax.ejb.*;
import javax.persistence.*;

import org.lesstif.jira.services.*;
import org.onerelease.actions.*;
import org.onerelease.enumeration.*;
import org.onerelease.model.*;
import org.openxava.actions.*;
import org.openxava.jpa.*;
import org.openxava.model.*;
import org.openxava.util.*;

public class AddTicketToSprintAction extends CollectionBaseAction{
	
	

	@Override
	public void execute() throws Exception {
		Map sprint =  (Map) getView().getValue("targetSprint");
		Map collaborator = (Map) getView().getValue("collaborator");
		if(sprint.get("id").equals("")) 
			{
			addWarning("You have to specify the Sprint ", 2);
			}
		else if(collaborator.get("id").equals("")){
			addWarning("You have to specify the collaborator ", 3);
		}
		else {
		Map sprintId =  new HashMap();
		Map collaboratorId =  new HashMap();
		Map ticketId =  new HashMap();
		sprintId.put("id", sprint.get("id"));
		collaboratorId.put("id", collaborator.get("id"));
		String milestone =  getView().getValueString("milestone");
		Map ticketBySprint = new HashMap();
		ticketId.put("id", AssignAction.getTicketId());
		ticketBySprint.put("ticket", ticketId);
		ticketBySprint.put("sprint", sprintId);
		ticketBySprint.put("milestone", milestone);
		ticketBySprint.put("collaborator", collaboratorId);
		if(existTicketBySprint(ticketId,sprintId,milestone)) {
		addWarning("Ticket exists with the same milestone in this sprint", 33);
		}
		else if(existTicketBySprintFinished(ticketId,milestone))
			{
			addWarning("Ticket with this milestone is already terminated", 34);
			}
		else {
			//AssignInJira
			IssueService issueService = new IssueService();
			issueService.assignIssue(getTicket((String) ticketId.get("id")).getReference(),
					getCollaborator((String) collaboratorId.get("id")).getJiraAccountID() );
			MapFacade.create("TicketBySprint", ticketBySprint);
			if(sprintActivated((String) sprintId.get("id")))
			changeTicketStatus((String) ticketId.get("id"),TicketStatus.InProgress);
			else {
				changeTicketStatus((String) ticketId.get("id"),TicketStatus.Opened);
			}
			closeDialog();
		}
		}
	}




	private boolean sprintActivated(String id) {
		try {
		Query query = XPersistence.getManager().createQuery(
				 "from Sprint as s where s.status =:status "
				 + "and s.id=:id");
				 query.setParameter("id", id);
				 query.setParameter("status", SprintStatus.activated);
				 query.getSingleResult();
				 return true;
		}
		catch(NoResultException e) {
			return false;
		}
	}


	private void changeTicketStatus(String id,TicketStatus ticketStatus) {
		Query query = XPersistence.getManager().createQuery(
				"from Ticket as t where t.id = :id ");
				 query.setParameter("id", id);
				 Ticket ticket = (Ticket) query.getSingleResult();
				 ticket.setStatus(ticketStatus);
				 //you can change state of ticket here
				 XPersistence.getManager().persist(ticket);
	}


	private boolean existTicketBySprintFinished(Map ticketId, String milestone) {
		try {
			 Query query = XPersistence.getManager().createQuery(
					 "from TicketBySprint as t where t.status = :status "
					 + "and t.ticket.id=:ticket_id and t.milestone=:milestone");
					 query.setParameter("milestone", milestone);
					 query.setParameter("ticket_id", ticketId.get("id"));
					 query.setParameter("status", TicketBySprintStatus.finished);
					 query.getSingleResult();
					 return true;
			}
			catch(NoResultException e) {
				return false;
			}	
	}


	private boolean existTicketBySprint(Map ticketId, Map sprintId, String milestone) {
			try {
			 Query query = XPersistence.getManager().createQuery(
					 "from TicketBySprint as t where t.sprint.id = :sprint_id "
					 + "and t.ticket.id=:ticket_id and t.milestone=:milestone");
					 query.setParameter("milestone", milestone);
					 query.setParameter("ticket_id", ticketId.get("id"));
					 query.setParameter("sprint_id", sprintId.get("id"));
					 query.getSingleResult();
					 return true;
			}
			catch(NoResultException e) {
				return false;
			}	
	}
	
	
	private Ticket getTicket(String id) {
		Query query = XPersistence.getManager().createQuery(
				 "from Ticket as t where t.id =:id ");
				 query.setParameter("id", id);
				 Ticket ticket = (Ticket) query.getSingleResult();
				 return ticket;
	}
	
	
	private Collaborator getCollaborator(String id) {
		Query query = XPersistence.getManager().createQuery(
				 "from Collaborator as c where c.id =:id ");
				 query.setParameter("id", id);
				 Collaborator collaborator = (Collaborator) query.getSingleResult();
				 return collaborator;
	}
}
