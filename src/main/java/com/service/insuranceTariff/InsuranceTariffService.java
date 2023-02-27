package com.service.insuranceTariff;

import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.core.framework.utils.SecurityUtil;
import com.domain.InsuranceTariff;
import com.domain.Personnel;
import com.domain.SecretaryWorkTime;
import com.repository.insuranceTariff.IInsuranceTariffRepository;
import com.repository.secretaryWorkTime.ISecretaryWorkTimeRepository;
import com.service.personnel.IPersonnelService;
import com.service.secretaryWorkTime.ISecretaryWorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;

@Service
public class InsuranceTariffService extends GenericService<InsuranceTariff, String> implements IInsuranceTariffService {

	@Autowired
	private IInsuranceTariffRepository iInsuranceTariffRepository;

	@Override
	protected IGenericRepository<InsuranceTariff, String> getGenericRepo() {
		return iInsuranceTariffRepository;
	}

}
