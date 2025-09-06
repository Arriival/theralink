package com.theralink.domain.client.controller;

import com.core.framework.common.mapping.ModelMapperUtil;
import com.core.framework.web.controller.BaseController;
import com.theralink.domain.client.model.Client;
import com.theralink.domain.client.service.IClientService;
import com.theralink.web.viewModel.CustomerFileInfoViewModel;
import com.theralink.domain.client.dto.ClientViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class ClientController extends BaseController {

	@Autowired
	private IClientService iCustomerService;

	@GetMapping(value = "/grid")
	public Page<ClientViewModel> pagination(Pageable pageable, String searchTxt) {
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().descending());
		return ModelMapperUtil.mapPage(iCustomerService.getAllGrid(pageable, searchTxt), ClientViewModel.class);
	}

	@GetMapping(value = "/load/{id}")
	public ClientViewModel load(@PathVariable String id) {
		return ModelMapperUtil.map(iCustomerService.load(id), ClientViewModel.class);
	}

	@GetMapping(value = "/file/{id}")
	public CustomerFileInfoViewModel file(@PathVariable String id) {
		return ModelMapperUtil.map(iCustomerService.load(id), CustomerFileInfoViewModel.class);
	}

	@GetMapping(value = "/find")
	public ClientViewModel find(String nationalCode) {
		return ModelMapperUtil.map(iCustomerService.find(nationalCode), ClientViewModel.class);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public String save(@RequestBody ClientViewModel entity) {
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
