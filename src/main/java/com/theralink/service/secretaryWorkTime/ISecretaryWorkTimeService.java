package com.theralink.service.secretaryWorkTime;

import com.theralink.service.IGenericService;
import com.theralink.domain.SecretaryWorkTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface ISecretaryWorkTimeService extends IGenericService<SecretaryWorkTime, String> {
	Page<SecretaryWorkTime> getAllGrid(String personnelId, String fromDate, String toDate, Pageable pageable);

	SecretaryWorkTime loadUnFinishedActivity();


	boolean setActivity(Date start, Date end);

	Float sumSalary(String personnelId, String fromDate, String toDate);
}
