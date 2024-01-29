package com.service.customer;

import com.core.framework.service.IGenericService;
import com.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService extends IGenericService<Customer, String> {
	Customer find(String nationalCode);

	long count();

	Page<Customer> getAllGrid(Pageable pageable, String searchTxt);
}
