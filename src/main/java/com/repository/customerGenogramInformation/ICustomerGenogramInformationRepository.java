package com.repository.customerGenogramInformation;

import com.core.framework.repository.IGenericRepository;
import com.domain.CustomerGenogramInformation;

import java.util.List;

public interface ICustomerGenogramInformationRepository extends IGenericRepository<CustomerGenogramInformation, String> {

	List<CustomerGenogramInformation> getAllByCustomerId(String customer_id);
}
