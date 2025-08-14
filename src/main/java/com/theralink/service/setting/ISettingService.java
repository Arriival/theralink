package com.theralink.service.setting;

import com.core.framework.service.IGenericService;
import com.theralink.domain.Setting;

import java.util.List;

public interface ISettingService extends IGenericService<Setting, String> {
    Setting loadByKey(String key);

    String saveWage(String value);

	List<Setting> list();
}
