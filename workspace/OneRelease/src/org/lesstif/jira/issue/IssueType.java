package org.lesstif.jira.issue;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import org.lesstif.jira.JsonPrettyString;

import lombok.Data;

@Data
@JsonIgnoreProperties({"entityId","scope"})
public class IssueType extends JsonPrettyString {
	// Default issue type
	public static final String ISSUE_TYPE_EPIC = "Epic";
	public static final String ISSUE_TYPE_BUG = "Bug";
	public static final String ISSUE_TYPE_TASK = "Task";
	public static final String ISSUE_TYPE_IMPROVEMENT = "Improvement";
	public static final String ISSUE_TYPE_SUBTASK = "SubTask";
	public static final String ISSUE_TYPE_Story = "Story";
	public static final String ISSUE_TYPE_NEW_FEATURE = "New Feature";
	
	private String self;
	private String id;
	private String description;
	private String iconUrl;
	private String name;
	private Boolean subtask;
	private Integer avatarId;
	
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Boolean getSubtask() {
		return subtask;
	}
	public void setSubtask(Boolean subtask) {
		this.subtask = subtask;
	}
	public Integer getAvatarId() {
		return avatarId;
	}
	public void setAvatarId(Integer avatarId) {
		this.avatarId = avatarId;
	}
}
