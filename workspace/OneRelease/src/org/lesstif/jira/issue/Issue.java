package org.lesstif.jira.issue;

import java.io.File;
import java.io.IOException;


import org.lesstif.jira.JsonPrettyString;

import lombok.Data;

@Data
//@XmlRootElement
public class Issue extends JsonPrettyString{
	

	private String expand;
	private String id;
	private String self;
	private String key;
	
	private IssueFields fields = new IssueFields();

	public Issue addAttachment(String filePath) throws IOException {		
		addAttachment(new File(filePath));
		
		return this;
	}
	
	public Issue addAttachment(File file) throws IOException {	
		fields.addAttachment(file);	
		
		return this;
	}

	/**
	 * check attachment exist 
	 * 
	 * @return boolean true: issue have attachment, false: no attachment
	 */
	public boolean hasAttachments() {
		if (fields.getFileList() != null && fields.getFileList().size() > 0)
			return true;
		
		return false;
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public IssueFields getFields() {
		return fields;
	}

	public void setFields(IssueFields fields) {
		this.fields = fields;
	}
       
        
}
