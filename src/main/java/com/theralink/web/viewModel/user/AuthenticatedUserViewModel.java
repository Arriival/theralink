package com.theralink.web.viewModel.user;

import com.theralink.web.viewModel.user.UserOrgRoleDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthenticatedUserViewModel {
	private String               username;
	private String               firstName;
	private String               lastName;
	private List<UserOrgRoleDto> authorities;
}
