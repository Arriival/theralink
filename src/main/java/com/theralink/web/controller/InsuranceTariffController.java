package com.theralink.web.controller;

import com.theralink.common.mapping.ModelMapperUtil;
import com.theralink.domain.InsuranceTariff;
import com.theralink.service.insuranceTariff.IInsuranceTariffService;
import com.theralink.web.dto.IChartDto;
import com.theralink.web.viewModel.InsuranceTariffViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("insuranceTariff")
public class InsuranceTariffController extends BaseController {

	@Autowired
	private IInsuranceTariffService iInsuranceTariffService;

	@GetMapping(value = "/list")
	public List<InsuranceTariffViewModel> listAll() {
		return ModelMapperUtil.mapList(iInsuranceTariffService.getAllByClinicFilter(), InsuranceTariffViewModel.class);
	}

	@GetMapping(value = "/list/combo")
	public List<InsuranceTariffViewModel> getAllEnableList() {
		return ModelMapperUtil.mapList(iInsuranceTariffService.getAllEnableList(), InsuranceTariffViewModel.class);
	}

	@GetMapping(value = "/load/{id}")
	public InsuranceTariffViewModel load(@PathVariable String id) {
		return ModelMapperUtil.map(iInsuranceTariffService.authorizedLoad(id), InsuranceTariffViewModel.class);
	}

	@GetMapping(value = "/toggle/{id}")
	public Boolean toggleStatus(@PathVariable String id) {
		return iInsuranceTariffService.toggleStatus(id);
	}

	@PostMapping(value = "/save")
	public String save(@RequestBody InsuranceTariffViewModel entity) {
		return iInsuranceTariffService.save(ModelMapperUtil.map(entity, InsuranceTariff.class));
	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return iInsuranceTariffService.deleteByAuthorizedId(id);
	}

	@GetMapping(value = "/chart")
	public List<IChartDto> chart(String from, String to) {
		return iInsuranceTariffService.chart(from, to);
	}

}
