package com.service.customerLevelingDescription;

import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.domain.CustomerLeveling;
import com.domain.CustomerLevelingDescription;
import com.repository.customerLeveling.ICustomerLevelingRepository;
import com.repository.customerLevelingDescription.ICustomerLevelingDescriptionRepository;
import com.service.customerLeveling.ICustomerLevelingService;
import com.web.dto.ICustomerPriorityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerLevelingDescriptionService extends GenericService<CustomerLevelingDescription, String> implements ICustomerLevelingDescriptionService {

	@Autowired
	private ICustomerLevelingDescriptionRepository iCustomerLevelingDescriptionRepository;

	@Override
	protected IGenericRepository<CustomerLevelingDescription, String> getGenericRepo() {
		return iCustomerLevelingDescriptionRepository;
	}

	@Override
	public List<CustomerLevelingDescription> list(String customerId) {
		return iCustomerLevelingDescriptionRepository.list(customerId);
	}
}
