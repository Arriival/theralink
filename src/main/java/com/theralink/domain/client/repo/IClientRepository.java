package com.theralink.domain.client.repo;

import com.core.framework.repository.IGenericRepository;
import com.theralink.domain.client.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IClientRepository extends IGenericRepository<Client, String> {
	@Query("from Client e where e.nationalCode = :nationalCode and :clinicId = :clinicId")
	Client find(@Param("nationalCode") String nationalCode, @Param("clinicId") String clinic);

	@Query(value = " from Client e where :txt is null  or e.nationalCode like CONCAT('%',:txt,'%') or CONCAT(e.firstName,' ', e.lastName) like CONCAT('%',:txt,'%')")
	Page<Client> getAllGrid( @Param("txt") String txt, Pageable pageable);
}
