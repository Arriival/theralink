package com.web.controller;

import com.core.framework.common.mapping.ModelMapperUtil;
import com.core.framework.web.controller.BaseController;
import com.domain.Customer;
import com.domain.CustomerLeveling;
import com.service.customer.ICustomerService;
import com.service.customerLeveling.ICustomerLevelingService;
import com.web.dto.ICustomerPriorityDto;
import com.web.viewModel.CustomerLevelingViewModel;
import com.web.viewModel.CustomerViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customerLeveling")
public class CustomerLevelingController extends BaseController {

	@Autowired
	private ICustomerLevelingService iCustomerLevelingService;

	@GetMapping(value = "/grid")
	public Page<CustomerLevelingViewModel> pagination(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().descending());
		return ModelMapperUtil.mapPage(iCustomerLevelingService.getAllGrid(pageable), CustomerLevelingViewModel.class);
	}

	@GetMapping(value = "/list")
	public List<CustomerLevelingViewModel> list() {
		return ModelMapperUtil.mapList(iCustomerLevelingService.list(), CustomerLevelingViewModel.class);
	}

	@GetMapping(value = "/customer/list")
	public List<ICustomerPriorityDto> priorityDtoList() {
		return iCustomerLevelingService.priorityCustomerList();
	}

	@GetMapping(value = "/load/{id}")
	public CustomerLevelingViewModel load(@PathVariable String id) {
		return ModelMapperUtil.map(iCustomerLevelingService.load(id), CustomerLevelingViewModel.class);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public String save(@RequestBody CustomerLevelingViewModel entity) {
		return iCustomerLevelingService.save(ModelMapperUtil.map(entity, CustomerLeveling.class));
	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return iCustomerLevelingService.deleteById(id);
	}

}
