package org.onerelease.model;

import java.util.*;

import javax.persistence.*;

import org.onerelease.actions.FunctionalityAnalysis.*;
import org.onerelease.util.*;
import org.openxava.annotations.*;
import org.openxava.model.*;

@Views({@View(name="FunctionnalityAnalysisUnderUserStory",
			members="analysisDate; businessAnalyst;  functionalityDescription; "
					+ "functionalityDiagram; functionalityFunctional; "
					+ "technicalImpacts; testingStrategies")})
@Entity
public class FunctionalityAnalysis extends Identifiable {
	
	@OneToOne(mappedBy="functionalityAnalysis",optional=true,cascade=CascadeType.ALL)
	//@DefaultValueCalculator(value=createFuncionalityAnalysis.class)
	@ReferenceView("TicketUnderFunctionalityAnalysis")
	private Ticket ticket;
	
	
	@ManyToOne(optional=true)
	@ReferenceView("CollaboratorUnderFunctionalityAnalysis")
	@DescriptionsList(descriptionProperties="lastName, firstName")
	
	private Collaborator businessAnalyst;
	
	@Column
	@DefaultValueCalculator(CurrentDate.class)
	private Date analysisDate;
	
	@Column
	@Lob
	@Stereotype("SIMPLE_HTML_TEXT")
	private String functionalityDescription;
	
	
	
	@Column
	@Lob
	@Stereotype("DIAGRAM")
	private String functionalityDiagram;
	
	@Column
	@Lob
	@Stereotype("SIMPLE_HTML_TEXT")
	private String functionalityFunctional;
	
	@Column
	@Lob
	@Stereotype("SIMPLE_HTML_TEXT")
	private String technicalImpacts;
	
	@Column
	@Lob
	@Stereotype("SIMPLE_HTML_TEXT")
	private String testingStrategies;
	
	@ManyToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="functionality_id")
	private Functionality functionality;
	
	

	
	
	public Collaborator getBusinessAnalyst() {
		return businessAnalyst;
	}

	public void setBusinessAnalyst(Collaborator businessAnalyst) {
		this.businessAnalyst = businessAnalyst;
	}

	public Date getAnalysisDate() {
		return analysisDate;
	}

	public void setAnalysisDate(Date analysisDate) {
		this.analysisDate = analysisDate;
	}

	public String getFunctionalityDescription() {
		return functionalityDescription;
	}

	public void setFunctionalityDescription(String functionalityDescription) {
		this.functionalityDescription = functionalityDescription;
	}


	public String getFunctionalityFunctional() {
		return functionalityFunctional;
	}

	public void setFunctionalityFunctional(String functionalityFunctional) {
		this.functionalityFunctional = functionalityFunctional;
	}

	public String getTechnicalImpacts() {
		return technicalImpacts;
	}

	public void setTechnicalImpacts(String technicalImpacts) {
		this.technicalImpacts = technicalImpacts;
	}

	public String getTestingStrategies() {
		return testingStrategies;
	}

	public void setTestingStrategies(String testingStrategies) {
		this.testingStrategies = testingStrategies;
	}

	public Functionality getFunctionality() {
		return functionality;
	}

	public void setFunctionality(Functionality functionality) {
		this.functionality = functionality;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getFunctionalityDiagram() {
		return functionalityDiagram;
	}

	public void setFunctionalityDiagram(String functionalityDiagram) {
		this.functionalityDiagram = functionalityDiagram;
	}

	

}
