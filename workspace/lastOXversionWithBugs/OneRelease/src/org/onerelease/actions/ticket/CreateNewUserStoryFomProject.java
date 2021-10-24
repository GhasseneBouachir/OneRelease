package org.onerelease.actions.ticket;

import org.openxava.actions.*;

public class CreateNewUserStoryFomProject extends CreateNewElementInCollectionAction {
	
	
	@Override
	public void execute() throws Exception {
		super.execute();
		getCollectionElementView().setHidden("FunctionalityAnalysis",true);
		getCollectionElementView().setHidden("PeerReview",true);
	}
	

	
}
