package com.theralink.web.controller;

import com.theralink.common.mapping.ModelMapperUtil;
import com.theralink.web.controller.BaseController;
import com.theralink.domain.CustomerGenogramInformation;
import com.theralink.service.customerGenogramInformation.ICustomerGenogramInformationService;
import com.theralink.web.viewModel.CustomerGenogramInformationViewModel;
import com.theralink.web.viewModel.CustomerLevelingViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genogramInfo")
public class CustomerGenogramInformationController extends BaseController {

	@Autowired
	private ICustomerGenogramInformationService iCustomerGenogramInformationService;

	@GetMapping(value = "/list/{customerId}")
	public List<CustomerGenogramInformationViewModel> list(@PathVariable String customerId) {
		return ModelMapperUtil.mapList(iCustomerGenogramInformationService.list(customerId), CustomerGenogramInformationViewModel.class);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public Integer save(@RequestBody List<CustomerGenogramInformationViewModel> entity) {
		return iCustomerGenogramInformationService.saveList(ModelMapperUtil.mapList(entity, CustomerGenogramInformation.class));
	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return iCustomerGenogramInformationService.deleteById(id);
	}

}
