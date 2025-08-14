package com.theralink.service.customerLevelingDescription;

import com.core.framework.service.IGenericService;
import com.theralink.domain.CustomerLeveling;
import com.theralink.domain.CustomerLevelingDescription;

import java.util.List;

public interface ICustomerLevelingDescriptionService extends IGenericService<CustomerLevelingDescription, String> {
	List<CustomerLevelingDescription> list(String customerId);

}
