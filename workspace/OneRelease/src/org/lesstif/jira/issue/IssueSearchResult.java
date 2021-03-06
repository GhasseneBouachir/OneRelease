package org.lesstif.jira.issue;

import org.lesstif.jira.JsonPrettyString;
import java.util.List;
import lombok.Data;

@Data
public class IssueSearchResult extends JsonPrettyString{
    
  private String expand;
  private Integer startAt;
  private Integer maxResults;
  private Integer total;
  private List<Issue> issues;
  
public String getExpand() {
	return expand;
}
public void setExpand(String expand) {
	this.expand = expand;
}
public Integer getStartAt() {
	return startAt;
}
public void setStartAt(Integer startAt) {
	this.startAt = startAt;
}
public Integer getMaxResults() {
	return maxResults;
}
public void setMaxResults(Integer maxResults) {
	this.maxResults = maxResults;
}
public Integer getTotal() {
	return total;
}
public void setTotal(Integer total) {
	this.total = total;
}
public List<Issue> getIssues() {
	return issues;
}
public void setIssues(List<Issue> issues) {
	this.issues = issues;
}
  
  
}
