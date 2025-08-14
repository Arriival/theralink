package com.theralink.web.controller;

import com.core.framework.common.mapping.ModelMapperUtil;
import com.core.framework.web.controller.BaseController;
import com.theralink.domain.Client;
import com.theralink.service.customer.ICustomerService;
import com.theralink.web.viewModel.CustomerFileInfoViewModel;
import com.theralink.web.viewModel.CustomerViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController extends BaseController {

	@Autowired
	private ICustomerService iCustomerService;

	@GetMapping(value = "/grid")
	public Page<CustomerViewModel> pagination(Pageable pageable, String searchTxt) {
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().descending());
		return ModelMapperUtil.mapPage(iCustomerService.getAllGrid(pageable, searchTxt), CustomerViewModel.class);
	}

	@GetMapping(value = "/load/{id}")
	public CustomerViewModel load(@PathVariable String id) {
		return ModelMapperUtil.map(iCustomerService.load(id), CustomerViewModel.class);
	}

	@GetMapping(value = "/file/{id}")
	public CustomerFileInfoViewModel file(@PathVariable String id) {
		return ModelMapperUtil.map(iCustomerService.load(id), CustomerFileInfoViewModel.class);
	}

	@GetMapping(value = "/find")
	public CustomerViewModel find(String nationalCode) {
		return ModelMapperUtil.map(iCustomerService.find(nationalCode), CustomerViewModel.class);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public String save(@RequestBody CustomerViewModel entity) {
		return iCustomerService.save(ModelMapperUtil.map(entity, Client.class));
	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return iCustomerService.deleteById(id);
	}

	@GetMapping(value = "/count")
	public long count() {
		return iCustomerService.count();
	}

}
