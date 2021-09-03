package org.onerelease.model;

import java.math.*;
import java.util.*;

import javax.persistence.*;
import javax.ws.rs.*;

import org.onerelease.actions.FunctionalityAnalysis.*;
import org.onerelease.calculators.*;
import org.onerelease.enumeration.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.model.*;

@Views({@View(name="PeerReview" ,members = " "
//		+ "ticket;   "
		+ "Review [ "
		+ " authorOfDelevirableReviewed; peerReviwer;  meetingDate ; " + "  peerReviewType;  "
		+ " verifier ;  completionDate, otherReviewNeeded;" + " peerReviewAnalysis "
		+ "] DefectsMeasures [# "
		+ " numberOfMajorDefects,  numberOfMinorDefetcs, totalDefectsFound; "
		+ " numberOfCorrectedDefects, numberOfNotCorrectedDefects;" + "numberofAcceptedCorrections;  ]  "
//		+ "Appraisal [ verifier ;  completionDate, otherReviewNeeded;" + " peerReviewAnalysis  ]"
		+ "  logSheet  ")
})
@Entity
public class PeerReview extends Identifiable {

	
	@OneToOne
	@DefaultValueCalculator(value = createFuncionalityAnalysis.class)
	@ReferenceView("TicketUnderFunctionalityAnalysis")
	private Ticket ticket;

	@ManyToOne(optional = true)
	@ReferenceView("CollaboratorUnderPeerReview")
	@NoModify
	private Collaborator authorOfDelevirableReviewed;

	@ManyToOne(optional = true)
	@ReferenceView("CollaboratorUnderPeerReview")
	@DescriptionsList(depends="this.authorOfDelevirableReviewed",// showReferenceView=true,
        descriptionProperties="lastName, firstName", condition="${id} != ?")
	private Collaborator peerReviwer; 
	


	@Column
	private BigDecimal numberOfMajorDefects;

	@Column
	private BigDecimal numberOfMinorDefetcs;
	
	@Calculation("(peerReview.numberOfMajorDefects + peerReview.numberOfMinorDefetcs)")
	private BigDecimal totalDefectsFound;
	
	@Column
	private BigDecimal numberOfNotCorrectedDefects;

	@Column
	private BigDecimal numberOfCorrectedDefects;

	@Column
	private BigDecimal numberofAcceptedCorrections;

	@Column
	private Date meetingDate;

	@Column
	private PeerReviewType peerReviewType;

	@ManyToOne(optional = true)
	@ReferenceView("CollaboratorUnderPeerReview")
	@DescriptionsList(depends="this.authorOfDelevirableReviewed, this.peerReviwer", // showReferenceView=true,
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
//	@AsEmbedded
	@OneToMany(mappedBy = "peerReview", cascade=CascadeType.REMOVE )
	@ListProperties("idLog, severity")
	private Collection<PeerReviewLine> logSheet;

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Collaborator getPeerReviwer() {
		return peerReviwer;
	}

	public void setPeerReviwer(Collaborator peerReviwer) {
		this.peerReviwer = peerReviwer;
	}

	public BigDecimal getNumberOfMajorDefects() {
		return numberOfMajorDefects;
	}

	public void setNumberOfMajorDefects(BigDecimal numberOfMajorDefects) {
		this.numberOfMajorDefects = numberOfMajorDefects;
	}

	public BigDecimal getNumberOfMinorDefetcs() {
		return numberOfMinorDefetcs;
	}

	public void setNumberOfMinorDefetcs(BigDecimal numberOfMinorDefetcs) {
		this.numberOfMinorDefetcs = numberOfMinorDefetcs;
	}

	public BigDecimal getTotalDefectsFound() {
		return totalDefectsFound;
	}

	public void setTotalDefectsFound(BigDecimal totalDefectsFound) {
		this.totalDefectsFound = totalDefectsFound;
	}

	public BigDecimal getNumberOfNotCorrectedDefects() {
		return numberOfNotCorrectedDefects;
	}

	public void setNumberOfNotCorrectedDefects(BigDecimal numberOfNotCorrectedDefects) {
		this.numberOfNotCorrectedDefects = numberOfNotCorrectedDefects;
	}

	public BigDecimal getNumberOfCorrectedDefects() {
		return numberOfCorrectedDefects;
	}

	public void setNumberOfCorrectedDefects(BigDecimal numberOfCorrectedDefects) {
		this.numberOfCorrectedDefects = numberOfCorrectedDefects;
	}

	public BigDecimal getNumberofAcceptedCorrections() {
		return numberofAcceptedCorrections;
	}

	public void setNumberofAcceptedCorrections(BigDecimal numberofAcceptedCorrections) {
		this.numberofAcceptedCorrections = numberofAcceptedCorrections;
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



}
