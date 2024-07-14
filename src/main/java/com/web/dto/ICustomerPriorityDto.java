package com.web.dto;

import java.util.Date;

public interface ICustomerPriorityDto {

	String getCustomerId();
	String getNationalCode();
	String getFirstName();
	String getLastName();
	String getLevelTitle();
	Integer getLevelDuration();
	String getPhone();
	Date getNextVisit();
	Date getLastVisit();

}
