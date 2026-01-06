package com.theralink.service.insuranceTariff;

import com.theralink.service.IGenericService;
import com.theralink.domain.InsuranceTariff;
import com.theralink.web.dto.IChartDto;

import java.util.List;

public interface IInsuranceTariffService extends IGenericService<InsuranceTariff, String> {
	List<IChartDto> chart(String from, String to);

	List<InsuranceTariff> getAllEnableList();

	Boolean toggleStatus(String id);
}
