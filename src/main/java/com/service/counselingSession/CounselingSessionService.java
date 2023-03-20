package com.service.counselingSession;

import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.core.framework.utils.DateUtility;
import com.domain.CounselingSession;
import com.domain.Customer;
import com.domain.InsuranceTariff;
import com.repository.counselingSession.ICounselingSessionRepository;
import com.repository.customer.ICustomerRepository;
import com.service.customer.ICustomerService;
import com.service.insuranceTariff.IInsuranceTariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;

@Service
public class CounselingSessionService extends GenericService<CounselingSession, String> implements ICounselingSessionService {

	@Autowired
	private ICounselingSessionRepository iCounselingSessionRepository;

	@Autowired
	private IInsuranceTariffService iInsuranceTariffService;

	@Override
	protected IGenericRepository<CounselingSession, String> getGenericRepo() {
		return iCounselingSessionRepository;
	}

	@Override
	public Page<CounselingSession> customerSessionHistory(String customerId, Pageable pageable) {
		return iCounselingSessionRepository.customerSessionHistory(customerId, pageable);
	}

	@Override
	public Page<CounselingSession> consultantSessionHistory(String personnelId, String fromDate, String toDate, Pageable pageable) {
		String from = fromDate, to = toDate;
		if (fromDate.equals("")) {
			from = null;
		}
		if (toDate.equals("")) {
			to = null;
		}
		return iCounselingSessionRepository.consultantSessionHistory(personnelId, DateUtility.jalaliToMiladi(from), DateUtility.jalaliToMiladi(to), pageable);
	}

	@Transactional
	@Override
	public String save(CounselingSession entity) {
		entity.setStart(new Date());
		return super.save(entity);
	}

	@Override
	public List<CounselingSession> inProcessList() {
		return iCounselingSessionRepository.inProcessList();
	}

	@Transactional
	@Override
	public boolean finishSession(String id) {
		CounselingSession session = load(id);
		session.setEnd(new Date());
		long sessionDuration = calculationTime(session);
		InsuranceTariff insuranceTariff = session.getInsuranceTariff();
		session.setConsultantFee(sessionDuration * insuranceTariff.getConsultantPaymentFactor());
		session.setCustomerFee((sessionDuration * insuranceTariff.getCustomerReceivedFactor()) * 10000);

		/*if (insuranceTariff.getCustomerReceivedFactor() == null || insuranceTariff.getCustomerReceivedFactor() == 0) {
			session.setCustomerFee(0F);
		}
		else {
			session.setCustomerFee((sessionDuration * insuranceTariff.getCustomerReceivedFactor()) * 10000);
		}*/
		String save = super.save(session);
		return true;
	}

	private long calculationTime(CounselingSession session) {
		long diff = session.getEnd().getTime() - session.getStart().getTime();
		long diffMinutes = (diff / 1000) / 60;
		return diffMinutes;
	}
}
