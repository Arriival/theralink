package com.web.controller;

import com.core.framework.common.mapping.ModelMapperUtil;
import com.core.framework.web.controller.BaseController;
import com.domain.InsuranceTariff;
import com.service.insuranceTariff.IInsuranceTariffService;
import com.web.viewModel.InsuranceTariffViewModel;
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
        return ModelMapperUtil.mapList(iInsuranceTariffService.getAll(), InsuranceTariffViewModel.class);
    }

    @GetMapping(value = "/load/{id}")
    public InsuranceTariffViewModel load(@PathVariable String id) {
        return ModelMapperUtil.map(iInsuranceTariffService.load(id), InsuranceTariffViewModel.class);
    }

    @PostMapping(value = "/save")
    public String save(@RequestBody InsuranceTariffViewModel entity) {
        return iInsuranceTariffService.save(ModelMapperUtil.map(entity, InsuranceTariff.class));
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean delete(@PathVariable String id) {
        return iInsuranceTariffService.deleteById(id);
    }

}
