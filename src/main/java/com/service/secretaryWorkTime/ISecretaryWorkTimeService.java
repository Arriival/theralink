package com.service.secretaryWorkTime;

import com.core.framework.service.IGenericService;
import com.domain.SecretaryWorkTime;
import com.domain.Setting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISecretaryWorkTimeService extends IGenericService<SecretaryWorkTime, String> {
    SecretaryWorkTime loadUnFinishedActivity();

    boolean setActivity();

	Page<SecretaryWorkTime> getAllGrid(String personnelId, Pageable pageable);
}
