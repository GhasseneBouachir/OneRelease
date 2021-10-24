package org.onerelease.actions.suqad;

import org.onerelease.actions.*;

public class SquadEditHideAction extends CustomEditElementInCollectionAction {

	public void execute() throws Exception {
		super.execute();
 		getCollectionElementView().setHidden("reference", false);
	}

}
