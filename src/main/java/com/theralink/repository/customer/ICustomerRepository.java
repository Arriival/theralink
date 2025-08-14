package com.theralink.repository.customer;

import com.core.framework.repository.IGenericRepository;
import com.theralink.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICustomerRepository extends IGenericRepository<Client, String> {
	@Query("from Client e where e.nationalCode = :nationalCode ")
	Client find(@Param("nationalCode") String nationalCode);

	@Query(value = " from Client e where :txt is null  or e.nationalCode like CONCAT('%',:txt,'%') or CONCAT(e.firstName,' ', e.lastName) like CONCAT('%',:txt,'%')")
	Page<Client> getAllGrid( @Param("txt") String txt, Pageable pageable);
}
