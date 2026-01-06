package com.theralink.service.insuranceTariff;

import com.theralink.repository.IGenericRepository;
import com.theralink.service.GenericService;

import com.theralink.utils.DateUtility;
import com.theralink.domain.InsuranceTariff;
import com.theralink.repository.insuranceTariff.IInsuranceTariffRepository;
import com.theralink.web.dto.IChartDto;
import jakarta.transaction.Transactional;
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
		enableClinicFilter();
		return iInsuranceTariffRepository.chart(DateUtility.jalaliToDate(from), DateUtility.jalaliToDate(to));
	}

	@Override
	public List<InsuranceTariff> getAllEnableList() {
		enableClinicFilter();
		return iInsuranceTariffRepository.findAllEnableList();
	}

	@Transactional
	@Override
	public Boolean toggleStatus(String id) {
		InsuranceTariff insuranceTariff = authorizedLoad(id);
		if (insuranceTariff != null) {
			if (insuranceTariff.getIsDisabled() == null) {
				insuranceTariff.setIsDisabled(true);
			}
			else {
				insuranceTariff.setIsDisabled(!insuranceTariff.getIsDisabled());
			}
			super.save(insuranceTariff);
			return insuranceTariff.getIsDisabled();
		}
		return null;
	}
}
