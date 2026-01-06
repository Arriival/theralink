package com.theralink.service.customerGenogramInformation;

import com.theralink.service.IGenericService;
import com.theralink.domain.CustomerGenogramInformation;

import java.util.List;

public interface ICustomerGenogramInformationService extends IGenericService<CustomerGenogramInformation, String> {
	List<CustomerGenogramInformation> list(String customerId);

	Integer saveList(List<CustomerGenogramInformation> customerGenogramInformations);
}
