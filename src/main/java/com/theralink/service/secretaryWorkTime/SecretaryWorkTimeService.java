package com.theralink.service.secretaryWorkTime;

import com.theralink.common.annotations.ClinicFilter;
import com.theralink.repository.IGenericRepository;
import com.theralink.service.GenericService;

import com.theralink.utils.DateUtility;
import com.theralink.utils.SecurityUtil;
import com.theralink.domain.Personnel;
import com.theralink.domain.SecretaryWorkTime;
import com.theralink.repository.secretaryWorkTime.ISecretaryWorkTimeRepository;
import com.theralink.service.personnel.IPersonnelService;
import com.theralink.service.setting.ISettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Date;

@Service
public class SecretaryWorkTimeService extends GenericService<SecretaryWorkTime, String> implements ISecretaryWorkTimeService {

	@Autowired
	private ISecretaryWorkTimeRepository iSecretaryWorkTimeRepository;

	@Autowired
	private IPersonnelService iPersonnelService;

	@Autowired
	private ISettingService iSettingService;

	@Override
	protected IGenericRepository<SecretaryWorkTime, String> getGenericRepo() {
		return iSecretaryWorkTimeRepository;
	}

	@ClinicFilter
	@Override
	public Page<SecretaryWorkTime> getAllGrid(String personnelId, String fromDate, String toDate, Pageable pageable) {
		String from = fromDate, to = toDate;
		if (fromDate.equals("")) {
			from = null;
		}
		if (toDate.equals("")) {
			to = null;
		}
		return iSecretaryWorkTimeRepository.getAllGrid(personnelId, DateUtility.jalaliToMiladi(from), DateUtility.jalaliToMiladi(to), pageable);
	}

	@ClinicFilter
	@Override
	public SecretaryWorkTime loadUnFinishedActivity() {
		String authenticatedPersonId = SecurityUtil.getAuthenticatedUser().getPerson().getId();
		Personnel personnel = iPersonnelService.loadByPersonId(authenticatedPersonId);
		if (personnel == null) {
			return null;
		}
		return iSecretaryWorkTimeRepository.loadUnFinishedActivity(personnel.getId());
	}

	@ClinicFilter
	@Transactional
	@Override
	public boolean setActivity(Date start, Date end) {
		String authenticatedPersonId = SecurityUtil.getAuthenticatedUser().getPerson().getId();
		Personnel personnel = iPersonnelService.loadByPersonId(authenticatedPersonId);
		if (personnel == null) {
			return false;
		}
		SecretaryWorkTime unFinishedActivity = iSecretaryWorkTimeRepository.loadUnFinishedActivity(personnel.getId());
		if (unFinishedActivity == null) {
			SecretaryWorkTime workTime = new SecretaryWorkTime();
			workTime.setSecretary(personnel);
			workTime.setStart(start);
			return super.save(workTime) != null;
		}
		else {
			unFinishedActivity.setEnd(end);
			Long duration = DateUtility.differenceMin(unFinishedActivity.getStart(), unFinishedActivity.getEnd());
			unFinishedActivity.setSalary(Float.valueOf((duration) * personnel.getSecretaryHourlyWage() / 60));
			return super.save(unFinishedActivity) != null;
		}
	}

	@ClinicFilter
	@Override
	public Float sumSalary(String personnelId, String fromDate, String toDate) {
		String from = fromDate, to = toDate;
		if (fromDate.equals("")) {
			from = null;
		}
		if (toDate.equals("")) {
			to = null;
		}
		return iSecretaryWorkTimeRepository.salarySum(personnelId, DateUtility.jalaliToMiladi(from), DateUtility.jalaliToMiladi(to));
	}

}
