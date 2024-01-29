package com.repository.customer;

import com.core.framework.repository.IGenericRepository;
import com.domain.ConsultantType;
import com.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICustomerRepository extends IGenericRepository<Customer, String> {
	@Query("from Customer e where e.nationalCode = :nationalCode ")
	Customer find(@Param("nationalCode") String nationalCode);

	@Query(value = " from Customer e where :txt is null  or e.nationalCode like CONCAT('%',:txt,'%') or CONCAT(e.firstname,' ', e.lastname) like CONCAT('%',:txt,'%')")
	Page<Customer> getAllGrid( @Param("txt") String txt, Pageable pageable);
}
