package com.repository.customer;

import com.core.framework.repository.IGenericRepository;
import com.domain.ConsultantType;
import com.domain.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICustomerRepository extends IGenericRepository<Customer, String> {
	@Query("from Customer e where e.nationalCode = :nationalCode ")
	Customer find(@Param("nationalCode") String nationalCode);
}
