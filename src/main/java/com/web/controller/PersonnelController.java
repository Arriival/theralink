package com.web.controller;

import com.core.framework.common.mapping.ModelMapperUtil;
import com.core.framework.web.controller.BaseController;
import com.core.framework.web.viewModel.person.PersonViewModel;
import com.domain.Personnel;
import com.domain.PersonnelType;
import com.service.personnel.IPersonnelService;
import com.web.viewModel.PersonnelViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("personnel")
public class PersonnelController extends BaseController {

    @Autowired
    private IPersonnelService iPersonnelService;

    @GetMapping(value = "/grid/{type}")
    public Page<PersonnelViewModel> pagination(@PathVariable PersonnelType type, Pageable pageable) {
        return ModelMapperUtil.mapPage(iPersonnelService.getAllGrid(type, pageable), PersonnelViewModel.class);
    }

    @GetMapping(value = "/load/{id}")
    public PersonnelViewModel load(@PathVariable String id) {
        return ModelMapperUtil.map(iPersonnelService.load(id), PersonnelViewModel.class);
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public String save(@RequestBody PersonnelViewModel entity) {
        return iPersonnelService.save(ModelMapperUtil.map(entity, Personnel.class));
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean delete(@PathVariable String id) {
        return iPersonnelService.deleteById(id);
    }
}
