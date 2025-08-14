package com.theralink.repository.customerGenogramInformation;

import com.core.framework.repository.IGenericRepository;
import com.theralink.domain.CustomerGenogramInformation;

import java.util.List;

public interface ICustomerGenogramInformationRepository extends IGenericRepository<CustomerGenogramInformation, String> {

	List<CustomerGenogramInformation> getAllByClientId(String customer_id);
}
