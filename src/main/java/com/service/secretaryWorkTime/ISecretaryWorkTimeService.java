package com.service.secretaryWorkTime;

import com.core.framework.service.IGenericService;
import com.domain.SecretaryWorkTime;
import com.domain.Setting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISecretaryWorkTimeService extends IGenericService<SecretaryWorkTime, String> {
	Page<SecretaryWorkTime> getAllGrid(String personnelId, String fromDate, String toDate, Pageable pageable);

	SecretaryWorkTime loadUnFinishedActivity();

    boolean setActivity();

	Float sumSalary(String personnelId, String fromDate, String toDate);
}
