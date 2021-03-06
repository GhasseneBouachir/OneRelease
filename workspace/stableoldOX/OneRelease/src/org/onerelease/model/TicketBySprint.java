package org.onerelease.model;

import javax.persistence.*;
import javax.ws.rs.*;

import org.onerelease.enumeration.*;
import org.openxava.annotations.*;
import org.openxava.model.*;


@Entity
@Views({@View(name="TicketBySprintUnderTicket", members="milestone, status, sprint, collaborator" )})

@Tab(properties="milestone")

public class TicketBySprint extends Identifiable {
	
	@Column(length = 20)
	private String milestone;
	
	@DefaultValue(value = "initiated")
	@Column
	private TicketBySprintStatus status; 
	
	@ReferenceView("SprintUnderTicketBySprint")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sprint sprint;
	
//	@ReferenceView("CollaboratorUnderTicketBySprint")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@ReferenceView(value="CollaboratorUnderTicketBySprint")
	private Collaborator collaborator;	
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@AsEmbedded
    private Ticket ticket;
	

	public String getMilestone() {
		return milestone;
	}

	
	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}



	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}




	public Collaborator getCollaborator() {
		return collaborator;
	}


	public void setCollaborator(Collaborator collaborator) {
		this.collaborator = collaborator;
	}


	public TicketBySprintStatus getStatus() {
		return status;
	}


	public void setStatus(TicketBySprintStatus status) {
		this.status = status;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}




}
