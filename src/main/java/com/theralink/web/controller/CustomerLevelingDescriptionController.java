package com.theralink.web.controller;

import com.theralink.common.mapping.ModelMapperUtil;
import com.theralink.web.controller.BaseController;
import com.theralink.domain.CustomerLevelingDescription;
import com.theralink.service.customerLevelingDescription.ICustomerLevelingDescriptionService;
import com.theralink.web.viewModel.CustomerLevelingDescriptionViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customerLevelingDescription")
public class CustomerLevelingDescriptionController extends BaseController {

	@Autowired
	private ICustomerLevelingDescriptionService iCustomerLevelingService;

	@GetMapping(value = "/grid")
	public Page<CustomerLevelingDescriptionViewModel> pagination(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().descending());
		return ModelMapperUtil.mapPage(iCustomerLevelingService.getAllGrid(pageable), CustomerLevelingDescriptionViewModel.class);
	}

	@GetMapping(value = "/list/{customerId}")
	public List<CustomerLevelingDescriptionViewModel> list(@PathVariable String customerId) {
		return ModelMapperUtil.mapList(iCustomerLevelingService.list(customerId), CustomerLevelingDescriptionViewModel.class);
	}

	@GetMapping(value = "/load/{id}")
	public CustomerLevelingDescriptionViewModel load(@PathVariable String id) {
		return ModelMapperUtil.map(iCustomerLevelingService.load(id), CustomerLevelingDescriptionViewModel.class);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public String save(@RequestBody CustomerLevelingDescriptionViewModel entity) {
		return iCustomerLevelingService.save(ModelMapperUtil.map(entity, CustomerLevelingDescription.class));
	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return iCustomerLevelingService.deleteById(id);
	}

}
