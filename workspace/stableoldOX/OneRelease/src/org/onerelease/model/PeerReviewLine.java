package org.onerelease.model;

import java.math.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;

import org.hibernate.annotations.*;
import org.onerelease.calculators.*;
import org.onerelease.enumeration.*;
import org.openxava.annotations.*;
import org.openxava.model.*;


@Entity
@View(name="PeerReviewLine", members="   DefectIdentification[ "
		+ "idLog , "
		+ " severity, location; "
		+ "desctiptionActinos ] "
		+ "CorrectionAndFollowUp [authorComments, "
		+ " correctionState; verifierComments, verificationState ]")


public class PeerReviewLine extends Identifiable{
	
	
	
	
	@Column
	@ReadOnly
	private int idLog;
	
	
	@Column
	private Severity severity;
	
	@Column
	private LocationFunctionalityAnalysis location;
	
	@Column(length=500)
	@Stereotype("MEMO")
	private String desctiptionActinos;
	
	@Column(length=500)
//	@Stereotype("MEMO")
	private String authorComments;
	
	@Column
	private State correctionState;
	
	
	
	@Column
	private State verificationState;
	
	
	
	
	
	@Column(length=500)
//	@Stereotype("MEMO")
	@DefaultValueCalculator(value=org.onerelease.calculators.VerifierCom.class, properties= { @PropertyValue(name="peerReviewTest" , from="peerReview.test")})
	private String verifierComments;

	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
//	@DefaultValueCalculator(PeerReviewCalc.class)
	private PeerReview peerReview;


	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public LocationFunctionalityAnalysis getLocation() {
		return location;
	}

	public void setLocation(LocationFunctionalityAnalysis location) {
		this.location = location;
	}

	public String getDesctiptionActinos() {
		return desctiptionActinos;
	}

	public void setDesctiptionActinos(String desctiptionActinos) {
		this.desctiptionActinos = desctiptionActinos;
	}

	public String getAuthorComments() {
		return authorComments;
	}

	public void setAuthorComments(String authorComments) {
		this.authorComments = authorComments;
	}

	public State getCorrectionState() {
		return correctionState;
	}

	public void setCorrectionState(State correctionState) {
		this.correctionState = correctionState;
	}







	
	public String getVerifierComments() {
		return verifierComments;
	}

	public void setVerifierComments(String verifierComments) {
		this.verifierComments = verifierComments;
	}

	public int getIdLog() {
		return idLog;
	}

	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}

	public State getVerificationState() {
		return verificationState;
	}

	public void setVerificationState(State verificationState) {
		this.verificationState = verificationState;
	}

	public PeerReview getPeerReview() {
		return peerReview;
	}

	public void setPeerReview(PeerReview peerReview) {
		this.peerReview = peerReview;
	}





	
}
