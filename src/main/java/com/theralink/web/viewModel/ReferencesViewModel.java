package com.theralink.web.viewModel;

import lombok.Data;

@Data
public class ReferencesViewModel {
	private String  id;
	private String  customerId;
	private String  customerFirstname;
	private String  customerLastname;
	private String  referrerId;
	private String  referrerFirstname;
	private String  referrerLastname;
	private String  referredToId;
	private String  referredToFirstname;
	private String  referredToLastname;
	private String  description;
	private Boolean isVisited;
	private String  referralDate;
}
