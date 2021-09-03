package org.onerelease.model;

import java.util.*;

import javax.persistence.*;
import javax.ws.rs.*;

import org.onerelease.actions.*;
import org.onerelease.actions.sprint.*;
import org.onerelease.enumeration.*;
import org.openxava.annotations.*;
import org.openxava.jpa.*;
import org.openxava.model.*;

import com.sun.xml.internal.ws.api.*;

@Views({@View(members="reference, name, status; startDate , endDate, squad;"
		+ "scrumMaster  ; project;TicketsbySprint {ticketsbySprint} "),
	@View(name="SprintUnderTicketBySprint", 
	members="Details[# reference, name, status, startDate, endDate, squad]"),
	@View(name="SprintUnderProject", 
	members="Details[# reference, name, status, startDate, endDate, squad]; "
			+ "scrumMaster ;TicketsbySprint {ticketsbySprint} ")})
@Entity


public class Sprint extends Identifiable{


	
	@Hidden
	@Column
	private int month;
	
	@Hidden
	@Column
	private int rank;
	
	@Column(length = 10)//unique=true,
    @ReadOnly
    private String reference;
	
//	@Action	("SprintManager.startSprint")
	@ReadOnly
	@Column(length = 30)
	private String name;
	
	
	@ManyToOne
	private Collaborator scrumMaster;	
	
	@DescriptionsList(showReferenceView=true)
	@ReferenceView("projectUnderSprint")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Project project;
	
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    //@ReferenceView(value="squadUnderSprint")
    @DescriptionsList(descriptionProperties="name")
    private Squad squad;
    
    @Column
    @Required
    @DefaultValue("planned")
    @OnChange(OnChangeSprintStatusAction.class)
    private SprintStatus status;
    
    @Column
    private Date startDate;
    
    @Column
    private Date endDate;
	

	@ListProperties("ticket.reference, milestone, status, ticket.summary")
	@OneToMany(mappedBy="sprint",cascade=CascadeType.ALL, fetch=FetchType.LAZY) 
	@AsEmbedded
    private Collection<TicketBySprint> ticketsbySprint;
	
	
	
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<TicketBySprint> getTicketsbySprint() {
		return ticketsbySprint;
	}

	public void setTicketsbySprint(Collection<TicketBySprint> ticketsbySprint) {
		this.ticketsbySprint = ticketsbySprint;
	}


	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Collaborator getScrumMaster() {
		return scrumMaster;
	}

	public void setScrumMaster(Collaborator scrumMaster) {
		this.scrumMaster = scrumMaster;
	}

	public SprintStatus getStatus() {
		return status;
	}

	public void setStatus(SprintStatus status) {
			this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Squad getSquad() {
		return squad;
	}

	public void setSquad(Squad squad) {
		this.squad = squad;
	}


	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}




	
		
}
