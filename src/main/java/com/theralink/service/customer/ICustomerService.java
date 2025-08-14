package com.theralink.service.customer;

import com.core.framework.service.IGenericService;
import com.theralink.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService extends IGenericService<Client, String> {
	Client find(String nationalCode);

	long count();

	Page<Client> getAllGrid(Pageable pageable, String searchTxt);
}
