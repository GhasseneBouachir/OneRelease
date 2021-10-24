package org.onerelease.actions.sprint;

import org.hibernate.loader.*;
import org.onerelease.model.*;
import org.openxava.actions.*;

public class OnChangeSquadAction extends OnChangePropertyBaseAction {
	
	@Override
	public void execute() throws Exception {
		if(getNewValue()!=null) {
		Squad s = (Squad)getNewValue();
		getView().setValue("scrumMaster", s.getScrumMaster());
		}
	}

}
