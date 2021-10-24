package org.onerelease.actions.peerReviewLine;

import org.openxava.actions.*;

public class SavePeerReviewLine extends SaveElementInCollectionAction {
	
	@Override
	public void execute() throws Exception {
		
		super.execute();
		getCollectionElementView().refresh();
	}

}
