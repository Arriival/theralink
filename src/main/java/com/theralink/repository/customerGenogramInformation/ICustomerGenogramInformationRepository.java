package com.theralink.repository.customerGenogramInformation;

import com.theralink.repository.IGenericRepository;
import com.theralink.domain.CustomerGenogramInformation;

import java.util.List;

public interface ICustomerGenogramInformationRepository extends IGenericRepository<CustomerGenogramInformation, String> {

	List<CustomerGenogramInformation> getAllByClientId(String customer_id);
}
