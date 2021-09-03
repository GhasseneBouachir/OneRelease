package org.onerelease.actions.suqad;

import org.openxava.actions.*;

public class SquadNewHideAction extends CreateNewElementInCollectionAction {

	public void execute() throws Exception {
		super.execute();
 		getCollectionElementView().setHidden("reference", true);
	}

}
