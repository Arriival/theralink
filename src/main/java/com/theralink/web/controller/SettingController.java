package com.theralink.web.controller;

import com.theralink.common.mapping.ModelMapperUtil;
import com.theralink.web.controller.BaseController;
import com.theralink.domain.Setting;
import com.theralink.service.setting.ISettingService;
import com.theralink.web.viewModel.SettingViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("setting")
public class SettingController extends BaseController {

	@Autowired
	private ISettingService iSettingService;

	@GetMapping(value = "/list")
	public List<SettingViewModel> list() {
		return ModelMapperUtil.mapList(iSettingService.list(), SettingViewModel.class);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public String save(@RequestBody SettingViewModel entity) {
		return iSettingService.save(ModelMapperUtil.map(entity, Setting.class));
	}

	@GetMapping(value = "/load/{id}")
	@ResponseBody
	public SettingViewModel load(@PathVariable String id) {
		return ModelMapperUtil.map(iSettingService.load(id), SettingViewModel.class);
	}

	@GetMapping(value = "/load/key/{key}")
	@ResponseBody
	public SettingViewModel loadByKey(@PathVariable String key) {
		return ModelMapperUtil.map(iSettingService.loadByKey(key), SettingViewModel.class);
	}

	@PostMapping(value = "/wage/save")
	@ResponseBody
	public String saveWage(@RequestBody SettingViewModel entity) {
		return iSettingService.saveWage(entity.getValue());
	}

	@GetMapping(value = "/wage/load")
	@ResponseBody
	public String loadWage() {
		return iSettingService.loadByKey("SECRETARY_WAGES_PER_HOUR").getValue();
	}

}
