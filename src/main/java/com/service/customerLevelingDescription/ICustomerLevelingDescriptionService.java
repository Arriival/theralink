package com.service.customerLevelingDescription;

import com.core.framework.service.IGenericService;
import com.domain.CustomerLeveling;
import com.domain.CustomerLevelingDescription;

import java.util.List;

public interface ICustomerLevelingDescriptionService extends IGenericService<CustomerLevelingDescription, String> {
	List<CustomerLevelingDescription> list(String customerId);

}
