package com.theralink.web.controller;

import com.theralink.common.mapping.ModelMapperUtil;
import com.theralink.domain.Group;
import com.theralink.service.group.IGroupService;
import com.theralink.web.controller.BaseController;
import com.theralink.web.viewModel.group.GroupViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("group")
public class GroupController extends BaseController {
    @Autowired
    private IGroupService iGroupService;

    @GetMapping(value = "/load/{id}")
    public GroupViewModel load(@PathVariable String id) {
        return ModelMapperUtil.map(iGroupService.load(id), GroupViewModel.class);
    }

    @GetMapping(value = "/list")
    public List<GroupViewModel> page() {
        return ModelMapperUtil.mapList(iGroupService.getAll(), GroupViewModel.class);
    }

    @GetMapping(value = "/grid")
    public Page<GroupViewModel> page(Pageable pageable) {
        return ModelMapperUtil.mapPage(iGroupService.getAllGrid(pageable), GroupViewModel.class);
    }

    @PostMapping(value = "/save")
    public String save(@RequestBody GroupViewModel entity) {
        return iGroupService.save(ModelMapperUtil.map(entity, Group.class));
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean delete(@PathVariable String id) {
        return iGroupService.deleteById(id);
    }

}
