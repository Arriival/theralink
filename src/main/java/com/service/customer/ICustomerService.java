package com.service.customer;

import com.core.framework.service.IGenericService;
import com.domain.Customer;

public interface ICustomerService extends IGenericService<Customer, String> {
	Customer find(String nationalCode);

	long count();
}
