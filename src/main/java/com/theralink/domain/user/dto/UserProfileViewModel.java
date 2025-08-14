package com.theralink.domain.user.dto;

import com.core.framework.web.viewModel.BaseEntityViewModel;
import com.core.framework.web.viewModel.user.UserViewModel;
import com.theralink.domain.user.model.UserRole;
import lombok.Data;

import java.util.List;

@Data
public class UserProfileViewModel extends BaseEntityViewModel<String> {
	private UserViewModel user;
	private String        clinicId;
	private UserRole      role;
	private Boolean       active;

}
