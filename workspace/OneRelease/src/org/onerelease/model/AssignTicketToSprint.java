package org.onerelease.model;

import javax.persistence.*;

import org.onerelease.enumeration.*;
import org.openxava.annotations.*;

//@View(members="targetSprint,milestone")

public class AssignTicketToSprint {
	
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@DescriptionsList(descriptionProperties="name",condition="${status} != 2")
	private Sprint targetSprint;
	
	
	@Required
	private Milestone milestone;
	
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@DescriptionsList(
			depends="this.targetSprint",
			descriptionProperties="lastName,firstName",

	condition="${squad} = (select squad from Sprint s where s.id = ?)")
//			+ "(select id " + 
//			"	FROM Squad s" + 
//			"	where s.name = (REGEXP_REPLACE(?,'(^([^\\.]+)\\.)|( [0-9]+\\..*$)','','g')))")
	private Collaborator collaborator;
	
	
	public Sprint getTargetSprint() {
		return targetSprint;
	}

	public void setTargetSprint(Sprint targetSprint) {
		this.targetSprint = targetSprint;
	}

	public Milestone getMilestone() {
		return milestone;
	}

	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}

	public Collaborator getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(Collaborator collaborator) {
		this.collaborator = collaborator;
	}

}
