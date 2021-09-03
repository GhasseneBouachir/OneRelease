package org.lesstif.jira.issue;

import org.lesstif.jira.JsonPrettyString;

import lombok.Data;

@Data
public class Priority extends JsonPrettyString {
	public static final String PRIORITY_BLOCKER = "Highest";
	public static final String PRIORITY_CRITICAL = "High";
	public static final String PRIORITY_MAJOR = "Medium";
	public static final String PRIORITY_MINOR = "Low";
	public static final String PRIORITY_TRIVIAL = "Trivial";
	
	private String self;
	private String iconUrl;
	private String name;
	private String id;
	private String statusColor;
	private String description;
	
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatusColor() {
		return statusColor;
	}
	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
