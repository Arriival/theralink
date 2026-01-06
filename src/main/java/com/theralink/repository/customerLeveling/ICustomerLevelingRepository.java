package com.theralink.repository.customerLeveling;

import com.theralink.repository.IGenericRepository;
import com.theralink.domain.CustomerLeveling;
import com.theralink.web.dto.ICustomerPriorityDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerLevelingRepository extends IGenericRepository<CustomerLeveling, String> {

	@Query("select e from CustomerLeveling e order by e.duration asc ")
	List<CustomerLeveling> list();

	@Query(value = "SELECT DATE_ADD((SELECT MAX(S.END) FROM MAC_COUNSELING_SESSION S WHERE S.CUSTOMER_ID = C.ID), INTERVAL l.duration DAY) as nextVisit,"
			+ " (SELECT MAX(S.END) FROM MAC_COUNSELING_SESSION S WHERE S.CUSTOMER_ID = C.ID) as lastVisit, "
			+ " C.ID as customerId, "
			+ " C.firstname as firstName, "
			+ " C.lastname as lastName, "
			+ " C.national_Code as nationalCode, "
			+ " C.phone as phone, "
			+ " L.duration as levelDuration, "
			+ " L.title as levelTitle "
			+ " FROM MAC_CLIENT C "
			+ " INNER JOIN MAC_CUSTOMER_LEVELING L ON L.ID = C.CUSTOMER_LEVELING "
			+ " WHERE C.CLINIC_ID = :clinicId AND  DATE_ADD((SELECT MAX(S.END) FROM MAC_COUNSELING_SESSION S WHERE S.CUSTOMER_ID = C.ID), INTERVAL L.duration DAY) >= CURRENT_DATE() "
			+ "order by nextVisit asc " , nativeQuery = true)
	List<ICustomerPriorityDto> priorityCustomerList(String clinicId);
}
