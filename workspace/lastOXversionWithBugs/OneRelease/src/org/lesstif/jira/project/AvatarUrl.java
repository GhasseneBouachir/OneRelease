package org.lesstif.jira.project;

import org.codehaus.jackson.annotate.JsonProperty;

import lombok.Data;

@Data
public class AvatarUrl {
	@JsonProperty("16x16")
	private String avatar16; 
	
	@JsonProperty("24x24")
	private String avatar24;
	
	@JsonProperty("32x32")
	private String avatar32;
	
	@JsonProperty("48x48")
	private String avatar48;

	public String getAvatar16() {
		return avatar16;
	}

	public void setAvatar16(String avatar16) {
		this.avatar16 = avatar16;
	}

	public String getAvatar24() {
		return avatar24;
	}

	public void setAvatar24(String avatar24) {
		this.avatar24 = avatar24;
	}

	public String getAvatar32() {
		return avatar32;
	}

	public void setAvatar32(String avatar32) {
		this.avatar32 = avatar32;
	}

	public String getAvatar48() {
		return avatar48;
	}

	public void setAvatar48(String avatar48) {
		this.avatar48 = avatar48;
	}
}
