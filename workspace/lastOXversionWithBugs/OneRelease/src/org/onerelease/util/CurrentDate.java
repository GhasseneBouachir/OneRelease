package org.onerelease.util;

import java.util.*;

import org.openxava.calculators.*;

public class CurrentDate implements ICalculator {

	@Override
	public Object calculate() throws Exception {
		return new Date();
	}

}
