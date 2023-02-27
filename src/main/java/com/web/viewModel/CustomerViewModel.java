package com.web.viewModel;

import com.core.framework.web.viewModel.BaseEntityViewModel;
import lombok.Data;

@Data
public class CustomerViewModel extends BaseEntityViewModel<String> {
	private String firstName;
	private String lastName;
	private String phone;
	private String nationalCode;
	private String address;
	private String insuranceTariffId;
	private String insuranceTariffTitle;
}
