package com.web.viewModel;

import com.core.framework.web.viewModel.BaseEntityViewModel;
import lombok.Data;

@Data
public class CustomerGenogramInformationViewModel extends BaseEntityViewModel<String> {

	private String customerId;
	private String firstname;
	private String lastname;
	private String fieldId;
	private String fieldTitle;
	private String description;

}
