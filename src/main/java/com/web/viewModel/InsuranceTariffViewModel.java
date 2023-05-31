package com.web.viewModel;

import com.core.framework.web.viewModel.BaseEntityViewModel;
import lombok.Data;

import javax.persistence.Column;

@Data
public class InsuranceTariffViewModel extends BaseEntityViewModel<String> {
	private String title;
	private Integer sessionTime;
	private Float  customerReceivedFactor;
	private Float  lisancPaymentFactor;
	private Float  arshadPaymentFactor;
	private Float  drStdPaymentFactor;
	private Float  drPaymentFactor;
	private Float  postDrPaymentFactor;
}
