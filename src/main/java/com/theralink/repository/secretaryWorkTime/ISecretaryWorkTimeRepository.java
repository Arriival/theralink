package com.theralink.repository.secretaryWorkTime;

import com.core.framework.repository.IGenericRepository;
import com.theralink.domain.CounselingSession;
import com.theralink.domain.SecretaryWorkTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ISecretaryWorkTimeRepository extends IGenericRepository<SecretaryWorkTime, String> {

	@Query("select e from SecretaryWorkTime e where e.end is null and e.secretary.id = :personnelId")
	SecretaryWorkTime loadUnFinishedActivity(@Param("personnelId") String personnelId);

	@Query("select e from SecretaryWorkTime e where"
			+ " (:personnelId like'null' or e.secretary.id = :personnelId) "
			+ " and (:fromDate is null or DATE_FORMAT(e.start, '%Y-%m-%d')  >= :fromDate ) "
			+ " and (:toDate is null or DATE_FORMAT(e.start, '%Y-%m-%d') <= :toDate  ) "
			+ " order by e.start desc")
	Page<SecretaryWorkTime> getAllGrid(@Param("personnelId") String personnelId, @Param("fromDate") String fromDate, @Param("toDate") String toDate ,Pageable pageable);

	@Query("select sum(e.salary) from SecretaryWorkTime e where "
			+ " (:personnelId like'null' or e.secretary.id = :personnelId) "
			+ " and (:fromDate is null or DATE_FORMAT(e.start, '%Y-%m-%d')  >= :fromDate ) "
			+ " and (:toDate is null or DATE_FORMAT(e.start, '%Y-%m-%d') <= :toDate  ) "
			+ " order by e.start desc")
	Float salarySum(@Param("personnelId") String personnelId, @Param("fromDate") String fromDate, @Param("toDate") String toDate);

}
