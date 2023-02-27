package com.web.controller;

import com.core.framework.web.controller.BaseController;
import com.service.setting.ISettingService;
import com.web.viewModel.SettingViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("setting")
public class SettingController extends BaseController {

    @Autowired
    private ISettingService iSettingService;

    @PostMapping(value = "/wage/save")
    @ResponseBody
    public String save(@RequestBody SettingViewModel entity) {
        return iSettingService.saveWage(entity.getValue());
    }

    @GetMapping(value = "/wage/load")
    @ResponseBody
    public String load() {
        return iSettingService.loadByKey("SECRETARY_WAGES_PER_HOUR").getValue();
    }

}
