package com.theralink.service.insuranceTariff;

import com.core.framework.service.IGenericService;
import com.theralink.domain.InsuranceTariff;
import com.theralink.web.dto.IChartDto;

import java.util.List;

public interface IInsuranceTariffService extends IGenericService<InsuranceTariff, String> {
	List<IChartDto> chart(String from, String to);
}
