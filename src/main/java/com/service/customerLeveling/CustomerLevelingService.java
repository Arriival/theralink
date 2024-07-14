package com.service.customerLeveling;

import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.domain.CustomerLeveling;
import com.repository.customerLeveling.ICustomerLevelingRepository;
import com.web.dto.ICustomerPriorityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerLevelingService extends GenericService<CustomerLeveling, String> implements ICustomerLevelingService {

	@Autowired
	private ICustomerLevelingRepository iCustomerLevelingRepository;

	@Override
	protected IGenericRepository<CustomerLeveling, String> getGenericRepo() {
		return iCustomerLevelingRepository;
	}

	@Override
	public List<CustomerLeveling> list() {
		return iCustomerLevelingRepository.list();
	}


	@Override
	public List<ICustomerPriorityDto> priorityCustomerList() {
		return iCustomerLevelingRepository.priorityCustomerList();
	}
}
