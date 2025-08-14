package com.theralink.domain.user.dto;

import com.core.framework.web.viewModel.BaseEntityViewModel;
import com.theralink.domain.user.model.UserRole;
import lombok.Data;

@Data
public class UserClinicRoleViewModel extends BaseEntityViewModel<String> {
	private String  userProfileId;
	private String   clinicId;
	private UserRole role;
	private Boolean  active;

}
