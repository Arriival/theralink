package com.theralink.repository.counselingSession;

import com.core.framework.repository.IGenericRepository;
import com.theralink.domain.CounselingSession;
import com.theralink.domain.client.model.Client;
import com.theralink.web.dto.ISessionDescriptionsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ICounselingSessionRepository extends IGenericRepository<CounselingSession, String> {
	@Query("select e from CounselingSession e where e.end is null order by e.start desc ")
	List<CounselingSession> inProcessList();

	@Query("select e from CounselingSession e where (:personId is null or e.consultant.person.id = :personId) and e.client.id = :customerId and e.end is not null order by e.start desc")
	Page<CounselingSession> customerSessionHistory(@Param("customerId") String customerId, @Param("personId") String personId, Pageable pageable);

	@Query(value = "select SUM(e.SESSION_COUNT)from MAC_COUNSELING_SESSION e"
			+ " where e.customer_id = :customerId and e.end is not null and (e.start >= :first or :first is null)", nativeQuery = true)
	Float numberOfCustomerSession(@Param("customerId") String customerId , @Param("first") Date firstMonth );

	@Query("select e from CounselingSession e where "
			+ " (:personnelId like'null' or e.consultant.id = :personnelId) "
			+ " and ( '' in :insuranceTariffIds or e.insuranceTariff.id in :insuranceTariffIds) "
			+ " and (:fromDate is null or DATE_FORMAT(e.start, '%Y-%m-%d')  >= :fromDate ) "
			+ " and (:toDate is null or DATE_FORMAT(e.start, '%Y-%m-%d') <= :toDate  ) "
			+ " order by e.start desc")
	Page<CounselingSession> consultantSessionHistory(@Param("personnelId") String personnelId, @Param("insuranceTariffIds") String[] insuranceTariffIds, @Param("fromDate") String fromDate, @Param("toDate") String toDate, Pageable pageable);

	@Query("select sum(e.consultantFee) from CounselingSession e where "
			+ " (:personnelId like'null' or e.consultant.id = :personnelId) "
			+ " and ( '' in :insuranceTariffIds or e.insuranceTariff.id in :insuranceTariffIds) "
			+ " and (:fromDate is null or DATE_FORMAT(e.start, '%Y-%m-%d')  >= :fromDate ) "
			+ " and (:toDate is null or DATE_FORMAT(e.start, '%Y-%m-%d') <= :toDate  ) "
			+ " order by e.start desc")
	Float consultantFeeSum(@Param("personnelId") String personnelId, @Param("insuranceTariffIds") String[] insuranceTariffIds, @Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query("select sum(e.customerFee) from CounselingSession e where "
			+ " (:personnelId like'null' or e.consultant.id = :personnelId) "
			+ " and ( '' in :insuranceTariffIds or e.insuranceTariff.id in :insuranceTariffIds) "
			+ " and (:fromDate is null or DATE_FORMAT(e.start, '%Y-%m-%d')  >= :fromDate ) "
			+ " and (:toDate is null or DATE_FORMAT(e.start, '%Y-%m-%d') <= :toDate  ) "
			+ " order by e.start desc")
	Float customerFeeSum(@Param("personnelId") String personnelId,@Param("insuranceTariffIds") String[] insuranceTariffIds, @Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query("select sum(e.end - e.start) from CounselingSession e where "
			+ " (:personnelId like'null' or e.consultant.id = :personnelId) "
			+ " and ( '' in :insuranceTariffIds or e.insuranceTariff.id in :insuranceTariffIds) "
			+ " and (:fromDate is null or DATE_FORMAT(e.start, '%Y-%m-%d')  >= :fromDate ) "
			+ " and (:toDate is null or DATE_FORMAT(e.start, '%Y-%m-%d') <= :toDate  ) "
			+ " order by e.start desc")
	Long sessionTimeSum(@Param("personnelId") String personnelId,@Param("insuranceTariffIds") String[] insuranceTariffIds, @Param("fromDate") String fromDate, @Param("toDate") String toDate);


	@Query("select e from CounselingSession e where e.consultant.person.id = :personId and e.end is null")
	CounselingSession currentSession(String personId);

	/*@Query("select e from Customer e where exists (select c from CounselingSession c where  e.id = c.customer.id and c.consultant.person.id = :consultantPersonId ) "
			+ " and :search is null  or e.nationalCode like CONCAT('%',:search,'%') or CONCAT(e.firstname,' ', e.lastname) like CONCAT('%',:search,'%')")*/
	@Query("select distinct e.client from CounselingSession e where e.consultant.person.id = :consultantPersonId and	"
			+ "  (:search is null  or e.client.nationalCode like CONCAT('%',:search,'%') or CONCAT(e.client.firstName,' ', e.client.lastName) like CONCAT('%',:search,'%'))")
	Page<Client> consultantCustomers(@Param("consultantPersonId") String consultantPersonId, @Param("search") String search, Pageable pageable);


	@Query("select e.id as id,e.sessionDescription as sessionDescription, e.nextMeetingAgenda as nextMeetingAgenda, e.start as sessionDate from CounselingSession e where e.consultant.person.id = :consultantId and e.client.id = :customerId order by e.start desc ")
	List<ISessionDescriptionsDto> sessionDescriptions(@Param("consultantId") String consultantId, @Param("customerId") String customerId);
}
