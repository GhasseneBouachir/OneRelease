package org.onerelease.actions.ticket;

import java.util.*;

import org.openxava.actions.*;

public class EditUserStory extends EditElementInCollectionAction {

	
	@Override
	public void execute() throws Exception {
		super.execute();
		Map functionality = (Map) getCollectionElementView().getValue("functionality");
		if(functionality.get("functionalityID")!=null)
			{
				getCollectionElementView().setHidden("FunctionalityAnalysis", false);
				getCollectionElementView().setHidden("PeerReview", false);
			}
		else {
			getCollectionElementView().setHidden("FunctionalityAnalysis", true);
			getCollectionElementView().setHidden("PeerReview", true);
		}
		getCollectionElementView().refresh();
	}
}
