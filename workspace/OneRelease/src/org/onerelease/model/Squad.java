package org.onerelease.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

@Entity
@Views({@View(name = "squadUnderSprint", 
members="reference, name, description; scrumMaster ; collaborators "),
	@View(name = "squadUnderProject", 
	members="name, description; scrumMaster  ; collaborators; sprints "),
	@View(name = "squadUnderCollaborator", 
	members=" name, description; "),
	})

public class Squad extends Identifiable {
	

	


	@PreRemove
	public void onPreRemove() {
		for (Collaborator collaborator : this.getCollaborators()) {
			collaborator.setSquad(null);
		}
	}
	
	
	
	@Column(length = 10)//unique=true,
    @ReadOnly
    private String reference;
	
	@Column(length = 30)
	private String name;
	
	@Column(length = 30)
	private String description;
	
	@Required
	@OneToOne( optional=false,fetch=FetchType.LAZY)
	@ReferenceView("CollaboratorUnderSquad")
	@DescriptionsList(descriptionProperties="lastName,firstName",
	condition="${role} = 0"
//			+ " and ${squadSM} is null"
			+ "")
//	@NoModify
//	@NoCreate
	// or override new action to hide squad property in the detail view
	private Collaborator scrumMaster;
	
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@DescriptionsList(descriptionProperties="name",
	showReferenceView=true,forViews="DEFAULT")
	@ReferenceView("projectUnderSquad")
	private Project project;
	
	@OneToMany(mappedBy="squad",fetch=FetchType.LAZY)
	private Collection<Sprint> sprints;
	
	@SaveAction(value="Collaborator.saveInCollection")
	@OneToMany(mappedBy="squad",fetch=FetchType.LAZY)
	@ListProperties("lastName, firstName, emailAddress, alias, role")
	private  Collection<Collaborator> collaborators;
	
	
	
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


	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}


	public Collection<Collaborator> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(Collection<Collaborator> collaborators) {
		this.collaborators = collaborators;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Collection<Sprint> getSprints() {
		return sprints;
	}

	public void setSprints(Collection<Sprint> sprints) {
		this.sprints = sprints;
	}

	public Collaborator getScrumMaster() {
		return scrumMaster;
	}

	public void setScrumMaster(Collaborator scrumMaster) {
		this.scrumMaster = scrumMaster;
	}


}
