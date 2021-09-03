//package org.onerelease.model;
//
//import java.util.*;
//
//import javax.persistence.*;
//
//import org.onerelease.enumeration.*;
//import org.openxava.annotations.*;
//import org.openxava.model.*;
//
//@Views({@View(members="reference, name, backlogType ; Subjects {subjects} ")})
//@Entity
//
//
//public class Backlog extends Identifiable {
//	
//	@ManyToOne(optional = false, fetch = FetchType.LAZY)
//	private Project project;
//	
//	@Column( length = 10)//unique=true,
//    @ReadOnly
//    private String reference;
//	
//	@Column(length = 30)
//	private String name;
//	
//	@Column
//	@Required
//	private BacklogType backlogType;
//	
//	@OneToMany(mappedBy="backlog",cascade=CascadeType.ALL)
//	@RowActions({@RowAction("Move.moveUp"),@RowAction("Move.moveDown")
//	//,@RowAction("addTicketToSprint.assign")
//	})
//	@XOrderBy("rank")
//    @ListProperties(value="reference, summary, state, description, type, priority,"
//    + "status, subjectAcknowledged, ackMeeting, ackRemark, creationDate, rank")
//   private Collection<Ticket> subjects;
//
//	public String getReference() {
//		return reference;
//	}
//
//	public void setReference(String reference) {
//		this.reference = reference;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//
//	public Project getProject() {
//		return project;
//	}
//
//	public void setProject(Project project) {
//		this.project = project;
//	}
//
//	public Collection<Ticket> getSubjects() {
//		return subjects;
//	}
//
//	public void setSubjects(Collection<Ticket> subjects) {
//		this.subjects = subjects;
//	}
//
//	public BacklogType getBacklogType() {
//		return backlogType;
//	}
//
//	public void setBacklogType(BacklogType backlogType) {
//		this.backlogType = backlogType;
//	}
//}
