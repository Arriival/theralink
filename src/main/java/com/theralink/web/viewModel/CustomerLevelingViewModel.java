package com.theralink.web.viewModel;

import com.theralink.web.viewModel.BaseEntityViewModel;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerLevelingViewModel extends BaseEntityViewModel<String> {
	private String title;
	private String color;
	private int    duration;

	public String getFullTitle() {
		return this.title + " - " + this.duration + " روز ";
	}

}
