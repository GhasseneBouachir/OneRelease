package org.lesstif.jira.issue;

import lombok.Data;

@Data
public class TimeTracking {
	private String originalEstimate;
	private String remainingEstimate;
	private String timeSpent;
	private String originalEstimateSeconds;
	private String remainingEstimateSeconds;
	private String timeSpentSeconds;
	public String getOriginalEstimate() {
		return originalEstimate;
	}
	public void setOriginalEstimate(String originalEstimate) {
		this.originalEstimate = originalEstimate;
	}
	public String getRemainingEstimate() {
		return remainingEstimate;
	}
	public void setRemainingEstimate(String remainingEstimate) {
		this.remainingEstimate = remainingEstimate;
	}
	public String getTimeSpent() {
		return timeSpent;
	}
	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}
	public String getOriginalEstimateSeconds() {
		return originalEstimateSeconds;
	}
	public void setOriginalEstimateSeconds(String originalEstimateSeconds) {
		this.originalEstimateSeconds = originalEstimateSeconds;
	}
	public String getRemainingEstimateSeconds() {
		return remainingEstimateSeconds;
	}
	public void setRemainingEstimateSeconds(String remainingEstimateSeconds) {
		this.remainingEstimateSeconds = remainingEstimateSeconds;
	}
	public String getTimeSpentSeconds() {
		return timeSpentSeconds;
	}
	public void setTimeSpentSeconds(String timeSpentSeconds) {
		this.timeSpentSeconds = timeSpentSeconds;
	}
}
