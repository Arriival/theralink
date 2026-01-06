package com.theralink.domain.clinic.repo;

import com.theralink.domain.clinic.model.Clinic;
import com.theralink.repository.IGenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClinicRepository extends IGenericRepository<Clinic, String> {


	@Query(value = " from Clinic e where :txt is null  or e.name like CONCAT('%',:txt,'%') or e.manager like CONCAT('%',:txt,'%')")
	Page<Clinic> getAllGrid( @Param("txt") String txt, Pageable pageable);

	List<Clinic> findAllByNameContains(String name);

	@Query("select e from Clinic e where :hasAdminRole is true or e in (select p.organizations from Person p where p.id = :personId)")
	List<Clinic> findAllByAuthorize(@Param("personId") String personId, boolean hasAdminRole);

	@Query(value = "select max(e.code) from Clinic e ")
	String getLastCode();

}



