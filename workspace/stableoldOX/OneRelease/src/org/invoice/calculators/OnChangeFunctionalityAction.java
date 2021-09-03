package org.invoice.calculators;

import org.openxava.actions.*;

public class OnChangeFunctionalityAction extends OnChangePropertyBaseAction {

	@Override
	public void execute() throws Exception {
		 getView().setValue("complexFactoryCharge", 1);
	}

}
