package org.lesstif.jira.sprint;

import org.codehaus.jackson.annotate.*;
import org.lesstif.jira.*;

import lombok.*;

@Data
@JsonIgnoreProperties()
public class JiraSprint extends JsonPrettyString {
	
	private String self;
	private String id;
	private String state;
	private String name;
	private String startDate;
	private String endDate;
	private String originBoardId;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getOriginBoardId() {
		return originBoardId;
	}
	public void setOriginBoardId(String originBoardId) {
		this.originBoardId = originBoardId;
	}
	
	
}
