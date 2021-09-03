package org.onerelease.actions;

import java.util.*;

import javax.persistence.*;

import org.apache.commons.collections.map.*;
import org.lesstif.jira.issue.*;
import org.lesstif.jira.services.*;
import org.onerelease.model.*;
import org.openxava.actions.*;
import org.openxava.jpa.*;
import org.openxava.model.meta.*;
import org.lesstif.jira.sprint.JiraSprint;

public class SaveElementInCollection extends SaveElementInCollectionAction {
	
	@Override
	public void execute() throws Exception {
		int maxSprint = 0;
		JiraSprint genSprint = null;
		User genUser = null;
		boolean creationSprint = false;
		boolean creationUser = false;
		
		
		
	
	
	
		/*** Create Sprint ****/
	
	if(getCollectionElementView().getModelName().equals("Project.Sprint")){
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
			String name = getParentView().getValue("nom") + "." + getSquadName((String) squad.get("id")) + " "
					+ (new Date().getYear() - 100) + "." + ((int) new Date().getMonth() + 1) + "." + "S"
					+ (maxSprint + 1);
			sprint.setName(name);
			// sprint.setStartDate("");
			// sprint.setEndDate("");

			SprintService sprintService = new SprintService();
			genSprint = sprintService.createSprint(sprint);
			getCollectionElementView().setValue("name", name);
			}
		}
	
	
	/*** create User/collaborator ***/
	if (getCollectionElementView().getModelName().equals("Collaborator"))
			{creationUser = true;
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
	
			}
	
	
	
	
	/*** Execute Save Action ***/
     super.execute();
     
     
     
     
     
     
     
     if(creationSprint && genSprint!=null) {
    	 Query query = XPersistence.getManager().createQuery(
 				"from Sprint as s where s.name = :name ");
 				 query.setParameter("name", genSprint.getName());
 				 Sprint sprint = (Sprint) query.getSingleResult();
 				 sprint.setMonth(new Date().getMonth()+1);
 				 sprint.setRank(maxSprint + 1);
 				 XPersistence.getManager().persist(sprint);
    	 
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

