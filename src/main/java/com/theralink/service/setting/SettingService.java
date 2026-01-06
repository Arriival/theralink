package com.theralink.service.setting;

import com.theralink.common.annotations.ClinicFilter;
import com.theralink.domain.Setting;
import com.theralink.domain.clinic.model.Clinic;
import com.theralink.domain.clinic.service.IClinicService;
import com.theralink.repository.IGenericRepository;
import com.theralink.repository.setting.ISettingRepository;
import com.theralink.service.GenericService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService extends GenericService<Setting, String> implements ISettingService {

    @Autowired
    private ISettingRepository iSettingRepository;

    @Autowired
    private IClinicService iClinicService;

    @Override
    protected IGenericRepository<Setting, String> getGenericRepo() {
        return iSettingRepository;
    }

    @ClinicFilter
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

    @Override
    @ClinicFilter
    public List<Setting> list() {
        return iSettingRepository.list();
    }

    @Override
    public List<Setting> getAllByEmptyClinic() {
        return iSettingRepository.getAllByClinicIsNull();
    }

    public boolean syncSetting(){
        List<Setting> sampleSetting = getAllByEmptyClinic();
        List<Clinic> clinics = iClinicService.getAll();







        return true;
    }
}
