package org.webservice;

import org.codehaus.jackson.annotate.*;


@JsonIgnoreProperties({"tmpFromAccountId","tmpToAccountId"
})
public class Modification {
	private String field;
	 private String fieldtype;
	 private String fieldId;
	 private String from;
	 private String fromString;
	 private String to;
	 private String toString;


	 // Getter Methods 

	 public String getField() {
	  return field;
	 }

	 public String getFieldtype() {
	  return fieldtype;
	 }

	 public String getFieldId() {
	  return fieldId;
	 }

	 public String getFrom() {
	  return from;
	 }

	 public String getFromString() {
	  return fromString;
	 }

	 public String getTo() {
	  return to;
	 }

	 public String getToString() {
	  return toString;
	 }

	 // Setter Methods 

	 public void setField(String field) {
	  this.field = field;
	 }

	 public void setFieldtype(String fieldtype) {
	  this.fieldtype = fieldtype;
	 }

	 public void setFieldId(String fieldId) {
	  this.fieldId = fieldId;
	 }

	 public void setFrom(String from) {
	  this.from = from;
	 }

	 public void setFromString(String fromString) {
	  this.fromString = fromString;
	 }

	 public void setTo(String to) {
	  this.to = to;
	 }

	 public void setToString(String toString) {
	  this.toString = toString;
	 }
}
