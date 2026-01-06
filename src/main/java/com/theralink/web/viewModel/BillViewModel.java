package com.theralink.web.viewModel;

import com.theralink.web.viewModel.BaseEntityViewModel;
import lombok.Data;

import java.util.Date;

@Data
public class BillViewModel extends BaseEntityViewModel<String> {
	private String subject;
	private String date;
	private Double income;
	private Double cost;
	private String description;
}
