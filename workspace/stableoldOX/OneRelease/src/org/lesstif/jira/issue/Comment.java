package org.lesstif.jira.issue;

import lombok.Data;

@Data
public class Comment {
	private Integer startAt;
	private Integer maxResults;
	private Integer total;
	private String[] comments;
	public Integer getStartAt() {
		return startAt;
	}
	public void setStartAt(Integer startAt) {
		this.startAt = startAt;
	}
	public Integer getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String[] getComments() {
		return comments;
	}
	public void setComments(String[] comments) {
		this.comments = comments;
	}
}
