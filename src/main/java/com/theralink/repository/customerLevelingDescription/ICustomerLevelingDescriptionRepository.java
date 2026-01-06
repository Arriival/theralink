package com.theralink.repository.customerLevelingDescription;

import com.theralink.repository.IGenericRepository;
import com.theralink.domain.CustomerLeveling;
import com.theralink.domain.CustomerLevelingDescription;
import com.theralink.web.dto.ICustomerPriorityDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerLevelingDescriptionRepository extends IGenericRepository<CustomerLevelingDescription, String> {

	@Query("select e from CustomerLevelingDescription e where e.client.id = :customerId order by e.createdDate desc ")
	List<CustomerLevelingDescription> list(@Param("customerId") String customerId);

}
