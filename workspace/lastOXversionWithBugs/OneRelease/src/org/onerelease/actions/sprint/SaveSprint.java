package org.onerelease.actions.sprint;

import java.util.*;

import javax.persistence.*;

import org.lesstif.jira.issue.*;
import org.lesstif.jira.services.*;
import org.lesstif.jira.sprint.*;
import org.onerelease.model.*;
import org.openxava.actions.*;
import org.openxava.jpa.*;

public class SaveSprint extends SaveElementInCollectionAction {
	@Override
	public void execute() throws Exception {
		int maxSprint = 0;
		JiraSprint genSprint = null;
		boolean creationSprint = false;
		String sprintName = null;
		
//		if(getCollectionElementView().getModelName().equals("Project.Sprint")){
			creationSprint = true;
			JiraSprint sprint = new JiraSprint();
			if (getCollectionElementView().getValue("name").toString().isEmpty()) {
			Query query = XPersistence.getManager()
					.createQuery("select max(rank) from Sprint s " + " where s.month = :month ");
			query.setParameter("month", new Date().getMonth() + 1);
			maxSprint = query.getSingleResult() == null ? 0 : (int) query.getSingleResult();
			sprint.setOriginBoardId("1");
			Map squad = (Map) getCollectionElementView().getValue("squad");
			if (squad.get("id").equals("")) {
				addWarning("You have to specify the squad ", 5);
				return;
			}
			sprintName = getParentView().getValue("name") + "." + getSquadName((String) squad.get("id")) + " "
					+ (new Date().getYear() - 100) + "." + ((int) new Date().getMonth() + 1) + "." + "S"
					+ (maxSprint + 1);
			sprint.setName(sprintName);
			// sprint.setStartDate("");
			// sprint.setEndDate("");

			SprintService sprintService = new SprintService();
			genSprint = sprintService.createSprint(sprint);
			getCollectionElementView().setValue("name", sprintName);
			}
//		}
		super.execute();
	     
	     
	     
	     
	     
	     
	     
	     if(creationSprint && genSprint != null) {
	    	 Query query = XPersistence.getManager().createQuery(
	 				"from Sprint as s where s.name = :name ");
	 				 query.setParameter("name", sprintName);
	 				 Sprint savedSprint = (Sprint) query.getSingleResult();
	 				 savedSprint.setMonth(new Date().getMonth()+1);
	 				 savedSprint.setRank(maxSprint + 1);
	 				 XPersistence.getManager().persist(savedSprint);
	    	 
	     }
		}
		
		
		

		private String getSquadName(String id) {
			Query query = XPersistence.getManager().createQuery(
	 				"from Squad as s where s.id = :id ");
	 				 query.setParameter("id", id);
	 				 Squad squad = (Squad) query.getSingleResult();
			return squad.getName();
		}
	}