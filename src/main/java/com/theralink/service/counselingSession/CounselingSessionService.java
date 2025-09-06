package com.theralink.service.counselingSession;

import com.core.framework.domain.BaseInformation;
import com.core.framework.domain.user.Role;
import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.core.framework.service.baseInformation.IBaseInformationService;
import com.core.framework.utils.DateUtility;
import com.core.framework.utils.SecurityUtil;
import com.theralink.domain.CounselingSession;
import com.theralink.domain.client.model.Client;
import com.theralink.domain.InsuranceTariff;
import com.theralink.domain.Setting;
import com.theralink.repository.counselingSession.ICounselingSessionRepository;
import com.theralink.service.insuranceTariff.IInsuranceTariffService;
import com.theralink.service.personnel.IPersonnelService;
import com.theralink.service.setting.ISettingService;
import com.theralink.web.dto.ConsultantSessionSumDto;
import com.theralink.web.dto.NumberOfCustomerSessionDto;
import com.theralink.web.dto.ISessionDescriptionsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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
	private ISettingService iSettingService;

	@Autowired
	private IInsuranceTariffService iInsuranceTariffService;

	@Override
	protected IGenericRepository<CounselingSession, String> getGenericRepo() {
		return iCounselingSessionRepository;
	}

	@Override
	public Page<CounselingSession> customerSessionHistory(String customerId, boolean withAuthorize, Pageable pageable) {
		List<Role> authenticatedUserRoles = SecurityUtil.getAuthenticatedUserRoles();
		if (withAuthorize) {
			String personId = SecurityUtil.getAuthenticatedUser().getPerson().getId();
			return iCounselingSessionRepository.customerSessionHistory(customerId, personId, pageable);
		}
		else {
			return iCounselingSessionRepository.customerSessionHistory(customerId, null, pageable);
		}
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
		if (insuranceTariffId.equals("null")) {
			insuranceTariffId = "";
		}
		String[] insuranceTariffIds = insuranceTariffId.split(",");
		return iCounselingSessionRepository.consultantSessionHistory(personnelId, insuranceTariffIds, DateUtility.jalaliToMiladi(from), DateUtility.jalaliToMiladi(to), pageable);
	}

	@Override
	public CounselingSession currentSession() {
		String personId = SecurityUtil.getAuthenticatedUser().getPerson().getId();
		return iCounselingSessionRepository.currentSession(personId);
	}

	@Override
	public Page<Client> consultantCustomers(String personnelId, String search, Pageable pageable) {
		String consultantId = personnelId;
		if (personnelId == null) {

			consultantId = SecurityUtil.getAuthenticatedUser().getPerson().getId();
		}
		return iCounselingSessionRepository.consultantCustomers(consultantId, search, pageable);
	}

	@Override
	public List<ISessionDescriptionsDto> sessionDescriptions(String customerId) {
		String consultantId = SecurityUtil.getAuthenticatedUser().getPerson().getId();
		return iCounselingSessionRepository.sessionDescriptions(consultantId, customerId);
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
		if (insuranceTariffId.equals("null")) {
			insuranceTariffId = "";
		}
		String[] insuranceTariffIds = insuranceTariffId.split(",");
		Float consultantFeeSum = iCounselingSessionRepository.consultantFeeSum(personnelId, insuranceTariffIds, DateUtility.jalaliToMiladi(from), DateUtility.jalaliToMiladi(to));
		Float customerFeeSum = iCounselingSessionRepository.customerFeeSum(personnelId, insuranceTariffIds, DateUtility.jalaliToMiladi(from), DateUtility.jalaliToMiladi(to));
		Long sessionTimeSum = iCounselingSessionRepository.sessionTimeSum(personnelId, insuranceTariffIds, DateUtility.jalaliToMiladi(from), DateUtility.jalaliToMiladi(to));
		return new ConsultantSessionSumDto(consultantFeeSum, customerFeeSum, sessionTimeSum);
	}

	@Transactional
	@Override
	public String save(CounselingSession entity) {
		if (entity.getStart() == null) {
			entity.setStart(new Date());
			entity.setEnd(null);
		}
		boolean isCompletedSession = entity.getStart() != null && entity.getEnd() != null;
		if (isCompletedSession) {
			long duration = getDuration(entity.getStart(), entity.getEnd());
			Integer sessionTime = iInsuranceTariffService.load(entity.getInsuranceTariff().getId()).getSessionTime();
			entity.setSessionCount((float) (duration / sessionTime.floatValue()));
		}

		String save = super.save(entity);

		if (isCompletedSession) {
			saveFee(entity);
		}
		return save;
	}

	private long getDuration(Date start, Date end) {
		long diff = 0;
		if (end == null) {
			Date current = new Date();
			diff = current.getTime() - start.getTime();
		}
		else {
			diff = end.getTime() - start.getTime();
		}

		long diffMinutes = (diff / 1000) / 60;
		return diffMinutes;
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
		long duration = getDuration(session.getStart(), session.getEnd());
		Integer sessionTime = iInsuranceTariffService.load(session.getInsuranceTariff().getId()).getSessionTime();
		session.setSessionCount((float) (duration / sessionTime.floatValue()));
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

		Setting maxConsultantWagesPerSession = iSettingService.loadByKey("MAX_CONSULTANT_WAGES_PER_SESSION");
		Setting maxSessionPrice = iSettingService.loadByKey("MAX_SESSION_PRICE");
		if (maxConsultantWagesPerSession != null && !maxConsultantWagesPerSession.getValue().isEmpty()) {
			float maxVal = Float.parseFloat(maxConsultantWagesPerSession.getValue());
			if (maxVal >= 0) {
				if (sessionDuration * paymentTariff > maxVal) {
					session.setConsultantFee(maxVal);
				}
			}
		}
		else {
			session.setConsultantFee(sessionDuration * paymentTariff);
		}

		if (maxSessionPrice != null && !maxSessionPrice.getValue().isEmpty()) {
			float maxVal = Float.parseFloat(maxSessionPrice.getValue());
			if (maxVal >= 0) {
				if ((sessionDuration * insuranceTariff.getCustomerReceivedFactor()) * 10000 > maxVal) {
					session.setCustomerFee(maxVal);
				}
			}
		}
		else {
			session.setCustomerFee((sessionDuration * insuranceTariff.getCustomerReceivedFactor()) * 10000);
		}
		super.save(session);
		return true;
	}

	private long calculationTime(CounselingSession session) {
		long diff = session.getEnd().getTime() - session.getStart().getTime();
		long diffMinutes = (diff / 1000) / 60;
		return diffMinutes;
	}
}
