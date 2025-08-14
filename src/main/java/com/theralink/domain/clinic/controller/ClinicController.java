package com.theralink.domain.clinic.controller;

import com.core.framework.common.mapping.ModelMapperUtil;
import com.core.framework.web.controller.BaseController;
import com.theralink.domain.clinic.dto.ClinicViewModel;
import com.theralink.domain.clinic.model.Clinic;
import com.theralink.domain.clinic.spec.ClinicFilterSpecification;
import com.theralink.domain.clinic.service.IClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clinic")
public class ClinicController extends BaseController {

	@Autowired
	private IClinicService iClinicService;

	@GetMapping(value = "/grid")
	public Page<ClinicViewModel> pagination(Pageable pageable, String searchTxt) {
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().descending());
		return ModelMapperUtil.mapPage(iClinicService.getAllGrid(pageable, searchTxt), ClinicViewModel.class);
	}

	@GetMapping(value = "/load/{id}")
	public ClinicViewModel load(@PathVariable String id) {
		return ModelMapperUtil.map(iClinicService.load(id), ClinicViewModel.class);
	}

	@GetMapping(value = "/list/all")
	public List<ClinicViewModel> getAll(String name) {
		return ModelMapperUtil.mapList(iClinicService.findAllByAuthorize(), ClinicViewModel.class);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public String save(@RequestBody ClinicViewModel entity) {
		return iClinicService.save(ModelMapperUtil.map(entity, Clinic.class));

	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return iClinicService.deleteById(id);
	}

}
