package org.onerelease.actions.sprint;

import java.util.*;

import javax.persistence.*;

import org.onerelease.enumeration.*;
import org.onerelease.model.*;
import org.openxava.actions.*;
import org.openxava.jpa.*;
import org.openxava.model.*;

import lombok.*;

public class StartSprint extends ViewBaseAction {

	@Override
	public void execute() throws Exception {
		boolean allTicketsBySprintFinished = true;
	String SprintId = (String) getView().getKeyValues().get("id");
	Query query = XPersistence.getManager().createQuery(
			 "from Sprint as s where s.id = :id ");
			 query.setParameter("id", SprintId);
			 Sprint s = (Sprint) query.getSingleResult();
			 s.setStatus(SprintStatus.activated);
			 XPersistence.getManager().persist(s);
			 Collection<TicketBySprint> ticketsBySprint = s.getTicketsbySprint();
			 for(TicketBySprint ticketBysSprint : ticketsBySprint) 
			 	{
				 ticketBysSprint.setStatus(TicketBySprintStatus.started);
				 XPersistence.getManager().persist(ticketBysSprint);
			 	}
			 getView().refresh();
			 removeActions("Sprint.startSprint");
			 addActions("Sprint.completeSprint");
			 
	}




}
