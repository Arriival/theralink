package com.web.controller;

import com.core.framework.common.mapping.ModelMapperUtil;
import com.core.framework.web.controller.BaseController;
import com.domain.SecretaryWorkTime;
import com.service.secretaryWorkTime.ISecretaryWorkTimeService;
import com.web.viewModel.ConsultantTypeViewModel;
import com.web.viewModel.SecretaryWorkTimeViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("secretaryWorkTime")
public class SecretaryWorkTimeController extends BaseController {

	@Autowired
	private ISecretaryWorkTimeService iSecretaryWorkTimeService;

	@GetMapping(value = "/grid/{personnelId}")
	public Page<SecretaryWorkTimeViewModel> pagination(@PathVariable String personnelId, Pageable pageable) {
		return ModelMapperUtil.mapPage(iSecretaryWorkTimeService.getAllGrid(personnelId, pageable), SecretaryWorkTimeViewModel.class);
	}

	@GetMapping(value = "/load/{id}")
	public SecretaryWorkTimeViewModel load(@PathVariable String id) {
		return ModelMapperUtil.map(iSecretaryWorkTimeService.load(id), SecretaryWorkTimeViewModel.class);
	}

	@GetMapping(value = "/load/activity")
	public SecretaryWorkTimeViewModel incompleteActivity() {
		return ModelMapperUtil.map(iSecretaryWorkTimeService.loadUnFinishedActivity(), SecretaryWorkTimeViewModel.class);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public String save(@RequestBody ConsultantTypeViewModel entity) {
		return iSecretaryWorkTimeService.save(ModelMapperUtil.map(entity, SecretaryWorkTime.class));
	}

	@GetMapping(value = "/activity")
	@ResponseBody
	public boolean setActivity() {
		return iSecretaryWorkTimeService.setActivity();
	}
}
