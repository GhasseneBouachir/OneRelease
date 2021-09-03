package org.lesstif.jira.issue;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import org.lesstif.jira.project.AvatarUrl;

import lombok.Data;

@Data 
@JsonIgnoreProperties({"accountId"})
public class Lead {
	
	private String self;
	private String name;
	private AvatarUrl avatarUrls;
	private String displayName;
	private Boolean active;
	private String key;
	
	
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
