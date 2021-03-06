package org.onerelease.model;

import java.util.*;
import javax.persistence.*;
import org.openxava.annotations.*;
import org.openxava.model.*;

@Views({@View(name="projectUnderSquad", members="reference , name"),
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
	@ListProperties(value = "reference, name, summary, description, priority,"
			+ " status, creationDate, rank")
	@SaveAction("Ticket.saveEpic")
	@Condition("NOT ${name} = null "
			+ "AND ${project.id} = ${this.id}"
			+ "")
	@CollectionView("Epic")
	@OneToMany( mappedBy="project", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Collection<Ticket> epics;
	

	@Condition("${name} = null "
			+ "AND ${project.id} = ${this.id}"
			+ "")
	@CollectionView(value = "UserStory")
	@OneToMany( mappedBy="project", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@RowAction("addTicketToSprint.assign")
	@ListProperties(value = "reference, summary, subject.reference, state, description, type, priority,"
			+ " status, subjectAcknowledged, ackMeeting, ackRemark, creationDate, rank")
	@NewAction("Ticket.createUserStoryFromProject")
	private Collection<Ticket> backlog;

	@NewAction("HideSquadActions.new")
	@EditAction("HideSquadActions.edit")
	@OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
	private Collection<Squad> squad;

	@OneToMany(orphanRemoval = true, mappedBy = "project", cascade = CascadeType.REMOVE)
	@DetailActions({ @DetailAction("Sprint.completeSprint"), @DetailAction("Sprint.startSprint") })
	@SaveAction("Sprint.save")
	@EditAction("Sprint.edit")
	@CollectionView("SprintUnderProject")
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

	public Collection<Squad> getSquad() {
		return squad;
	}

	public void setSquad(Collection<Squad> squad) {
		this.squad = squad;
	}

	public Collection<Ticket> getEpics() {
		return epics;
	}

	public void setEpics(Collection<Ticket> epics) {
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

}
