package org.onerelease.model;

import java.math.*;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.onerelease.actions.FunctionalityAnalysis.*;
import org.onerelease.calculators.*;
import org.onerelease.enumeration.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.model.*;

@Views({@View(name="PeerReview" ,members = " "
//		+ "ticket;   "
		+ "Review [# "
		+ " authorOfDelevirableReviewed, peerReviewer, verifier;  meetingDate , " + "    "
		+ "  peerReviewType; completionDate, otherReviewNeeded;  " + " peerReviewAnalysis "
		+ "] DefectsMeasures [# "
		+ " numberOfMajorDefects,  numberOfMinorDefetcs, totalDefectsFound; "
		+ " numberOfCorrectedDefects, numberOfNotCorrectedDefects;" + "numberofAcceptedCorrections;  ]  "
//		+ "Appraisal [ verifier ;  completionDate, otherReviewNeeded;" + " peerReviewAnalysis  ]"
		+ "  LogSheet[# logSheet ]  ")
})
@Entity
public class PeerReview extends Identifiable {

	
	@OneToOne(mappedBy="peerReview", fetch=FetchType.LAZY)
//	@DefaultValueCalculator(value = createFuncionalityAnalysis.class)
	@ReferenceView("TicketUnderFunctionalityAnalysis")
	private Ticket ticket;

	@ManyToOne(optional = true)
	@ReferenceView("CollaboratorUnderPeerReview")
	@DescriptionsList(descriptionProperties="lastName, firstName")
	@ReadOnly
	private Collaborator authorOfDelevirableReviewed;

	@ManyToOne(optional = true)
	@ReferenceView("CollaboratorUnderPeerReview")
	@DescriptionsList(depends="this.authorOfDelevirableReviewed",// showReferenceView=true,
        descriptionProperties="lastName, firstName", condition="${id} != ?")
	private Collaborator peerReviewer; 
	
	

	
	
//	@Calculation("(peerReview.numberOfMajorDefects + peerReview.numberOfMinorDefetcs)")
//	private BigDecimal totalDefectsFound;
	
	@Column(length=4)
	@DisplaySize(4)
	private int numberOfNotCorrectedDefects;

	@Column(length=4)
	@DisplaySize(4)
	private int numberOfCorrectedDefects;

	@Column(length=4)
	@DisplaySize(4)
	private int numberofAcceptedCorrections;

	@Column
	private Date meetingDate;

	@Column
	private PeerReviewType peerReviewType;

	@ManyToOne(optional = true)
	@ReferenceView("CollaboratorUnderPeerReview")
	@DescriptionsList(depends="this.authorOfDelevirableReviewed, this.peerReviewer", // showReferenceView=true,
    descriptionProperties="lastName, firstName", condition="${id} != ? and ${id}!=?")
	private Collaborator verifier;

	@Column
	private Date completionDate;

	@Column
	private Boolean otherReviewNeeded;

	@Column(length=500)
	@Stereotype("MEMO")
	private String peerReviewAnalysis;
	
	@CollectionView("PeerReviewLine")
	@NewAction("PeerReviewLine.newPeerReviewLine")
	@EditAction("PeerReviewLine.editPeerReviewLine")
	@ListProperties("idLog, severity, location")
	@OneToMany(mappedBy = "peerReview", cascade=CascadeType.ALL )
	@AsEmbedded
	@SaveAction(value="PeerReviewLine.savePeerReviewLine")
	private Collection<PeerReviewLine> logSheet;

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	@DisplaySize(4)
	public int getNumberOfMajorDefects() {
		int nbMajors = 0;
		for(PeerReviewLine peerReviewLine : getLogSheet()) 
		{
			if(peerReviewLine.getSeverity().equals(Severity.Major))
				nbMajors ++;
		}
		return nbMajors;
	}

	@DisplaySize(4)
	public int getNumberOfMinorDefetcs() {
		int nbMinor = 0;
		for(PeerReviewLine peerReviewLine : getLogSheet()) 
		{
			if(peerReviewLine.getSeverity().equals(Severity.Minor))
				nbMinor ++;
		}
		return nbMinor;
	}

	@DisplaySize(4)
	public int getTotalDefectsFound() {
		return getNumberOfMinorDefetcs() + getNumberOfMajorDefects() ;
	}



	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public PeerReviewType getPeerReviewType() {
		return peerReviewType;
	}

	public void setPeerReviewType(PeerReviewType peerReviewType) {
		this.peerReviewType = peerReviewType;
	}

	public Collaborator getVerifier() {
		return verifier;
	}

	public void setVerifier(Collaborator verifier) {
		this.verifier = verifier;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public Boolean getOtherReviewNeeded() {
		return otherReviewNeeded;
	}

	public void setOtherReviewNeeded(Boolean otherReviewNeeded) {
		this.otherReviewNeeded = otherReviewNeeded;
	}

	public String getPeerReviewAnalysis() {
		return peerReviewAnalysis;
	}

	public void setPeerReviewAnalysis(String peerReviewAnalysis) {
		this.peerReviewAnalysis = peerReviewAnalysis;
	}

	public Collection<PeerReviewLine> getLogSheet() {
		return logSheet;
	}

	public void setLogSheet(Collection<PeerReviewLine> logSheet) {
		this.logSheet = logSheet;
	}

	public Collaborator getAuthorOfDelevirableReviewed() {
		return authorOfDelevirableReviewed;
	}

	public void setAuthorOfDelevirableReviewed(Collaborator authorOfDelevirableReviewed) {
		this.authorOfDelevirableReviewed = authorOfDelevirableReviewed;
	}

	public Collaborator getPeerReviewer() {
		return peerReviewer;
	}

	public void setPeerReviewer(Collaborator peerReviewer) {
		this.peerReviewer = peerReviewer;
	}

	public int getNumberOfNotCorrectedDefects() {
		return numberOfNotCorrectedDefects;
	}

	public void setNumberOfNotCorrectedDefects(int numberOfNotCorrectedDefects) {
		this.numberOfNotCorrectedDefects = numberOfNotCorrectedDefects;
	}

	public int getNumberOfCorrectedDefects() {
		return numberOfCorrectedDefects;
	}

	public void setNumberOfCorrectedDefects(int numberOfCorrectedDefects) {
		this.numberOfCorrectedDefects = numberOfCorrectedDefects;
	}

	public int getNumberofAcceptedCorrections() {
		return numberofAcceptedCorrections;
	}

	public void setNumberofAcceptedCorrections(int numberofAcceptedCorrections) {
		this.numberofAcceptedCorrections = numberofAcceptedCorrections;
	}



}
