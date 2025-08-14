package com.theralink.service.insuranceTariff;

import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.core.framework.utils.DateUtility;
import com.theralink.domain.InsuranceTariff;
import com.theralink.repository.insuranceTariff.IInsuranceTariffRepository;
import com.theralink.web.dto.IChartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceTariffService extends GenericService<InsuranceTariff, String> implements IInsuranceTariffService {

	@Autowired
	private IInsuranceTariffRepository iInsuranceTariffRepository;

	@Override
	protected IGenericRepository<InsuranceTariff, String> getGenericRepo() {
		return iInsuranceTariffRepository;
	}

	@Override
	public List<IChartDto> chart(String from, String to) {
		return iInsuranceTariffRepository.chart(DateUtility.jalaliToDate(from), DateUtility.jalaliToDate(to));
	}
}
