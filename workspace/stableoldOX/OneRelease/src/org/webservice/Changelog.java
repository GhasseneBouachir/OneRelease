package org.webservice;

import java.util.*;

public class Changelog {
	 private String id;
	 ArrayList < Modification > items = new ArrayList < Modification > ();


	 // Getter Methods 

	 public String getId() {
	  return id;
	 }

	 // Setter Methods 

	 public void setId(String id) {
	  this.id = id;
	 }

	public ArrayList<Modification> getItems() {
		return items;
	}

	public void setItems(ArrayList<Modification> items) {
		this.items = items;
	}
	}