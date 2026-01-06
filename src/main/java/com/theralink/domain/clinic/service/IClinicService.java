package com.theralink.domain.clinic.service;

import com.theralink.domain.clinic.model.Clinic;
import com.theralink.service.IGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClinicService extends IGenericService<Clinic, String> {
	Page<Clinic> getAllGrid(Pageable pageable, String searchTxt);

	String generateNewPersonCode();

	List<Clinic> findAllByName(String name);

	List<Clinic> findAllByAuthorize();
}
