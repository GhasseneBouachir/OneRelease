package org.onerelease.model;

import java.util.*;
import javax.persistence.*;
import org.openxava.annotations.*;
import org.openxava.model.*;

@Views({@View(members="reference, name; epics; "
		+ "backlog; squads; sprints"),@View(name="projectUnderSquad", members="reference , name"),
	@View(name="projectUnderSprint", extendsView="projectUnderSquad")})
@Entity
public class Project extends Identifiable {
	

	@Column(unique = true)
	private String reference;

	@Column(length = 20, unique = true)
	private String name;

	@RowActions({ @RowAction("Move.moveUp"), @RowAction("Move.moveDown")
			// ,@RowAction("addTicketToSprint.assign")
	})
	@NewAction("HideEpicActions.new")
	@EditAction("HideEpicActions.edit")
	@XOrderBy("rank")
	@ListProperties(value = "reference, name, summary, "
			+ " creationDate, rank")
	@SaveAction("Ticket.saveEpic")
	@Condition("NOT ${name} = null "
			+ "AND ${project.id} = ${this.id}"
			+ "")
	@CollectionView("Epic")
	@OneToMany( mappedBy="project", orphanRemoval=true//, cascade = CascadeType.ALL, fetch=FetchType.LAZY
	)
	@AsEmbedded
	private Collection<Epic> epics;
	

	@Condition( "${project.id} = ${this.id}")
	@CollectionView(value = "UserStory")
	@RowAction("addTicketToSprint.assign")
	@ListProperties(value = "reference, summary, subject.reference, type, priority,"
			+ " status, creationDate")
	@NewAction("Ticket.createUserStoryFromProject")
	@OneToMany(mappedBy = "project", orphanRemoval=true //,cascade = CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true	
	)
	@SaveAction("Ticket.saveUserStory")
	@EditAction("Ticket.editUserStory")
	@AsEmbedded
	private Collection<Ticket> backlog;

	@NewAction("HideSquadActions.new")
	@EditAction("HideSquadActions.edit")
	@CollectionView("squadUnderProject")
	@OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
	private Collection<Squad> squads;


	@DetailActions({ @DetailAction("Sprint.completeSprint"), @DetailAction("Sprint.startSprint") })
	@SaveAction("Sprint.save")
	@EditAction("Sprint.edit")
	@CollectionView("SprintUnderProject")
	@OneToMany(mappedBy = "project", orphanRemoval=true	
//	, cascade = CascadeType.REMOVE
	)
	@AsEmbedded
	private Collection<Sprint> sprints;


	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<Sprint> getSprints() {
		return sprints;
	}

	public void setSprints(Collection<Sprint> sprints) {
		this.sprints = sprints;
	}


	public Collection<Epic> getEpics() {
		return epics;
	}

	public void setEpics(Collection<Epic> epics) {
		this.epics = epics;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Collection<Ticket> getBacklog() {
		return backlog;
	}

	public void setBacklog(Collection<Ticket> backlog) {
		this.backlog = backlog;
	}

	public Collection<Squad> getSquads() {
		return squads;
	}

	public void setSquads(Collection<Squad> squads) {
		this.squads = squads;
	}

}
