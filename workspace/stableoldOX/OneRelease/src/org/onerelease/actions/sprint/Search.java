package org.onerelease.actions.sprint;

import javax.persistence.*;

import org.onerelease.enumeration.*;
import org.openxava.actions.*;
import org.openxava.jpa.*;

public class Search extends SearchByViewKeyAction {
	
	
	public void execute() throws Exception { 

		super.execute();
		if (getView().getValueString("status").equals("activated")) {
			removeActions("Sprint.startSprint");
		} else if(getView().getValueString("status").equals("planned")) {
			if (existActiveSprint()) {
				removeActions("Sprint.startSprint");
			}
			removeActions("Sprint.completeSprint");
		} else
			removeActions("Sprint.startSprint", "Sprint.completeSprint");
    }
 
    public String getNextAction() throws Exception {
        return getEnvironment() // To access an environment variables
            .getValue("XAVA_SEARCH_ACTION");
    }
	
	private boolean existActiveSprint() {
		try {
		 Query query = XPersistence.getManager().createQuery(
				 "from Sprint as s where s.status = :status ");
				 query.setParameter("status", SprintStatus.activated);
				 query.getSingleResult();
				 return true;
		}
		catch(NoResultException e) {
			return false;
		}	
	}
}
