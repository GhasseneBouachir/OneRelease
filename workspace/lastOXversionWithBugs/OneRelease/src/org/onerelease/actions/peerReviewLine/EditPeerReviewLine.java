package org.onerelease.actions.peerReviewLine;

import java.util.*;

import org.onerelease.enumeration.*;
import org.openxava.actions.*;
import org.openxava.util.*;
import org.openxava.view.*;

public class EditPeerReviewLine extends EditElementInCollectionAction {
	
	@Override
	public void execute() throws Exception {
		Map peerReview =  (Map) getView().getAllValues().get("peerReview");
		super.execute();
		
		Map PeerReviewer = (Map) peerReview.get("peerReviewer");
		Map verifier = (Map) peerReview.get("verifier");
		Map author = (Map) peerReview.get("authorOfDelevirableReviewed");
		View defectIdentificationView = getCollectionElementView().getGroupView("DefectIdentification");
		getCollectionElementView().setEditable("verifierComments", true);
		getCollectionElementView().setEditable("verificationState",true);
		getCollectionElementView().setEditable("authorComments",true);
		getCollectionElementView().setEditable("correctionState",true);
		defectIdentificationView.setEditable(true);
		if(Users.getCurrent().equals(
				NewPeerReviewLine.getUserNameById((String)PeerReviewer.get("id"))))
			{
				getCollectionElementView().setEditable("verifierComments", false);
				getCollectionElementView().setEditable("verificationState",false);
				getCollectionElementView().setEditable("authorComments",false);
				getCollectionElementView().setEditable("correctionState",false);
			}
		else if(Users.getCurrent().equals(
				NewPeerReviewLine.getUserNameById((String)verifier.get("id"))))
			{	defectIdentificationView.setEditable(false);
				getCollectionElementView().setEditable("authorComments",false);
				getCollectionElementView().setEditable("correctionState",false);
			}
		else if(Users.getCurrent().equals(
				NewPeerReviewLine.getUserNameById((String)author.get("id")))) 
			{
				defectIdentificationView.setEditable(false);
				getCollectionElementView().setEditable("verifierComments", false);
				getCollectionElementView().setEditable("verificationState",false);
			     
			}
	}

//	collaboratorRole CurrentCollaboratorRole() {
//		// TODO Auto-generated method stub
//		return collaboratorRole.Manager;
//	}
	
	
	
	
}
