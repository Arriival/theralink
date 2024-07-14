package com.service.customerGenogramInformation;

import com.core.framework.service.IGenericService;
import com.domain.CustomerGenogramInformation;

import java.util.List;

public interface ICustomerGenogramInformationService extends IGenericService<CustomerGenogramInformation, String> {
	List<CustomerGenogramInformation> list(String customerId);

	Integer saveList(List<CustomerGenogramInformation> customerGenogramInformations);
}
