package com.theralink.web.controller;

import com.theralink.common.mapping.ModelMapperUtil;
import com.theralink.domain.user.User;
import com.theralink.service.user.IUserService;
import com.theralink.utils.SecurityUtil;
import com.theralink.web.controller.BaseController;
import com.theralink.web.viewModel.organizationStructure.OrganizationStructureLiteViewModel;
import com.theralink.web.viewModel.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

	@Autowired
	private IUserService iUserService;

	@GetMapping("/test")
	public void testError() {
		throw new RuntimeException("test");
	}

	@GetMapping(value = "/load/{id}")
	public LiteUserViewModel load(@PathVariable String id) {
		return ModelMapperUtil.map(iUserService.load(id), LiteUserViewModel.class);
	}

	@GetMapping(value = "/loadAllInfo/{id}")
	public UserViewModel loadAllInfo(@PathVariable String id) {
		return ModelMapperUtil.map(iUserService.load(id), UserViewModel.class);
	}

	@GetMapping(value = "/list")
	public List<LiteUserViewModel> list() {
		return ModelMapperUtil.mapList(iUserService.getAll(), LiteUserViewModel.class);
	}

	@GetMapping(value = "/orgStruct/list/{userId}")
	public List<OrganizationStructureLiteViewModel> userOrgStructList(@PathVariable String userId) {
		return ModelMapperUtil.mapList(iUserService.load(userId).getPerson().getOrganizations(), OrganizationStructureLiteViewModel.class);
	}

	@GetMapping(value = "/grid")
	public Page<LiteUserViewModel> page(Pageable pageable) {
		return ModelMapperUtil.mapPage(iUserService.getAllGridByClinicFilter(pageable), LiteUserViewModel.class);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public String save(@RequestBody UserViewModel entity) {
		return iUserService.save(ModelMapperUtil.map(entity, User.class));
	}

	@PostMapping(value = "/signUp")
	@ResponseBody
	public String signUp(@RequestBody UserViewModel entity) {
		return iUserService.signUp(entity);
	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return iUserService.deleteById(id);
	}

	@GetMapping(value = "/unLock/{id}")
	public boolean unLock(@PathVariable String id) {
		return iUserService.unLock(id);
	}

	@GetMapping(value = "/authenticated/authorities")
	public List<UserOrgRoleDto> authenticatedUserAuthoritiesList() {
		return iUserService.authenticatedUserAuthoritiesList();
	}

	@GetMapping(value = "/authenticated/roles")
	public List<String> authenticatedUserRoles() {
		return iUserService.authenticatedUserRoles();
	}

	@GetMapping(value = "/authenticated/info")
	public AuthenticatedUserViewModel authenticatedUserDetails() {
		AuthenticatedUserViewModel authenticatedUser = ModelMapperUtil.map(SecurityUtil.getAuthenticatedUser(), AuthenticatedUserViewModel.class);
		authenticatedUser.setAuthorities(iUserService.authenticatedUserAuthoritiesList());
		return authenticatedUser;
	}

	@GetMapping(value = "/checkUserNameExists/{username}")
	public boolean checkUserNameExists(@PathVariable String username) {
		return iUserService.checkUserNameExists(username);
	}

	@PostMapping(value = "/changePassword")
	public boolean changePassword(@RequestBody ChangePasswordDto entity) {
		return iUserService.changePassword(entity);
	}

	@PostMapping(value = "/changePassword/admin")
	public boolean changePasswordByAdmin(@RequestBody ChangePasswordDto entity) {
		return iUserService.changePasswordByAdmin(entity);
	}
}
