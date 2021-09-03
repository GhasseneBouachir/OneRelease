package org.onerelease.actions.ticket;

import java.util.*;

import org.openxava.actions.*;

public class AssignAction extends CollectionBaseAction {
		private static String ticketId;
	@Override
	public void execute() throws Exception {
		//ticketId = (String) getCollectionElementView().getCollectionValues().get(getRow()).get("id");
		ticketId = (String) getCollectionElementView().getCollectionTab().getAllKeys()[getRow()].get("id");
		showDialog(); // 2
		
		 getView().setTitle("enter the sprint and the milestone"); 
		 getView().setModelName("AssignTicketToSprint"); 
		 //setControllers("AddFullAddress", "Dialog");
		addActions("addTicketToSprint.AddTicketToSprintAction","Dialog.cancel");
	}
	
	public static String getTicketId() {return ticketId;}

}
