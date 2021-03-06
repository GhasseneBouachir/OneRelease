package org.onerelease.model;


import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;



//@View(members="functionalityID , functionalityName,  descriptionFunctionality"
//		+ "; functionalityAnalysis")
@Views({@View(name="functinalityUnderUserStory", 
members="functionalityID , functionalityName,  descriptionFunctionality")})
@Entity

public class Functionality {

	@Id
    @Column(length = 4)
    private Integer functionalityID;
    
    @Column(length = 30) @Required
    private String functionalityName;
    
    @Column @Required
    private String descriptionFunctionality;
    
	
	
	
	@AsEmbedded
    @OneToMany(cascade = CascadeType.REMOVE,fetch=FetchType.LAZY,
    		mappedBy = "functionality")
    private Collection<FunctionalityAnalysis> functionalityAnalysis;
    

   

    public String getDescriptionFunctionality() {
        return descriptionFunctionality;
    }

    public void setDescriptionFunctionality(String descriptionFunctionality) {
        this.descriptionFunctionality = descriptionFunctionality;
    }

	public Integer getFunctionalityID() {
		return functionalityID;
	}

	public void setFunctionalityID(Integer functionalityID) {
		this.functionalityID = functionalityID;
	}

	public String getFunctionalityName() {
		return functionalityName;
	}

	public void setFunctionalityName(String functionalityName) {
		this.functionalityName = functionalityName;
	}
	


	public Collection<FunctionalityAnalysis> getFunctionalityAnalysis() {
		return functionalityAnalysis;
	}

	public void setFunctionalityAnalysis(Collection<FunctionalityAnalysis> functionalityAnalysis) {
		this.functionalityAnalysis = functionalityAnalysis;
	}








    
}