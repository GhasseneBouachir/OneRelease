package org.onerelease.actions.ticket;

import java.util.*;

import javax.persistence.*;

import org.onerelease.model.*;
import org.openxava.actions.*;
import org.openxava.jpa.*;



public class ChangeFunctionality extends OnChangePropertyBaseAction {
	

	
    public void execute() throws Exception {
//    	Map functionality = (Map) getView().getAllValues().get("functionality");
//    	if(functionality.get("functionalityID")!=null)
//    			{
//    				getView().setValue("functionalityAnalysis.functionalitySystem",
//    							getFunctioalitySystem((Integer)functionality.get("functionalityID")));
//    				getView().setHidden("FunctionalityAnalysis", false);
//    			}
//    	else getView().setHidden("FunctionalityAnalysis", true);
    	Integer functionality = (Integer) getNewValue();
//    	 Map functionality = (Map) getView().getAllValues().get("functionality");
         if(functionality != null)
         {  getView().setHidden("FunctionalityAnalysis", false);
         	getView().setHidden("PeerReview", false);
         	getView().setValue("functionalityAnalysis.functionalitySystem",
					getFunctioalitySystem(functionality));
         }
//		 else getView().setHidden("FunctionalityAnalysis", true);
         
    }

	
    
    
    
	private String getFunctioalitySystem(Integer id) {
		try{
			Query query = XPersistence.getManager().createQuery(
		
 				"from FunctionalityAnalysis as fa where fa.functionality.id = :id "
 				+ " order by fa.id desc");
 				 query.setParameter("id", id);
 				 query.setMaxResults(1);
 				 FunctionalityAnalysis functionalityAnalysis = (FunctionalityAnalysis) query.getResultList().get(0);
		return functionalityAnalysis.getFunctionalitySystem();
		}
		catch(IndexOutOfBoundsException e)
			{
			return null;
			}
	
		}

	
	
}