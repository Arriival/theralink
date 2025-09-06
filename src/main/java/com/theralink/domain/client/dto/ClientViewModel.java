package com.theralink.domain.client.dto;

import com.core.framework.web.viewModel.BaseEntityViewModel;
import lombok.Data;

import java.util.Date;

@Data
public class ClientViewModel extends BaseEntityViewModel<String> {
	private String  clinicId;
	private String  clinicName;
	private String  firstName;
	private String  lastName;
	private String  phone;
	private String  nationalCode;
	private String  address;
	private String  insuranceTariffTitle;
	private String  insuranceTariffId;
	private String  educationLevelTitle;
	private String  educationLevelId;
	private int     age;
	private String  job;
	private Boolean married;
	private Boolean marriageHistory;
	private String  genoGram;
	private String  psychologistVisitHistory;
	private String  drugUseHistory;
	private String  sicknessHistory;
	private String  sleepingStatus;
	private String  lifeStyle;
	private String  problems;
	private String  problemBeginning;
	private String  problemCause;
	private String  testsDone;
	private String  firstRecognition;
	private String  customerLevelingId;
	//	private String  customerLevelingTitle;
	//	private int     customerLevelingDuration;
	private Date    lastVisit;
}
