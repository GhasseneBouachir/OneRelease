package org.onerelease.calculators;

import org.openxava.actions.*;
import org.openxava.calculators.*;

public class NewTestCalculator implements ICalculator  {

	
	private int newTest;
	
	
	@Override
	public Object calculate() throws Exception {
		// TODO Auto-generated method stub
		return getNewTest();
	}


	public int getNewTest() {
		return newTest;
	}


	public void setNewTest(int newTest) {
		this.newTest = newTest;
	}
	
	

}
