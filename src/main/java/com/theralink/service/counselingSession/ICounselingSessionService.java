package com.theralink.service.counselingSession;

import com.core.framework.service.IGenericService;
import com.theralink.domain.CounselingSession;
import com.theralink.domain.Client;
import com.theralink.web.dto.ConsultantSessionSumDto;
import com.theralink.web.dto.NumberOfCustomerSessionDto;
import com.theralink.web.dto.ISessionDescriptionsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICounselingSessionService extends IGenericService<CounselingSession, String> {
	ConsultantSessionSumDto consultantSessionSum(String personnelId, String fromDate, String toDate, String insuranceTariffId);

	List<CounselingSession> inProcessList();

	boolean finishSession(String id);

	Page<CounselingSession> customerSessionHistory(String customerId, boolean withAuthorize, Pageable pageable);

	NumberOfCustomerSessionDto numberOfCustomerSession(String customerId);

	Page<CounselingSession> consultantSessionHistory(String personnelId, String insuranceTariffId, String fromDate, String toDate, Pageable pageable);

	CounselingSession currentSession();

	Page<Client> consultantCustomers(String personnelId, String search, Pageable pageable);

	List<ISessionDescriptionsDto> sessionDescriptions(String customerId);
}
