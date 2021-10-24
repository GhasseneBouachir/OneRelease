package org.onerelease.actions.ticket;

import java.math.*;
import java.util.*;

import org.hibernate.boot.model.naming.*;
import org.openxava.actions.*;
import org.openxava.model.*;

public class MoveUp extends CollectionBaseAction {

	@Override
	public void execute() throws Exception {
		Map keysUP = null;
		Map keysDown = null;
		Map	valuesUP = null;
		Map	valuesDown = null;
		Collection elements;
		if(getRow()>0) {
//		if (getCollectionElementView().isCollectionFromModel()) {		
			elements = getCollectionElementView().getCollectionValues();
			if (elements == null) return;
			if (elements instanceof List) {
				keysUP = (Map) ((List) elements).get(getRow());
				keysDown = (Map) ((List) elements).get(getRow()-1);
			}
//		}
//		else {
//			keysUP = (Map) getCollectionElementView().getCollectionTab().getTableModel().getObjectAt(getRow());
//			keysDown = (Map) getCollectionElementView().getCollectionTab().getTableModel().getObjectAt(getRow()-1);
//		}
		valuesUP =MapFacade.getValues(getCollectionElementView().getModelName(), keysUP, keysUP);
		
		valuesDown =MapFacade.getValues(getCollectionElementView().getModelName(), keysDown, keysDown);
			valuesUP.put("rank", BigInteger.valueOf(getRow()-1));
			valuesDown.put("rank", BigInteger.valueOf(getRow()));
			MapFacade.setValues(getCollectionElementView().getModelName(), valuesUP, valuesUP);
			MapFacade.setValues(getCollectionElementView().getModelName(), valuesDown, valuesDown);
			
		}
		//MapFacade.getValues(getCollectionElementView().getModelName(), first, getCollectionElementView().getMembersNames());
//		getCollectionElementView().clear(); 
//		addMessage(getCollectionElementView().getCollectionTab().getOrderBy(), 2);
		getCollectionElementView().refreshCollections();
//		getCollectionElementView().reset();
//		getView().refresh();
		
	}


}
