package com.service.setting;

import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.domain.Setting;
import com.repository.setting.ISettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SettingService extends GenericService<Setting, String> implements ISettingService {

    @Autowired
    private ISettingRepository iSettingRepository;

    @Override
    protected IGenericRepository<Setting, String> getGenericRepo() {
        return iSettingRepository;
    }

    @Override
    public Setting loadByKey(String key) {
        return iSettingRepository.loadByKey(key);
    }

    @Override
    @Transactional
    public String saveWage(String value) {
        Setting wages = loadByKey("SECRETARY_WAGES_PER_HOUR");
        if (wages != null) {
            wages.setValue(value);
            return super.save(wages);
        } else {
            Setting newWage = new Setting();
            newWage.setKey("SECRETARY_WAGES_PER_HOUR");
            newWage.setValue(value);
            return super.save(newWage);
        }
    }
}
