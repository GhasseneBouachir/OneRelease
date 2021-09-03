package org.lesstif.jira.issue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.experimental.Accessors;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.DateTime;

import org.lesstif.jira.project.Project;

@Data
@JsonIgnoreProperties({"lastViewed", "aggregateprogress"
,"timeoriginalestimate", "aggregatetimespent",
"fileList"
})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Accessors(chain=true)
public class IssueFields {
	private Project  project;
	private String summary;
	
	private Map<String, String> progress;
	
	private TimeTracking timetracking;
	private IssueType issuetype;
	
	private String timespent;
	
	private Reporter reporter;
	
	private DateTime created;
	private DateTime updated;
	
	private String description;
	
	private Priority priority;
	
	private String[] issuelinks;
	
	// for custom field	
	private Map<String,Object> customfield = new HashMap<String, Object>();
	
	@JsonAnyGetter
	public Map<String,Object> getCustomfield(){
		return this.customfield; 
	}
	
	@JsonAnySetter
	public IssueFields setCustomfield(String key,Object value){ 
		if (this.customfield == null)
			this.customfield = new HashMap<String, Object>();
		
		this.customfield.put(key, value);
		
		return this;
	}
		
	private String[] subtasks;
	private Status status;
	
	private String[] labels;
	
	private Integer workratio;
	
	private String environment;
	
	private List<Component> components;
	
	private Comment comment;
	
	private Vote votes;
	
	private Resolution resolution;
	
	private String[] fixVersions;
	private DateTime resolutiondate;
	
	private Reporter creator;
	
	private DateTime aggregatetimeoriginalestimate;
	private DateTime duedate;
	
	private Map<String, String> watches;

	private Worklog worklog;
	
	private Reporter assignee;
	
	private List<Attachment> attachment;
	
	private	List<File>	fileList;
	
	private DateTime aggregatetimeestimate;
	
	private List<Version> versions;
	
	private Integer timeestimate;
	
	// Helper method
	public IssueFields setProjectId(String id) {
		if (project == null)
			project = new Project();
		
		project.setId(id);
		
		return this;
	}
	
	public IssueFields setProjectKey(String key) {
		if (project == null)
			project = new Project();
		
		project.setKey(key);
		
		return this;
	}
	
	public IssueFields setIssueTypeId(String id) {
		if (issuetype == null)
			issuetype = new IssueType();
		issuetype.setId(id);
		
		return this;
	}
	
	public IssueFields setIssueTypeName(String name) {
		if (issuetype == null)
			issuetype = new IssueType();
		issuetype.setName(name);
		
		return this;
	}
	
	public IssueFields setAssigneeName(String name) {
		if (assignee == null)
			assignee = new Reporter();

		assignee.setName(name);
		
		return this;
	}
	
	public IssueFields setReporterName(String name) {
		if (reporter == null)
			reporter = new Reporter();

		reporter.setName(name);
		return this;
	}

	public IssueFields setPriorityId(String id) {
		if (priority == null)
			priority = new Priority();

		priority.setId(id);
		
		return this;		
	}
	
	public IssueFields setPriorityName(String name) {
		if (priority == null)
			priority = new Priority();

		priority.setName(name);
		
		return this;
	}

	public IssueFields addAttachment(String filePath) {
		addAttachment(new File(filePath));

		return this;
	}

	public IssueFields addAttachment(File file) {			
		if (this.fileList == null)
			this.fileList = new ArrayList<File>();
			
		fileList.add(file);
		
		return this;
	}
	
	public IssueFields setComponentsByStringArray(String[] componenArray) {
	    
	    if (componenArray == null || componenArray.length == 0)
	        return this;
	    
	    Component[] c = new Component[componenArray.length];
	    
	    for(int i = 0; i < componenArray.length; i++) {
	        c[i] = new Component(componenArray[i]);
	    }
	    
	    this.components = Arrays.asList(c);
	    
	    return this;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Map<String, String> getProgress() {
		return progress;
	}

	public void setProgress(Map<String, String> progress) {
		this.progress = progress;
	}

	public TimeTracking getTimetracking() {
		return timetracking;
	}

	public void setTimetracking(TimeTracking timetracking) {
		this.timetracking = timetracking;
	}

	public IssueType getIssuetype() {
		return issuetype;
	}

	public void setIssuetype(IssueType issuetype) {
		this.issuetype = issuetype;
	}

	public String getTimespent() {
		return timespent;
	}

	public void setTimespent(String timespent) {
		this.timespent = timespent;
	}

	public Reporter getReporter() {
		return reporter;
	}

	public void setReporter(Reporter reporter) {
		this.reporter = reporter;
	}

	public DateTime getCreated() {
		return created;
	}

	public void setCreated(DateTime created) {
		this.created = created;
	}

	public DateTime getUpdated() {
		return updated;
	}

	public void setUpdated(DateTime updated) {
		this.updated = updated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String[] getIssuelinks() {
		return issuelinks;
	}

	public void setIssuelinks(String[] issuelinks) {
		this.issuelinks = issuelinks;
	}

	public String[] getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(String[] subtasks) {
		this.subtasks = subtasks;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public Integer getWorkratio() {
		return workratio;
	}

	public void setWorkratio(Integer workratio) {
		this.workratio = workratio;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Vote getVotes() {
		return votes;
	}

	public void setVotes(Vote votes) {
		this.votes = votes;
	}

	public Resolution getResolution() {
		return resolution;
	}

	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	public String[] getFixVersions() {
		return fixVersions;
	}

	public void setFixVersions(String[] fixVersions) {
		this.fixVersions = fixVersions;
	}

	public DateTime getResolutiondate() {
		return resolutiondate;
	}

	public void setResolutiondate(DateTime resolutiondate) {
		this.resolutiondate = resolutiondate;
	}

	public Reporter getCreator() {
		return creator;
	}

	public void setCreator(Reporter creator) {
		this.creator = creator;
	}

	public DateTime getAggregatetimeoriginalestimate() {
		return aggregatetimeoriginalestimate;
	}

	public void setAggregatetimeoriginalestimate(DateTime aggregatetimeoriginalestimate) {
		this.aggregatetimeoriginalestimate = aggregatetimeoriginalestimate;
	}

	public DateTime getDuedate() {
		return duedate;
	}

	public void setDuedate(DateTime duedate) {
		this.duedate = duedate;
	}

	public Map<String, String> getWatches() {
		return watches;
	}

	public void setWatches(Map<String, String> watches) {
		this.watches = watches;
	}

	public Worklog getWorklog() {
		return worklog;
	}

	public void setWorklog(Worklog worklog) {
		this.worklog = worklog;
	}

	public Reporter getAssignee() {
		return assignee;
	}

	public void setAssignee(Reporter assignee) {
		this.assignee = assignee;
	}

	public List<Attachment> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<Attachment> attachment) {
		this.attachment = attachment;
	}

	public List<File> getFileList() {
		return fileList;
	}

	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}

	public DateTime getAggregatetimeestimate() {
		return aggregatetimeestimate;
	}

	public void setAggregatetimeestimate(DateTime aggregatetimeestimate) {
		this.aggregatetimeestimate = aggregatetimeestimate;
	}

	public List<Version> getVersions() {
		return versions;
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}

	public Integer getTimeestimate() {
		return timeestimate;
	}

	public void setTimeestimate(Integer timeestimate) {
		this.timeestimate = timeestimate;
	}

	public void setCustomfield(Map<String, Object> customfield) {
		this.customfield = customfield;
	}
	
}
