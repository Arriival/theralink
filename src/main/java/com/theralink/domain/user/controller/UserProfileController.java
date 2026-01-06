package com.theralink.domain.user.controller;

import com.theralink.common.mapping.ModelMapperUtil;
import com.theralink.web.controller.BaseController;
import com.theralink.domain.user.dto.UserProfileViewModel;
import com.theralink.domain.user.model.UserProfile;
import com.theralink.domain.user.service.IUserProfileService;
import com.theralink.web.viewModel.BillViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userProfile")
public class UserProfileController extends BaseController {

	@Autowired
	private IUserProfileService iUserProfileService;

	@GetMapping(value = "/grid")
	public Page<BillViewModel> pagination(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "date"));
		return ModelMapperUtil.mapPage(iUserProfileService.getAllGrid(pageable), BillViewModel.class);
	}

	@GetMapping(value = "/load/{id}")
	public BillViewModel load(@PathVariable String id) {
		return ModelMapperUtil.map(iUserProfileService.load(id), BillViewModel.class);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public String save(@RequestBody UserProfileViewModel entity) {
		UserProfile map = ModelMapperUtil.map(entity, UserProfile.class);
		return iUserProfileService.save(map);
	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return iUserProfileService.deleteById(id);
	}

}
