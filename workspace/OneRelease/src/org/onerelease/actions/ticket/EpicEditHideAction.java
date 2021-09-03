package org.onerelease.actions.ticket;

import org.onerelease.actions.*;

public class EpicEditHideAction extends CustomEditElementInCollectionAction {

	public void execute() throws Exception {
		super.execute();
 		getCollectionElementView().setHidden("reference", false);
 		getCollectionElementView().setHidden("rank", false);
 		getCollectionElementView().refresh();
	}

}
