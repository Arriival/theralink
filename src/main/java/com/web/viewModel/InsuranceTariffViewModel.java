package com.web.viewModel;

import com.core.framework.web.viewModel.BaseEntityViewModel;
import lombok.Data;

import javax.persistence.Column;

@Data
public class InsuranceTariffViewModel extends BaseEntityViewModel<String> {
	private String title;
	private Float  consultantPaymentFactor;
	private Float  customerReceivedFactor;
}
