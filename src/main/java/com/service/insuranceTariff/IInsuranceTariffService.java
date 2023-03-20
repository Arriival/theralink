package com.service.insuranceTariff;

import com.core.framework.service.IGenericService;
import com.domain.InsuranceTariff;
import com.web.dto.IChartDto;

import java.util.List;

public interface IInsuranceTariffService extends IGenericService<InsuranceTariff, String> {
	List<IChartDto> chart();
}
