package org.onerelease.model;

import java.math.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.model.*;


import org.onerelease.actions.ticket.*;
import org.onerelease.enumeration.*;

@Entity

@Views({
		@View(members = "Details [# reference, summary; type, priority, status; creationDate];"
		+" Functionality [ functionality ] "
		+ "TicketsbySprint {ticketsbySprint}   " 
		+ " Description { description } functionalityanalysis { functionalityAnalysis } "
		+ "PeerReview { peerReview } UserStories{userStories} "
		),
		@View(name="Epic", members="name, reference, summary, creationDate, rank ; userStories; "
				+ "  Description [ description ]; "
//				+ "UserStories{userStories} "
				),
		@View(name = "UserStory", members = "Details [# reference, summary; type, priority, status; creationDate];"
				+ "TicketsbySprint {ticketsbySprint} Functionality { functionality }  " + ""
				+ " Description { description } FunctionalityAnalysis { functionalityAnalysis } "
				+ "PeerReview { peerReview }"
		),
		@View(name = "TicketUnderSprint", members = "Details [reference, summary, state; type, priority, status; creationDate]"),
		@View(name="TicketUnderFunctionalityAnalysis", extendsView="TicketUnderSprint")})
//@Tab(properties = "reference,status,summary, state, type, priority,  creationDate", defaultOrder = "${rank} asc")

public class Ticket extends Identifiable {


	//	 @PrePersist
//	    private void prePersist() {
//		 		
//	    	 setDescription(getDescription() + " CREATED");
//	    	}
	@PreDelete
	public void onPreDelete() {
		if (getSubject() == null) {
			for (Ticket userStory : this.getUserStories()) {
				userStory.setSubject(null);
			}
		}
	}

	@PreCreate
	public void onPreCreate() {
		if (getProject() == null) {
			if (getSubject() != null && getSubject().getProject() != null) {
				setProject(getSubject().getProject());
			}
		}
		if(getName()==null)
			{	if(functionalityAnalysis != null)
				functionalityAnalysis.setTicket(this);
				if(peerReview != null)
				peerReview.setTicket(this);
				if(this.functionality!=null)
				functionalityAnalysis.setFunctionality(this.functionality);
			}
	}
	
	@PreUpdate
	public void onPreUpdate() {
		if(getName()==null)
		{
			if(functionalityAnalysis != null)
			{
			functionalityAnalysis.setTicket(this);
			functionalityAnalysis.setFunctionality(this.functionality);
			}
			if(peerReview != null)
				peerReview.setTicket(this);
		}	
	}
	
	
	@Column(length = 20)
	private String name;

	// @Hidden
	@Column
	@ReadOnly
	private BigInteger rank;
	
	@Column(length = 10)
    @ReadOnly
    private String reference;
	

	@Hidden
	@ReadOnly
	@Column(length = 10, unique = true)
	private String jiraId;
	
    

	@Column(length = 80)
	@Required(message = "you have to specify the Summary")
	private String summary;

	@Column(length = 20)
	private TicketState state;
	
	@Column
	@Stereotype("SIMPLE_HTML_TEXT")
	private String description;

	@Column
	private TicketType type;

	@Column
	private TicketPriority priority;

	@Column
	private TicketStatus status;

	@Column
	private boolean subjectAcknowledged;

	@Column
	private boolean ackMeeting;

	@Column
	@Stereotype("MEMO")
	private String ackRemark;

	@Required
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date creationDate;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private Ticket subject;
	
	
	@CollectionView("UserStory")
	@RowAction("addTicketToSprint.assign")
	@ListProperties("reference, summary, type, priority, status")
	@SaveAction("Ticket.saveUserStory")
	@OneToMany(mappedBy="subject")
	@NewAction("Ticket.createUserStoryFromProject")
	@EditAction("Ticket.editUserStory")
	private Collection<Ticket> userStories;
	

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@ReferenceView("ReleaseUnderTicket")
	private Release release;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private Project project;

	@OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
	@CollectionView(value = "TicketBySprintUnderTicket")
	@ListProperties("milestone, status, sprint.name, collaborator.lastName, collaborator.firstName")
	@AsEmbedded
	private Collection<TicketBySprint> ticketsbySprint;

	@OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
	@CollectionView(value = "EstimationUnderTicket")
	private Collection<Estimation> estimations;
	

	
	
	
	
	@ReferenceView("functinalityUnderUserStory")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@OnChange(ChangeFunctionality.class)
	private Functionality functionality;
	
	@OneToOne(mappedBy="ticket",optional=true,cascade=CascadeType.ALL)
	@AsEmbedded
	@ReferenceView("FunctionnalityAnalysisUnderUserStory")
	private FunctionalityAnalysis functionalityAnalysis;
	
	@OneToOne(mappedBy="ticket",fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	@AsEmbedded
	@ReferenceView("PeerReview")
	private PeerReview peerReview;
	
	
	

	
	
	
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TicketType getType() {
		return type;
	}

	public void setType(TicketType type) {
		this.type = type;
	}

	public TicketPriority getPriority() {
		return priority;
	}

	public void setPriority(TicketPriority priority) {
		this.priority = priority;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Release getRelease() {
		return release;
	}

	public void setRelease(Release release) {
		this.release = release;
	}

	public Collection<Estimation> getEstimations() {
		return estimations;
	}

	public void setEstimations(Collection<Estimation> estimations) {
		this.estimations = estimations;
	}

	public String getAckRemark() {
		return ackRemark;
	}

	public void setAckRemark(String ackRemark) {
		this.ackRemark = ackRemark;
	}

	public TicketState getState() {
		return state;
	}

	public void setState(TicketState state) {
		this.state = state;
	}

	public Collection<TicketBySprint> getTicketsbySprint() {
		return ticketsbySprint;
	}

	public void setTicketsbySprint(Collection<TicketBySprint> ticketsbySprint) {
		this.ticketsbySprint = ticketsbySprint;
	}

	public Ticket getSubject() {
		return subject;
	}

	public void setSubject(Ticket subject) {
		this.subject = subject;
	}

	public Collection<Ticket> getUserStories() {
		return userStories;
	}

	public void setUserStories(Collection<Ticket> userStories) {
		this.userStories = userStories;
	}

	public String getJiraId() {
		return jiraId;
	}

	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public boolean isSubjectAcknowledged() {
		return subjectAcknowledged;
	}

	public void setSubjectAcknowledged(boolean subjectAcknowledged) {
		this.subjectAcknowledged = subjectAcknowledged;
	}

	public boolean isAckMeeting() {
		return ackMeeting;
	}

	public void setAckMeeting(boolean ackMeeting) {
		this.ackMeeting = ackMeeting;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getRank() {
		return rank;
	}

	public void setRank(BigInteger rank) {
		this.rank = rank;
	}

	public Functionality getFunctionality() {
		return functionality;
	}
	
	public void setFunctionality(Functionality functionality) {
		this.functionality = functionality;
	}
	
	public FunctionalityAnalysis getFunctionalityAnalysis() {
		return functionalityAnalysis;
	}

	public void setFunctionalityAnalysis(FunctionalityAnalysis functionalityAnalysis) {
		this.functionalityAnalysis = functionalityAnalysis;
	}

	public PeerReview getPeerReview() {
		return peerReview;
	}

	public void setPeerReview(PeerReview peerReview) {
		this.peerReview = peerReview;
	}

}