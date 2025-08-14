package com.theralink.service.customerLeveling;

import com.core.framework.service.IGenericService;
import com.theralink.domain.CustomerLeveling;
import com.theralink.web.dto.ICustomerPriorityDto;

import java.util.List;

public interface ICustomerLevelingService extends IGenericService<CustomerLeveling, String> {
	List<CustomerLeveling> list();

	List<ICustomerPriorityDto> priorityCustomerList();
}
