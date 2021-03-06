package org.onerelease.validators.sprint;

import java.util.*;

import javax.persistence.*;

import org.onerelease.enumeration.*;
import org.onerelease.model.*;
import org.openxava.jpa.*;
import org.openxava.util.*;
import org.openxava.validators.*;

public class StartDateValidator implements IValidator {
	
	
	private Squad squad;
	private Date startDate;
	
	@Override
	public void validate(Messages errors) throws Exception {
		if(getLastSprintEndDate(getSquad().getId(), getStartDate())!= null && 
				startDate.before(getLastSprintEndDate(getSquad().getId(),getStartDate())))
		errors.add("start_date_sprint_before",getStartDate());
	}
	
	//a method returns last end Sprint date of the Squad passed in parameter
		private static Date getLastSprintEndDate(String squad, Date startDate) {
				 Query query = XPersistence.getManager().createNativeQuery(
						 "select max(enddate) from Sprint as s "
						 + "where s.squad_id = '" + squad + "' "
						 		+ "and s.enddate < '"+ startDate+ "'");
						if( query.getSingleResult()!=null)
							{	Date d = (Date) query.getSingleResult();
								return d;
							}
						else return null;
		}

	public Squad getSquad() {
		return squad;
	}

	public void setSquad(Squad squad) {
		this.squad = squad;
	}
	
	

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	

}
