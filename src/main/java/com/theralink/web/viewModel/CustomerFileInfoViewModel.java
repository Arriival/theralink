package com.theralink.web.viewModel;

import com.core.framework.web.viewModel.BaseEntityViewModel;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerFileInfoViewModel extends BaseEntityViewModel<String> {
	private String genoGram;
	private String psychologistVisitHistory;
	private String drugUseHistory;
	private String sicknessHistory;
	private String sleepingStatus;
	private String lifeStyle;
	private String problems;
	private String problemBeginning;
	private String problemCause;
	private String testsDone;
	private String firstRecognition;
}
