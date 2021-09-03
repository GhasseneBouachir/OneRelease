package org.onerelease.actions.sprint;

import javax.persistence.*;

import org.onerelease.enumeration.*;
import org.openxava.actions.*;
import org.openxava.jpa.*;

public class EditSprint extends EditElementInCollectionAction {

	@Override
	public void execute() throws Exception {
		super.execute();
		if (getCollectionElementView().getValueString("status").equals("activated")) {
			removeActions("Sprint.startSprint");
		} else if (getCollectionElementView().getValueString("status").equals("planned")) {
			if (existActiveSprint()) {
				removeActions("Sprint.startSprint");
			}
			removeActions("Sprint.completeSprint");
		} else
			removeActions("Sprint.startSprint", "Sprint.completeSprint");
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
