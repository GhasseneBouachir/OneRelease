package org.lesstif.jira.issue;

import lombok.Data;

@Data
public class Vote {
	private String self;
	private Integer votes;
	private Boolean hasVoted;
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public Integer getVotes() {
		return votes;
	}
	public void setVotes(Integer votes) {
		this.votes = votes;
	}
	public Boolean getHasVoted() {
		return hasVoted;
	}
	public void setHasVoted(Boolean hasVoted) {
		this.hasVoted = hasVoted;
	}
}
