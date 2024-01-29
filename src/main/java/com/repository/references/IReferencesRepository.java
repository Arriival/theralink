package com.repository.references;

import com.core.framework.repository.IGenericRepository;
import com.domain.References;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IReferencesRepository extends IGenericRepository<References, String> {

	@Query("SELECT e FROM References e WHERE e.referredTo.person.id = :referredToPersonId ")
	Page<References> referredToGrid(@Param("referredToPersonId") String referredToPersonId, Pageable pageable);


	@Query("SELECT e FROM References e WHERE e.referrer.person.id = :referredFromPersonId and (:customerId is null or e.customer.id = :customerId) order by e.createdDate desc ")
	Page<References> referredFromGrid(@Param("referredFromPersonId")String referredFromPersonId, @Param("customerId")String customerId, Pageable pageable);
}
