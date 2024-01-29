package com.web.controller;

import com.core.framework.common.mapping.ModelMapperUtil;
import com.core.framework.web.controller.BaseController;
import com.domain.References;
import com.service.references.IReferencesService;
import com.web.viewModel.ReferencesViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("references")
public class ReferencesController extends BaseController {

	@Autowired
	private IReferencesService iReferencesService;

	@GetMapping(value = "/referredTo/grid")
	public Page<ReferencesViewModel> referredTo(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().descending());
		return ModelMapperUtil.mapPage(iReferencesService.referredToGrid(pageable), ReferencesViewModel.class);
	}

	@GetMapping(value = "/referredFrom/grid")
	public Page<ReferencesViewModel> referredFrom(String customerId, Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().descending());
		return ModelMapperUtil.mapPage(iReferencesService.referredFromGrid(customerId,pageable), ReferencesViewModel.class);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public String save(@RequestBody ReferencesViewModel entity) {
		return iReferencesService.save(ModelMapperUtil.map(entity, References.class));
	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return iReferencesService.deleteById(id);
	}

	@GetMapping(value = "/visit/{id}")
	@ResponseBody
	public String visit(@PathVariable String id) {
		return iReferencesService.visit(id);
	}

}
