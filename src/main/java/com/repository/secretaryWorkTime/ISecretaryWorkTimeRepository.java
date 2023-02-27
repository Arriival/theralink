package com.repository.secretaryWorkTime;

import com.core.framework.repository.IGenericRepository;
import com.domain.SecretaryWorkTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ISecretaryWorkTimeRepository extends IGenericRepository<SecretaryWorkTime, String> {

	@Query("select e from SecretaryWorkTime e where e.end is null and e.secretary.id = :personnelId")
	SecretaryWorkTime loadUnFinishedActivity(@Param("personnelId") String personnelId);

	@Query("select e from SecretaryWorkTime e where e.secretary.id = :personnelId order by e.start desc")
	Page<SecretaryWorkTime> getAllGrid(@Param("personnelId") String personnelId, Pageable pageable);
}
