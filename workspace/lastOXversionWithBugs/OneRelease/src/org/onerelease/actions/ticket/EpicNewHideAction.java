package org.onerelease.actions.ticket;

import org.openxava.actions.*;

public class EpicNewHideAction extends CreateNewElementInCollectionAction {

	public void execute() throws Exception {
		super.execute();
 		getCollectionElementView().setHidden("reference", true);
 		getCollectionElementView().setHidden("rank", true);
	}

}
