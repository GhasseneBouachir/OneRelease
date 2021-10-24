package org.lesstif.jira.issue;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import org.lesstif.jira.project.AvatarUrl;

import lombok.Data;

@Data
@JsonIgnoreProperties({"accountId","accountType"})
public class Reporter {	
	private String self;
	private String key;
	private String name;
	private String emailAddress;
	private AvatarUrl avatarUrls;
	private String displayName;
	private Boolean active;
	private String timeZone;
	
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public AvatarUrl getAvatarUrls() {
		return avatarUrls;
	}
	public void setAvatarUrls(AvatarUrl avatarUrls) {
		this.avatarUrls = avatarUrls;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
}
