package org.onerelease.actions.ticket;

import java.math.*;
import java.util.*;

import org.openxava.actions.*;
import org.openxava.model.*;

public class MoveDown extends CollectionBaseAction {

	@Override
	public void execute() throws Exception {
		if(getRow()<getCollectionElementView().getCollectionSize()-1)
			{
			Map keysDOWN = null;
			Map keysUP = null;
			Map	valuesDOWN = null;
			Map	valuesUP = null;
			Collection elements;
			//getCollectionElementView().getCollectionObjects()
//			if (getCollectionElementView().isCollectionFromModel()) {		
				elements = getCollectionElementView().getCollectionValues();
				if (elements == null) return;
				if (elements instanceof List) {
					keysDOWN = (Map) ((List) elements).get(getRow());
					keysUP = (Map) ((List) elements).get(getRow()+1);
					
				}
//			}
//			else {
//				keysDOWN = (Map) getCollectionElementView().getCollectionTab().getTableModel().getObjectAt(getRow());
//				keysUP = (Map) getCollectionElementView().getCollectionTab().getTableModel().getObjectAt(getRow()+1);
//			}
			valuesDOWN =MapFacade.getValues(getCollectionElementView().getModelName(), keysDOWN, keysDOWN);
			valuesUP =MapFacade.getValues(getCollectionElementView().getModelName(), keysUP, keysDOWN);
				valuesDOWN.put("rank", BigInteger.valueOf(getRow()+1));
				valuesUP.put("rank", BigInteger.valueOf(getRow()));
				MapFacade.setValues(getCollectionElementView().getModelName(), valuesDOWN, valuesDOWN);
				MapFacade.setValues(getCollectionElementView().getModelName(), valuesUP, valuesUP);
				
			}
		getCollectionElementView().refreshCollections();
	}

}
