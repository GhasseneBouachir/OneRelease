package org.onerelease.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.openxava.annotations.*;
import org.openxava.model.*;
import org.onerelease.enumeration.*;
@Views({@View(members="jiraAccountID;"
		+ " lastName, firstName , alias ; emailAddress ; role;"
		+ " "),
	@View(name="CollaboratorUnderTicketBySprint", members="firstName, lastName, role"),
	@View(name="CollaboratorUnderFunctionalityAnalysis",extendsView="CollaboratorUnderTicketBySprint"),
	@View(name="CollaboratorUnderPeerReview",extendsView="CollaboratorUnderTicketBySprint"),
	@View(name="CollaboratorUnderSquad",extendsView="CollaboratorUnderTicketBySprint")
})
@Entity
public class Collaborator extends Identifiable {
	
	@Column
	@ReadOnly
	private String jiraAccountID;
	
	@Column
	@Email(message="Email address is not valid")
	@ReadOnly(onCreate=false)
	private String emailAddress;
	
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@ReferenceView("squadUnderCollaborator")
	private Squad squad;
	
	@OneToOne(mappedBy="scrumMaster")
	private Squad squadSM;
	
	@Column(length = 20)
	private String lastName;
	
	@Column(length = 20)
	private String firstName;
	
	@Column(length = 3)
	private String alias;
	
	@Column
	@Required
	private collaboratorRole role;
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public collaboratorRole getRole() {
		return role;
	}

	public void setRole(collaboratorRole role) {
		this.role = role;
	}

	public Squad getSquad() {
		return squad;
	}

	public void setSquad(Squad squad) {
		this.squad = squad;
	}

	public String getJiraAccountID() {
		return jiraAccountID;
	}

	public void setJiraAccountID(String jiraAccountID) {
		this.jiraAccountID = jiraAccountID;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

//	public Squad getSquadSM() {
//		return squadSM;
//	}
//
//	public void setSquadSM(Squad squadSM) {
//		this.squadSM = squadSM;
//	}


}
