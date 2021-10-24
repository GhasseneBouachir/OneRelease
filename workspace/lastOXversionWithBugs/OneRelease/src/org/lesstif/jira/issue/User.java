package org.lesstif.jira.issue;

import org.codehaus.jackson.annotate.*;
import org.lesstif.jira.project.*;

@JsonIgnoreProperties({"avatarUrls","locale","groups","applicationRoles","expand"
})
public class User {
	 private String self;
	 private String accountId;
	 private String emailAddress;
	 //AvatarUrl avatarUrls;
	 private String displayName;
	 private boolean active;
	 private String timeZone;
	 private String accountType;


	 // Getter Methods 

	 public String getSelf() {
	  return self;
	 }

	 public String getAccountId() {
	  return accountId;
	 }

	 public String getEmailAddress() {
	  return emailAddress;
	 }

//	 public AvatarUrl getAvatarUrls() {
//	  return avatarUrls;
//	 }

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

	 public void setAccountId(String accountId) {
	  this.accountId = accountId;
	 }

	 public void setEmailAddress(String emailAddress) {
	  this.emailAddress = emailAddress;
	 }

//	 public void setAvatarUrls(AvatarUrl avatarUrls) {
//	  this.avatarUrls = avatarUrls;
//	 }

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