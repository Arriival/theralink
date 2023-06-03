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

	@Query(value = "select SUM(TIMESTAMPDIFF(MINUTE, e.start, e.end))/t.session_time from MAC_COUNSELING_SESSION e left join MAC_INSURANCE_TARIFF t on t.id = e.INSURANCE_TARIFF_ID "
			+ " where e.customer_id = :customerId and e.end is not null and (e.start >= :first or :first is null)", nativeQuery = true)
	Float numberOfCustomerSession(@Param("customerId") String customerId , @Param("first") Date firstMonth );

	@Query("select e from CounselingSession e where "
			+ " (:personnelId like'null' or e.consultant.id = :personnelId) "
			+ " and (:insuranceTariffId like'null' or e.insuranceTariff.id = :insuranceTariffId) "
			+ " and (:fromDate is null or DATE_FORMAT(e.start, '%Y-%m-%d')  >= :fromDate ) "
			+ " and (:toDate is null or DATE_FORMAT(e.start, '%Y-%m-%d') <= :toDate  ) "
			+ " order by e.start desc")
	Page<CounselingSession> consultantSessionHistory(@Param("personnelId") String personnelId, @Param("insuranceTariffId") String insuranceTariffId, @Param("fromDate") String fromDate, @Param("toDate") String toDate, Pageable pageable);

	@Query("select sum(e.consultantFee) from CounselingSession e where "
			+ " (:personnelId like'null' or e.consultant.id = :personnelId) "
			+ " and (:insuranceTariffId like'null' or e.insuranceTariff.id = :insuranceTariffId) "
			+ " and (:fromDate is null or DATE_FORMAT(e.start, '%Y-%m-%d')  >= :fromDate ) "
			+ " and (:toDate is null or DATE_FORMAT(e.start, '%Y-%m-%d') <= :toDate  ) "
			+ " order by e.start desc")
	Float consultantFeeSum(@Param("personnelId") String personnelId, @Param("insuranceTariffId") String insuranceTariffId, @Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query("select sum(e.customerFee) from CounselingSession e where "
			+ " (:personnelId like'null' or e.consultant.id = :personnelId) "
			+ " and (:insuranceTariffId like'null' or e.insuranceTariff.id = :insuranceTariffId) "
			+ " and (:fromDate is null or DATE_FORMAT(e.start, '%Y-%m-%d')  >= :fromDate ) "
			+ " and (:toDate is null or DATE_FORMAT(e.start, '%Y-%m-%d') <= :toDate  ) "
			+ " order by e.start desc")
	Float customerFeeSum(@Param("personnelId") String personnelId,@Param("insuranceTariffId") String insuranceTariffId, @Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query("select sum(e.end - e.start) from CounselingSession e where "
			+ " (:personnelId like'null' or e.consultant.id = :personnelId) "
			+ " and (:insuranceTariffId like'null' or e.insuranceTariff.id = :insuranceTariffId) "
			+ " and (:fromDate is null or DATE_FORMAT(e.start, '%Y-%m-%d')  >= :fromDate ) "
			+ " and (:toDate is null or DATE_FORMAT(e.start, '%Y-%m-%d') <= :toDate  ) "
			+ " order by e.start desc")
	Long sessionTimeSum(@Param("personnelId") String personnelId,@Param("insuranceTariffId") String insuranceTariffId, @Param("fromDate") String fromDate, @Param("toDate") String toDate);
}
