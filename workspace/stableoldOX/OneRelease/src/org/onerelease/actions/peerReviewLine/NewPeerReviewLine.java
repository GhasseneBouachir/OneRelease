package org.onerelease.actions.peerReviewLine;

import java.math.*;
import java.util.*;

import org.onerelease.model.*;
import org.openxava.actions.*;
import org.openxava.jpa.*;
import org.openxava.model.meta.*;
import org.openxava.util.*;
import org.openxava.view.*;


public class NewPeerReviewLine extends CreateNewElementInCollectionAction  {

	
	
	
	
	@Override
	public void execute() throws Exception {
		Map peerReview =  (Map) getView().getAllValues().get("peerReview");
		Map PeerReviewer = (Map) peerReview.get("peerReviwer");
		Map verifier = (Map) peerReview.get("verifier");
		Map author = (Map) peerReview.get("authorOfDelevirableReviewed");
		if(PeerReviewer.get("id").toString().isEmpty() || verifier.get("id").toString().isEmpty() 
				|| author.get("id") ==null)
			{	if(author.get("id") == null)
				addWarning("you have to feel the author ");
				if(PeerReviewer.get("id").toString().isEmpty()) 
				addWarning("you have to feel the peerReview");	
				if(verifier.get("id").toString().isEmpty()) 
				addWarning("you have to feel the verifier ");
				
			}
		else {
			super.execute();
			
			getCollectionElementView().setValue("idLog", getNumberOfPeerReviewLines((String) peerReview.get("id")) + 1);
			View defectIdentificationView = getCollectionElementView().getGroupView("DefectIdentification");
			getCollectionElementView().setEditable("verifierComments", true);
			getCollectionElementView().setEditable("verificationState",true);
			getCollectionElementView().setEditable("authorComments",true);
			getCollectionElementView().setEditable("correctionState",true);
			defectIdentificationView.setEditable(true);
			if(Users.getCurrent().equals(
					getUserNameById((String)PeerReviewer.get("id"))))
				{
					getCollectionElementView().setEditable("verifierComments", false);
					getCollectionElementView().setEditable("verificationState",false);
					getCollectionElementView().setEditable("authorComments",false);
					getCollectionElementView().setEditable("correctionState",false);
				}
			else if(Users.getCurrent().equals(
				getUserNameById((String)verifier.get("id"))))
				{	defectIdentificationView.setEditable(false);
					getCollectionElementView().setEditable("authorComments",false);
					getCollectionElementView().setEditable("correctionState",false);
				}
			else if(Users.getCurrent().equals(
					getUserNameById((String)author.get("id")))) 
				{
					defectIdentificationView.setEditable(false);
					getCollectionElementView().setEditable("verifierComments", false);
					getCollectionElementView().setEditable("verificationState",false);
				     
				}
			}
		}


	private int getNumberOfPeerReviewLines(String peerReviewId) {
		 BigInteger n =  (BigInteger) XPersistence.getManager().createNativeQuery(
				"select count(*) from PeerReviewLine as pl "
				+ "where pl.peerreview_id = '" + peerReviewId +"' ").getSingleResult();
		 return n.intValueExact();
	}
	
	
	
	static String getUserNameById(String collaboratorId){
		Collaborator current = XPersistence.getManager().find(Collaborator.class, collaboratorId);
		return current.getFirstName();
		
	}
	


	
	
}
