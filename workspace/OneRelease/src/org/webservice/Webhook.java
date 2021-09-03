package org.webservice;

import org.lesstif.jira.issue.*;

public class Webhook {
	 private float timestamp;
	 private String webhookEvent;
	 private String issue_event_type_name;
	 User UserObject;
	 Issue IssueObject;
	 Changelog ChangelogObject;


	 // Getter Methods 

	 public float getTimestamp() {
	  return timestamp;
	 }

	 public String getWebhookEvent() {
	  return webhookEvent;
	 }

	 public String getIssue_event_type_name() {
	  return issue_event_type_name;
	 }

	 public User getUser() {
	  return UserObject;
	 }

	 public Issue getIssue() {
	  return IssueObject;
	 }

	 public Changelog getChangelog() {
	  return ChangelogObject;
	 }

	 // Setter Methods 

	 public void setTimestamp(float timestamp) {
	  this.timestamp = timestamp;
	 }

	 public void setWebhookEvent(String webhookEvent) {
	  this.webhookEvent = webhookEvent;
	 }

	 public void setIssue_event_type_name(String issue_event_type_name) {
	  this.issue_event_type_name = issue_event_type_name;
	 }

	 public void setUser(User userObject) {
	  this.UserObject = userObject;
	 }

	 public void setIssue(Issue issueObject) {
	  this.IssueObject = issueObject;
	 }

	 public void setChangelog(Changelog changelogObject) {
	  this.ChangelogObject = changelogObject;
	 }
	}