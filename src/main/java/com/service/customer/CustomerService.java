package com.service.customer;

import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.domain.Customer;
import com.repository.customer.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends GenericService<Customer, String> implements ICustomerService {

	@Autowired
	private ICustomerRepository iCustomerRepository;

	@Override
	protected IGenericRepository<Customer, String> getGenericRepo() {
		return iCustomerRepository;
	}

	@Override
	public Customer find(String nationalCode) {
		return iCustomerRepository.find(nationalCode);
	}

	@Override
	public long count() {
		return iCustomerRepository.count();
	}
}
