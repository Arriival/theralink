package com.theralink.web.controller;

import com.theralink.common.mapping.ModelMapperUtil;
import com.theralink.web.controller.BaseController;
import com.theralink.domain.ConsultantType;
import com.theralink.domain.Personnel;
import com.theralink.domain.PersonnelType;
import com.theralink.service.consultantType.IConsultantTypeService;
import com.theralink.service.personnel.IPersonnelService;
import com.theralink.web.viewModel.ConsultantTypeViewModel;
import com.theralink.web.viewModel.PersonnelViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("consultantType")
public class ConsultantTypeController extends BaseController {

    @Autowired
    private IConsultantTypeService iConsultantTypeService;

    @GetMapping(value = "/grid")
    public Page<ConsultantTypeViewModel> pagination(Pageable pageable) {
        return ModelMapperUtil.mapPage(iConsultantTypeService.getAllGrid(pageable), ConsultantTypeViewModel.class);
    }

    @GetMapping(value = "/list")
    public List<ConsultantTypeViewModel> listAll() {
        return ModelMapperUtil.mapList(iConsultantTypeService.getAllList(), ConsultantTypeViewModel.class);
    }

    @GetMapping(value = "/load/{id}")
    public ConsultantTypeViewModel load(@PathVariable String id) {
        return ModelMapperUtil.map(iConsultantTypeService.load(id), ConsultantTypeViewModel.class);
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public String save(@RequestBody ConsultantTypeViewModel entity) {
        return iConsultantTypeService.save(ModelMapperUtil.map(entity, ConsultantType.class));
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean delete(@PathVariable String id) {
        return iConsultantTypeService.deleteById(id);
    }
}
