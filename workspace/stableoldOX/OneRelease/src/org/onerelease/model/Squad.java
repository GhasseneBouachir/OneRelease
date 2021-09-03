package org.onerelease.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

@Entity
@Views({@View(name = "squadUnderSprint", 
members="reference, name, description ; collaborators "),
	@View(name = "squadUnderCollaborator", 
	members="reference, name, description; project; collaborators"),
	})

public class Squad extends Identifiable {
	

	
	public Collection<Sprint> getSprints() {
		return sprints;
	}

	public void setSprints(Collection<Sprint> sprints) {
		this.sprints = sprints;
	}

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
	
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@DescriptionsList(descriptionProperties="name",
	showReferenceView=true,forViews="DEFAULT")
	@ReferenceView("projectUnderSquad")
	private Project project;
	
	@OneToMany(mappedBy="squad",fetch=FetchType.LAZY)
	private Collection<Sprint> sprints;
	
	@SaveAction(value="Collaborator.saveInCollection")
	@OneToMany(mappedBy="squad",fetch=FetchType.LAZY)
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


}
