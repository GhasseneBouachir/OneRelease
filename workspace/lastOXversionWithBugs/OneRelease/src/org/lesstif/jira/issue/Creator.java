package org.lesstif.jira.issue;

public class Creator {
	 private String self;
	 private String name;
	 private String key;
	 private String accountId;
	 private String emailAddress;
	 private String displayName;
	 private boolean active;
	 private String timeZone;
	 private String accountType;


	 // Getter Methods 

	 public String getSelf() {
	  return self;
	 }

	 public String getName() {
	  return name;
	 }

	 public String getKey() {
	  return key;
	 }

	 public String getAccountId() {
	  return accountId;
	 }

	 public String getEmailAddress() {
	  return emailAddress;
	 }

	 public String getDisplayName() {
	  return displayName;
	 }

	 public boolean getActive() {
	  return active;
	 }

	 public String getTimeZone() {
	  return timeZone;
	 }

	 public String getAccountType() {
	  return accountType;
	 }

	 // Setter Methods 

	 public void setSelf(String self) {
	  this.self = self;
	 }

	 public void setName(String name) {
	  this.name = name;
	 }

	 public void setKey(String key) {
	  this.key = key;
	 }

	 public void setAccountId(String accountId) {
	  this.accountId = accountId;
	 }

	 public void setEmailAddress(String emailAddress) {
	  this.emailAddress = emailAddress;
	 }

	 public void setDisplayName(String displayName) {
	  this.displayName = displayName;
	 }

	 public void setActive(boolean active) {
	  this.active = active;
	 }

	 public void setTimeZone(String timeZone) {
	  this.timeZone = timeZone;
	 }

	 public void setAccountType(String accountType) {
	  this.accountType = accountType;
	 }
	}