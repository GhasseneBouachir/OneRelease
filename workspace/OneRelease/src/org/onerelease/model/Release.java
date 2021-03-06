package org.onerelease.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.model.*;
import org.onerelease.enumeration.*;

@Views({ @View(members = "Details [reference, type, version]; Milestones [bAAnalysisStart, "
		+ "bAAnalysisDeadline, developmentStart, developmentDealine; uATStart, uATDeadline, e2EStart, e2EDeadline;"
		+ "signOffDate, releasePresentationDate, gOLiveDate]; tickets"),
@View(name = "ReleaseUnderTicket", members = "Related Release [reference, type, gOLiveDate, uATStart]") })
@Entity
public class Release extends Identifiable {

	@Column(length = 10)
	private String reference;

	@Column
	private ReleaseType type;
	
	@Column(length = 10)
	private String version;

	@OneToMany(mappedBy = "release",cascade=CascadeType.ALL)
	//@ListAction("TicketBySprint.moveUp")
	private Collection<Ticket> tickets;

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date bAAnalysisStart;

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date bAAnalysisDeadline;

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date developmentStart;

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date developmentDealine;

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date uATStart;

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date uATDeadline;

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date e2EStart;

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date e2EDeadline;

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date signOffDate;

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date releasePresentationDate;

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date gOLiveDate;

	public ReleaseType getType() {
		return type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}



	public void setType(ReleaseType type) {
		this.type = type;
	}

	public Date getgOLiveDate() {
		return gOLiveDate;
	}

	public void setgOLiveDate(Date gOLiveDate) {
		this.gOLiveDate = gOLiveDate;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Date getbAAnalysisStart() {
		return bAAnalysisStart;
	}

	public void setbAAnalysisStart(Date bAAnalysisStart) {
		this.bAAnalysisStart = bAAnalysisStart;
	}

	public Date getbAAnalysisDeadline() {
		return bAAnalysisDeadline;
	}

	public void setbAAnalysisDeadline(Date bAAnalysisDeadline) {
		this.bAAnalysisDeadline = bAAnalysisDeadline;
	}

	public Date getDevelopmentStart() {
		return developmentStart;
	}

	public void setDevelopmentStart(Date developmentStart) {
		this.developmentStart = developmentStart;
	}

	public Date getDevelopmentDealine() {
		return developmentDealine;
	}

	public void setDevelopmentDealine(Date developmentDealine) {
		this.developmentDealine = developmentDealine;
	}

	public Date getuATStart() {
		return uATStart;
	}

	public void setuATStart(Date uATStart) {
		this.uATStart = uATStart;
	}

	public Date getuATDeadline() {
		return uATDeadline;
	}

	public void setuATDeadline(Date uATDeadline) {
		this.uATDeadline = uATDeadline;
	}

	public Date getE2EStart() {
		return e2EStart;
	}

	public void setE2EStart(Date e2eStart) {
		e2EStart = e2eStart;
	}

	public Date getE2EDeadline() {
		return e2EDeadline;
	}

	public void setE2EDeadline(Date e2eDeadline) {
		e2EDeadline = e2eDeadline;
	}

	public Date getSignOffDate() {
		return signOffDate;
	}

	public void setSignOffDate(Date signOffDate) {
		this.signOffDate = signOffDate;
	}

	public Date getReleasePresentationDate() {
		return releasePresentationDate;
	}

	public void setReleasePresentationDate(Date releasePresentationDate) {
		this.releasePresentationDate = releasePresentationDate;
	}

	public Collection<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Collection<Ticket> tickets) {
		this.tickets = tickets;
	}

}
