package com.service.counselingSession;

import com.core.framework.service.IGenericService;
import com.domain.CounselingSession;
import com.domain.Customer;
import com.web.dto.ConsultantSessionSumDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICounselingSessionService extends IGenericService<CounselingSession, String> {
	ConsultantSessionSumDto consultantSessionSum(String personnelId, String fromDate, String toDate, String insuranceTariffId);

	List<CounselingSession> inProcessList();

	boolean finishSession(String id);

	Page<CounselingSession> customerSessionHistory(String customerId, Pageable pageable);

	Page<CounselingSession> consultantSessionHistory(String personnelId, String insuranceTariffId, String fromDate, String toDate, Pageable pageable);
}
