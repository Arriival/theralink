package com.repository.counselingSession;

import com.core.framework.repository.IGenericRepository;
import com.domain.CounselingSession;
import com.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ICounselingSessionRepository extends IGenericRepository<CounselingSession, String> {
	@Query("select e from CounselingSession e where e.end is null order by e.start desc ")
	List<CounselingSession> inProcessList();

	@Query("select e from CounselingSession e where e.customer.id = :customerId and e.end is not null order by e.start desc")
	Page<CounselingSession> customerSessionHistory(@Param("customerId") String customerId, Pageable pageable);

	@Query("select e from CounselingSession e where "
			+ " e.consultant.id = :personnelId "
			+ " and (:fromDate is null or DATE_FORMAT(e.start, '%Y-%m-%d')  >= :fromDate ) "
			+ " and (:toDate is null or DATE_FORMAT(e.start, '%Y-%m-%d') <= :toDate  ) "
			+ " order by e.start desc")
	Page<CounselingSession> consultantSessionHistory(@Param("personnelId") String personnelId, @Param("fromDate") String fromDate, @Param("toDate") String toDate, Pageable pageable);
}
