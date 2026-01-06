package com.theralink.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IGenericService<T, PK extends Serializable> {

	T load(PK id);

	T authorizedLoad(PK entityId);


	List<T> getAllByFilter(String filterName, Map<String, Object> params);

	List<T> getAllByClinicFilter();

	List<T> getAll();

	Page<T> getAllGridByClinicFilter(Pageable pageable);

	Page<T> getAllGrid(Pageable pageable);

	Page<T> getAllGrid(Specification<T> specification, Pageable pageable);

	PK save(T t);

	boolean deleteById(PK id);

	boolean deleteByAuthorizedId(PK id);
}
