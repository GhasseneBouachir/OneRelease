package org.onerelease.actions;

import java.util.*;

import javax.inject.Inject;

import org.openxava.actions.*;
import org.openxava.view.*;
import org.openxava.view.meta.*;


public class CustomChangeSection extends ChangeSectionAction {
	
	@Inject
	private View view;
	
	@Override
	public void execute() throws Exception {
//		Functionality functionality = (Functionality) getView().getAllValues().get("functionality");
		// TODO Auto-generated method stub
		
//		if(getView().getActiveSection()==3)
//			{
//				getView().setActiveSection(1);
//				  addWarning("you have to specify the Functionality firstly", 55);
//			}
//		getView().setActiveSection(1);
		
		super.execute();
		MetaView metaView = (MetaView) getView().getSections().get(getView().getActiveSection());
		metaView.getName();
		if(metaView.getName().equalsIgnoreCase("PeerReview"))
			{
				Map businessAnalyst = (Map) getView().getValue("functionalityAnalysis.businessAnalyst");
				getView().setValue("peerReview.authorOfDelevirableReviewed", businessAnalyst);
			}
		}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}


		
}
