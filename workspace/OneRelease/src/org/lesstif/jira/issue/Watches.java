package org.lesstif.jira.issue;

public class Watches {
	 private String self;
	 private float watchCount;
	 private boolean isWatching;


	 // Getter Methods 

	 public String getSelf() {
	  return self;
	 }

	 public float getWatchCount() {
	  return watchCount;
	 }

	 public boolean getIsWatching() {
	  return isWatching;
	 }

	 // Setter Methods 

	 public void setSelf(String self) {
	  this.self = self;
	 }

	 public void setWatchCount(float watchCount) {
	  this.watchCount = watchCount;
	 }

	 public void setIsWatching(boolean isWatching) {
	  this.isWatching = isWatching;
	 }
	}