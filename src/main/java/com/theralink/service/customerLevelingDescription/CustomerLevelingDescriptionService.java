package com.theralink.service.customerLevelingDescription;

import com.theralink.repository.IGenericRepository;
import com.theralink.service.GenericService;

import com.theralink.domain.CustomerLeveling;
import com.theralink.domain.CustomerLevelingDescription;
import com.theralink.repository.customerLeveling.ICustomerLevelingRepository;
import com.theralink.repository.customerLevelingDescription.ICustomerLevelingDescriptionRepository;
import com.theralink.service.customerLeveling.ICustomerLevelingService;
import com.theralink.web.dto.ICustomerPriorityDto;
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
