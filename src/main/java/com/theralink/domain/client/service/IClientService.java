package com.theralink.domain.client.service;

import com.theralink.service.IGenericService;
import com.theralink.domain.client.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClientService extends IGenericService<Client, String> {
	Client find(String nationalCode);

	long count();

	Page<Client> getAllGrid(Pageable pageable, String searchTxt);
}
