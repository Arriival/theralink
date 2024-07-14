package com.web.viewModel;

import com.core.framework.web.viewModel.BaseEntityViewModel;
import lombok.Data;

@Data
public class CustomerLevelingDescriptionViewModel extends BaseEntityViewModel<String> {

	private String customerId;
	private String firstname;
	private String lastname;
	private String description;

}
