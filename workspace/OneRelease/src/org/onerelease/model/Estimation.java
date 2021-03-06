package org.onerelease.model;

import java.math.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.model.*;
import org.onerelease.enumeration.*;

import com.sun.org.apache.bcel.internal.generic.*;

@Entity
@Views({@View(members = "Details [estimationType; computed [Analysis [computedAnalysis;] "
		+ "Functional Tests [computedFunctionalTests; " + 
		"computedPrepareFunctionalTest; " +
		"computedImplementAndRunTest; " +
		"computedPeerReview; ]" +
		"User Acceptance Tests [computedUserAcceptanceTest; " +
		"computedDependenciesManagement;] " +
		"Rollout [computedRollout;] " +
		"Total [computedTotal]] " +
		"adjusted [Analysis2 [adjustedAnalysis;] FunctionalTests2 [adjustedFunctionalTests; "
		+ "adjustedPrepareFunctionalTest;"
		+ "adjustedImplementAndRunTest; adjustedPeerReview;] UserAcceptanceTests2 [ "
		+ "adjustedUserAcceptanceTest;"
		+ "adjustedDependenciesManagement;] Rollout2 [ adjustedRollout;]"
		+ "Total2 [ adjustedTotal;] ]] ; "
        + "EstimationLines { estimationLines } "),
    @View(name="EstimationUnderTicket", members = "Details [estimationType; computedAnalysis, "
    		+ "adjustedAnalysis;"
    		+ "computedFunctionalTests, adjustedFunctionalTests] ;"
            + " EstimationLines { estimationLines } ")})

public class Estimation extends Identifiable {
	
    @Column
    @Required
    private EstimationType estimationType;

    @ElementCollection @OrderColumn
//    @ListProperties("functionality.functionalityName, functionality.descriptionFunctionality, functionality.functionType ," + 
//            " estimationLineType, quantity, complexFactoryCharge, estimated"
////            + "[ estimation.total ]"
//            + ""
//            )
    private List<EstimationLine> estimationLines;

    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    private Ticket ticket;
    
    @Column
    private BigDecimal adjustedAnalysis;
    
    @Column
    private BigDecimal adjustedFunctionalTests;
    
    @Column
    private BigDecimal adjustedPrepareFunctionalTest;
    
    @Column
    private BigDecimal adjustedImplementAndRunTest;
    
    @Column
    private BigDecimal adjustedPeerReview;
    
    @Column
    private BigDecimal adjustedUserAcceptanceTest;
    
    @Column
    private BigDecimal adjustedDependenciesManagement;

    @Column
    private BigDecimal adjustedRollout;
    
    @Column
    private BigDecimal adjustedTotal;
    
    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public EstimationType getEstimationType() {
        return estimationType;
    }

    public void setEstimationType(EstimationType estimationType) {
        this.estimationType = estimationType;
    }

    public List<EstimationLine> getEstimationLines() {
        return estimationLines;
    }

    public void setEstimationLines(List<EstimationLine> estimationLines) {
        this.estimationLines = estimationLines;
    }
    
//    public BigDecimal getTotal() { 
//		BigDecimal sum = BigDecimal.ZERO;
//		for (EstimationLine estimationLine: this.getEstimationLines()) {
//			sum = sum.add(estimationLine.getEstimated());
//		}
//		return sum;
//	}
    
//    @ReadOnly
//    public BigDecimal getComputedAnalysis() {
//    	return this.getTotal();
//    }
//    
//    @ReadOnly
//    public BigDecimal getComputedFunctionalTests() {
//    	return (this.getTotal().multiply(new BigDecimal("50.00"))).divide(new BigDecimal("39.00"),2,BigDecimal.ROUND_HALF_EVEN);
//    }
//    @ReadOnly
//    public BigDecimal getComputedPrepareFunctionalTest() {
//    	return (this.getComputedFunctionalTests().multiply(new BigDecimal("25.00"))).divide(new BigDecimal("100.00"),2,BigDecimal.ROUND_HALF_EVEN);
//    }
//    
//    @ReadOnly
//    public BigDecimal getComputedImplementAndRunTest() {
//    	return (this.getComputedFunctionalTests().multiply(new BigDecimal("75.00"))).divide(new BigDecimal("100.00"),2,BigDecimal.ROUND_HALF_EVEN);
//    }
    
