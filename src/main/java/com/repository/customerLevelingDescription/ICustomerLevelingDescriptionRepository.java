package com.repository.customerLevelingDescription;

import com.core.framework.repository.IGenericRepository;
import com.domain.CustomerLeveling;
import com.domain.CustomerLevelingDescription;
import com.web.dto.ICustomerPriorityDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerLevelingDescriptionRepository extends IGenericRepository<CustomerLevelingDescription, String> {

	@Query("select e from CustomerLevelingDescription e where e.customer.id = :customerId order by e.createdDate desc ")
	List<CustomerLevelingDescription> list(@Param("customerId") String customerId);

}
