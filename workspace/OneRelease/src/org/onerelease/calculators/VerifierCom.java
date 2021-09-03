package org.onerelease.calculators;



import org.onerelease.model.*;
import org.openxava.actions.*;
import org.openxava.calculators.*;
import org.openxava.util.*;
import org.openxava.validators.*;
import org.openxava.view.*;

public class VerifierCom  implements ICalculator{
	
	private int peerReviewTest;

	 
	 
	 
	 
	 public Object calculate() throws Exception {
//		 getView().getAllValues();
	if(getPeerReviewTest() != 0) return "aloooo";
//	{
//	 Connection con = provider.getConnection();
//	 try {
//	 PreparedStatement ps = con.prepareStatement(
//	 "select count(*) from peerreviewline " +
//	"where peerreview_id = ? ");
//	 ps.setString(1, "");
//	 ResultSet rs = ps.executeQuery();
//	 rs.next();
//	 Long result = new Long(rs.getInt(1));
//	 ps.close();
//	 return result;
//	 }
//	 finally {
//	 con.close();
//	 }
//	 }
		 
	return null;
	 }





	public int getPeerReviewTest() {
		return peerReviewTest;
	}





	public void setPeerReviewTest(int peerReviewTest) {
		this.peerReviewTest = peerReviewTest;
	}

























//
//	@Override
//	public void setConnectionProvider(IConnectionProvider provider) {
//		// TODO Auto-generated method stub
//		this.provider = provider;
//	}
















}
