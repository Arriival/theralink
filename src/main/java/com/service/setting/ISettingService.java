package com.service.setting;

import com.core.framework.service.IGenericService;
import com.domain.Setting;

public interface ISettingService extends IGenericService<Setting, String> {
    Setting loadByKey(String key);

    String saveWage(String value);
}
