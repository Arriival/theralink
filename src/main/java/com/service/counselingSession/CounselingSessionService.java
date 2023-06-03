package com.service.counselingSession;

import com.core.framework.domain.baseInformation.BaseInformation;
import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.core.framework.service.baseInformation.IBaseInformationService;
import com.core.framework.utils.DateUtility;
import com.domain.CounselingSession;
import com.domain.InsuranceTariff;
import com.repository.counselingSession.ICounselingSessionRepository;
import com.service.insuranceTariff.IInsuranceTariffService;
import com.service.personnel.IPersonnelService;
import com.web.dto.ConsultantSessionSumDto;
import com.web.dto.NumberOfCustomerSessionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class CounselingSessionService extends GenericService<CounselingSession, String> implements ICounselingSessionService {

	@Autowired
	private ICounselingSessionRepository iCounselingSessionRepository;

	@Autowired
	private IBaseInformationService iBaseInformationService;

	@Autowired
	private IPersonnelService iPersonnelService;

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
	public NumberOfCustomerSessionDto numberOfCustomerSession(String customerId) {
		Date firstMonth = new Date(DateUtility.getFirstMonthTimestamp().getTime());
		Float inMonth = iCounselingSessionRepository.numberOfCustomerSession(customerId, firstMonth);
		Float total = iCounselingSessionRepository.numberOfCustomerSession(customerId, null);
		NumberOfCustomerSessionDto dto = new NumberOfCustomerSessionDto();
		dto.setInMonth(inMonth == null ? 0 : inMonth);
		dto.setTotal(total == null ? 0 : total);
		return dto;
	}

	@Override
	public Page<CounselingSession> consultantSessionHistory(String personnelId, String insuranceTariffId, String fromDate, String toDate, Pageable pageable) {
		String from = fromDate, to = toDate;
		if (fromDate.equals("")) {
			from = null;
		}
		if (toDate.equals("")) {
			to = null;
		}
		return iCounselingSessionRepository.consultantSessionHistory(personnelId, insuranceTariffId, DateUtility.jalaliToMiladi(from), DateUtility.jalaliToMiladi(to), pageable);
	}

	@Override
	public ConsultantSessionSumDto consultantSessionSum(String personnelId, String fromDate, String toDate, String insuranceTariffId) {
		String from = fromDate, to = toDate;
		if (fromDate.equals("")) {
			from = null;
		}
		if (toDate.equals("")) {
			to = null;
		}
		Float consultantFeeSum = iCounselingSessionRepository.consultantFeeSum(personnelId, insuranceTariffId, DateUtility.jalaliToMiladi(from), DateUtility.jalaliToMiladi(to));
		Float customerFeeSum = iCounselingSessionRepository.customerFeeSum(personnelId, insuranceTariffId, DateUtility.jalaliToMiladi(from), DateUtility.jalaliToMiladi(to));
		Long sessionTimeSum = iCounselingSessionRepository.sessionTimeSum(personnelId, insuranceTariffId, DateUtility.jalaliToMiladi(from), DateUtility.jalaliToMiladi(to));
		return new ConsultantSessionSumDto(consultantFeeSum, customerFeeSum, sessionTimeSum);
	}

	@Transactional
	@Override
	public String save(CounselingSession entity) {
		System.out.println(entity.getStart());
		System.out.println(entity.getEnd());
		if (entity.getStart() == null) {
			entity.setStart(new Date());
			entity.setEnd(null);
		}
		String save = super.save(entity);
		if (entity.getStart() != null && entity.getEnd() != null) {
			saveFee(entity);
		}
		return save;
	}

	@Override
	public List<CounselingSession> inProcessList() {
		return iCounselingSessionRepository.inProcessList();
	}

	@Transactional
	@Override
	public boolean finishSession(String sessionId) {
		CounselingSession session = load(sessionId);
		if (session.getEnd() == null) {
			session.setEnd(new Date());
		}
		return saveFee(session);
	}

	private boolean saveFee(CounselingSession session) {
		long sessionDuration = calculationTime(session);
		InsuranceTariff insuranceTariff = iInsuranceTariffService.load(session.getInsuranceTariff().getId());
		BaseInformation educationLevel = iPersonnelService.load(session.getConsultant().getId()).getPerson().getEducationLevel();
		float paymentTariff = 0;
		switch (educationLevel.getCode()) {
		case "1":
			paymentTariff = insuranceTariff.getLisancPaymentFactor();
			break;
		case "2":
			paymentTariff = insuranceTariff.getArshadPaymentFactor();
			break;
		case "3":
			paymentTariff = insuranceTariff.getDrStdPaymentFactor();
			break;
		case "4":
			paymentTariff = insuranceTariff.getDrPaymentFactor();
			break;
		case "5":
			paymentTariff = insuranceTariff.getPostDrPaymentFactor();
			break;
		}
		session.setConsultantFee(sessionDuration * paymentTariff);
		session.setCustomerFee((sessionDuration * insuranceTariff.getCustomerReceivedFactor()) * 10000);
		String save = super.save(session);
		return true;
	}

	private long calculationTime(CounselingSession session) {
		long diff = session.getEnd().getTime() - session.getStart().getTime();
		long diffMinutes = (diff / 1000) / 60;
		return diffMinutes;
	}
}
