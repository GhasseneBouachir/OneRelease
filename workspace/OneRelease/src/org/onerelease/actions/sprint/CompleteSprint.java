package org.onerelease.actions.sprint;

import java.util.*;

import javax.persistence.*;

import org.onerelease.enumeration.*;
import org.onerelease.model.*;
import org.openxava.actions.*;
import org.openxava.jpa.*;

public class CompleteSprint  extends ViewBaseAction   {

	@Override
	public void execute() throws Exception {
		boolean allTicketsBySprintFinished = true;
		String SprintId = (String) getView().getKeyValues().get("id");
		Query query = XPersistence.getManager().createQuery(
				 "from Sprint as s where s.id = :id ");
				 query.setParameter("id", SprintId);
				 Sprint s = (Sprint) query.getSingleResult();
				 Collection<TicketBySprint> ticketsBySprint = s.getTicketsbySprint();
		for(TicketBySprint ticketBysSprint : ticketsBySprint) 
	 	{
		 if(ticketBysSprint.getStatus() != TicketBySprintStatus.finished)
		 	{allTicketsBySprintFinished = false;
			 break;
		 	}
	 	}
	 if(allTicketsBySprintFinished)
	 	{
		 s.setStatus(SprintStatus.terminated);
		 XPersistence.getManager().persist(s);
		 getView().refresh();
		 removeActions("Sprint.completeSprint");
	 	}
	 else {
		 addWarning("there is already ticketsBySprint with not finished status", 33);
	 }
		
	}

	



	

}