    @ReadOnly
    public BigDecimal getComputedPeerReview() {
    	return BigDecimal.ZERO;
    }
    
//    @ReadOnly
//    public BigDecimal getComputedUserAcceptanceTest() {
//    	return (this.getTotal().multiply(new BigDecimal("3.00"))).divide(new BigDecimal("30.00"),2,BigDecimal.ROUND_HALF_EVEN);
//    }
//    
//    @ReadOnly
//    public BigDecimal getComputedDependenciesManagement() {
//    	return BigDecimal.ZERO;
//    }
//    
//    @ReadOnly
//    public BigDecimal getComputedRollout() {
//    	return (this.getTotal().multiply(new BigDecimal("3.00"))).divide(new BigDecimal("39.00"),2,BigDecimal.ROUND_HALF_EVEN);
//    }
//    
//    @ReadOnly
//    public BigDecimal getComputedTotal() {
//    	return (this.getTotal().multiply(new BigDecimal("95.00"))).divide(new BigDecimal("39.00"),2,BigDecimal.ROUND_HALF_EVEN);
//    }
//    
//	public BigDecimal getAdjustedAnalysis() {
//		return adjustedAnalysis==null?this.getComputedAnalysis():adjustedAnalysis;
//	}
//
//	public void setAdjustedAnalysis(BigDecimal adjustedAnalysis) {
//		this.adjustedAnalysis = adjustedAnalysis;
//	}
//
//	public BigDecimal getAdjustedFunctionalTests() {
//		return adjustedFunctionalTests==null?this.getComputedFunctionalTests():adjustedFunctionalTests;
//	}
//
//	public void setAdjustedFunctionalTests(BigDecimal adjustedFunctionalTests) {
//		this.adjustedFunctionalTests = adjustedFunctionalTests;
//	}
//
//	public BigDecimal getAdjustedPrepareFunctionalTest() {
//		return adjustedPrepareFunctionalTest==null?this.getComputedPrepareFunctionalTest():adjustedPrepareFunctionalTest;
//	}
//
//	public void setAdjustedPrepareFunctionalTest(BigDecimal adjustedPrepareFunctionalTest) {
//		this.adjustedPrepareFunctionalTest = adjustedPrepareFunctionalTest;
//	}
//
//	public BigDecimal getAdjustedImplementAndRunTest() {
//		return adjustedImplementAndRunTest==null?this.getComputedImplementAndRunTest():adjustedImplementAndRunTest;
//	}

	public void setAdjustedImplementAndRunTest(BigDecimal adjustedImplementAndRunTest) {
		this.adjustedImplementAndRunTest = adjustedImplementAndRunTest;
	}

	public BigDecimal getAdjustedPeerReview() {
		return adjustedPeerReview==null?this.getComputedPeerReview():adjustedPeerReview;
	}

	public void setAdjustedPeerReview(BigDecimal adjustedPeerReview) {
		this.adjustedPeerReview = adjustedPeerReview;
	}

//	public BigDecimal getAdjustedUserAcceptanceTest() {
//		return adjustedUserAcceptanceTest==null?this.getComputedUserAcceptanceTest():adjustedUserAcceptanceTest;
//	}
//
//	public void setAdjustedUserAcceptanceTest(BigDecimal adjustedUserAcceptanceTest) {
//		this.adjustedUserAcceptanceTest = adjustedUserAcceptanceTest;
//	}
//
//	public BigDecimal getAdjustedDependenciesManagement() {
//		return adjustedDependenciesManagement==null?this.getComputedDependenciesManagement():adjustedDependenciesManagement;
//	}
//
//	public void setAdjustedDependenciesManagement(BigDecimal adjustedDependenciesManagement) {
//		this.adjustedDependenciesManagement = adjustedDependenciesManagement;
//	}
//
//	public BigDecimal getAdjustedTotal() {
//		return adjustedTotal==null?this.getComputedTotal():adjustedTotal;
//	}
//
//	public void setAdjustedTotal(BigDecimal adjustedTotal) {
//		this.adjustedTotal = adjustedTotal;
//	}
//
//	public BigDecimal getAdjustedRollout() {
//		return adjustedRollout==null?getComputedRollout():adjustedRollout;
//	}
//
//	public void setAdjustedRollout(BigDecimal adjustedRollout) {
//		this.adjustedRollout = adjustedRollout;
//	}


    

}