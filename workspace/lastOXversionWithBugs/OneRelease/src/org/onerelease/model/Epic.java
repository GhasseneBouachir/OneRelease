package org.onerelease.model;

import java.math.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.model.*;
@Views({
	@View(name="Epic", members="# reference, name,  summary; creationDate, rank ; userStories; "
			+ "  Description [ description ]; "
			)
})
@Entity
public class Epic extends Identifiable {
			@Column(length = 20)
			private String name;

			// @Hidden
			@Column
			@ReadOnly
			private BigInteger rank;
			
			@Column(length = 10)
		    @ReadOnly
		    private String reference;
			
			@Column(length = 80)
			@Required(message = "you have to specify the Summary")
			private String summary;
			
			@Column
			@Lob
			@Stereotype("SIMPLE_HTML_TEXT")
			private String description;
			
			@Required
			@DefaultValueCalculator(CurrentDateCalculator.class)
			private Date creationDate;
			
			@CollectionView("UserStory")
			@RowAction("addTicketToSprint.assign")
			@ListProperties("reference, summary, type, priority, status")
			@SaveAction("Ticket.saveUserStory")
			@OneToMany(mappedBy="subject", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
			@NewAction("Ticket.createUserStoryOnEpic")
			@EditAction("Ticket.editUserStory")
			private Collection<Ticket> userStories;
			
			@ManyToOne(optional = false, fetch = FetchType.LAZY)
			private Project project;
			

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

			public Date getCreationDate() {
				return creationDate;
			}

			public void setCreationDate(Date creationDate) {
				this.creationDate = creationDate;
			}

			public Collection<Ticket> getUserStories() {
				return userStories;
			}

			public void setUserStories(Collection<Ticket> userStories) {
				this.userStories = userStories;
			}

			public Project getProject() {
				return project;
			}

			public void setProject(Project project) {
				this.project = project;
			}
}
