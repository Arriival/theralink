package com.theralink.web.controller;

import com.theralink.common.mapping.ModelMapperUtil;
import com.theralink.domain.Action;
import com.theralink.service.action.IActionService;
import com.theralink.web.controller.BaseController;
import com.theralink.web.viewModel.action.ActionViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("action")
public class ActionController extends BaseController {

    @Autowired
    private IActionService iActionService;

    @GetMapping(value = "/load/{id}")
    public ActionViewModel load(@PathVariable String id) {
        return ModelMapperUtil.map(iActionService.load(id), ActionViewModel.class);
    }

    @GetMapping(value = "/list")
    public List<ActionViewModel> list() {
        return ModelMapperUtil.mapList(iActionService.getAll(), ActionViewModel.class);
    }

    @PostMapping(value = "/save")
    public String save(@RequestBody ActionViewModel entity) {
        return iActionService.save(ModelMapperUtil.map(entity, Action.class));
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean delete(@PathVariable String id) {
        return iActionService.deleteById(id);
    }

}
