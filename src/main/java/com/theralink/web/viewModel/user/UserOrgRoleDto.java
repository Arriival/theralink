package com.theralink.web.viewModel.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserOrgRoleDto {
	private String       organizationId;
	private String       organizationName;
	private List<String> authorities;
}
