package org.lesstif.jira.issue;

import lombok.Data;

import org.joda.time.DateTime;

import org.lesstif.jira.JsonPrettyString;

@Data
/**
 * @see https://docs.atlassian.com/jira/REST/latest/#d2e4213
 * 
 * @author lesstif
 *
 */
public class Attachment extends JsonPrettyString{	
	private String id;
	private String self;
	private String filename;
	private Reporter author;
	
	private DateTime created;
	
	private Integer size;
	private String mimeType;	
	private String content;
	private String thumbnail;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Reporter getAuthor() {
		return author;
	}
	public void setAuthor(Reporter author) {
		this.author = author;
	}
	public DateTime getCreated() {
		return created;
	}
	public void setCreated(DateTime created) {
		this.created = created;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}
