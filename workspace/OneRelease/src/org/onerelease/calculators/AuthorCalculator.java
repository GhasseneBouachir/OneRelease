package org.onerelease.calculators;

import org.openxava.actions.*;
import org.openxava.calculators.*;

public class AuthorCalculator implements ICalculator{
	
	
	private String id;
	
	@Override
	public Object calculate() throws Exception {
		return id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
}
