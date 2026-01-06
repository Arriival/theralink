package com.theralink.web.controller;

import com.theralink.common.mapping.ModelMapperUtil;
import com.theralink.web.controller.BaseController;
import com.theralink.domain.SecretaryWorkTime;
import com.theralink.service.secretaryWorkTime.ISecretaryWorkTimeService;
import com.theralink.web.dto.TimeDto;
import com.theralink.web.viewModel.ConsultantTypeViewModel;
import com.theralink.web.viewModel.SecretaryWorkTimeViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("secretaryWorkTime")
public class SecretaryWorkTimeController extends BaseController {

	@Autowired
	private ISecretaryWorkTimeService iSecretaryWorkTimeService;

	@GetMapping(value = "/grid/{personnelId}")
	public Page<SecretaryWorkTimeViewModel> pagination(@PathVariable String personnelId, String fromDate, String toDate, Pageable pageable) {
		return ModelMapperUtil.mapPage(iSecretaryWorkTimeService.getAllGrid(personnelId, fromDate, toDate, pageable), SecretaryWorkTimeViewModel.class);
	}

	@GetMapping(value = "/salary/sum/{personnelId}")
	public Float sumSalary(@PathVariable String personnelId, String fromDate, String toDate) {
		return iSecretaryWorkTimeService.sumSalary(personnelId, fromDate, toDate);
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

	@PostMapping(value = "/activity")
	@ResponseBody
	public boolean setActivity(@RequestBody TimeDto timeDto) {
		return iSecretaryWorkTimeService.setActivity(timeDto.getStart(), timeDto.getEnd());
	}
}
