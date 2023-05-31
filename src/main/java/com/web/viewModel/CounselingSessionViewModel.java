package com.web.viewModel;

import com.core.framework.web.viewModel.BaseEntityViewModel;
import lombok.Data;

import java.util.Date;

@Data
public class CounselingSessionViewModel extends BaseEntityViewModel<String> {
	private String consultantId;
	private String consultantFirstName;
	private String consultantLastName;
	private String customerId;
	private String customerFirstName;
	private String customerLastName;
	private String customerPhone;
	private String customerNationalCode;
	private Date   start;
	private Date   end;
	private Float  customerFee;
	private Float  consultantFee;
	private String insuranceTariffId;
	private String insuranceTariffTitle;
	private Integer minutes;

	public long getDuration() {
		long diff = 0;
		if (end == null) {
			Date current = new Date();
			diff = current.getTime() - start.getTime();
		}
		else {
			diff = end.getTime() - start.getTime();
		}

		long diffMinutes = (diff / 1000) / 60;
		return diffMinutes;
	}
}
