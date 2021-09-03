package org.onerelease.calculators;

import org.onerelease.enumeration.*;
import org.openxava.calculators.*;

public class TicketBySprintStatusCalculator implements ICalculator {

	@Override
	public Object calculate() throws Exception {
		
		return TicketBySprintStatus.initiated;
	}

}
