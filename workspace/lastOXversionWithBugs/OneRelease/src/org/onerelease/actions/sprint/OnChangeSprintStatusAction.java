package org.onerelease.actions.sprint;

import javax.persistence.*;

import org.onerelease.enumeration.*;
import org.openxava.actions.*;
import org.openxava.jpa.*;

public class OnChangeSprintStatusAction extends OnChangePropertyBaseAction{

	@Override
	public void execute() throws Exception {
		if(getNewValue() != null)
		{
		if(getNewValue().toString().equalsIgnoreCase("activated"))
		{
			
				try {
					 Query query = XPersistence.getManager().createQuery(
							 "from Sprint as s where s.status = 1 ");
							 query.getSingleResult();
							 addInfo("there is already an activated sprint", 4);
							 setNewValue("Planned");
							 getView().refresh();
					}
					catch(NoResultException e) {
						
					}
				
				
		}
		}
	}

}
