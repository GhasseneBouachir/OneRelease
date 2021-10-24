package org.onerelease.actions.FunctionalityAnalysis;

import org.apache.commons.validator.*;
import org.onerelease.model.*;
import org.openxava.actions.*;
import org.openxava.calculators.*;
import org.openxava.web.dwr.*;

public class createFuncionalityAnalysis  extends CreateNewElementInCollectionAction
	implements ICalculator {
	
	private static String US_ID;
	
	@Override
	public Object calculate() throws Exception {
		return US_ID;
	}

	@Override
	public void execute() throws Exception {
		if(getPreviousView().getAllValues().get("id") != null)
		US_ID = getPreviousView().getAllValues().get("id").toString();
		super.execute();
	}

}
