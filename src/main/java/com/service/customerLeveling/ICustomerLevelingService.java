package com.service.customerLeveling;

import com.core.framework.service.IGenericService;
import com.domain.CustomerLeveling;
import com.web.dto.ICustomerPriorityDto;

import java.util.List;

public interface ICustomerLevelingService extends IGenericService<CustomerLeveling, String> {
	List<CustomerLeveling> list();

	List<ICustomerPriorityDto> priorityCustomerList();
}
