package com.theralink.web.viewModel.organizationStructure;

import com.theralink.web.viewModel.BaseEntityViewModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOrganizationStructureRoleViewModel extends BaseEntityViewModel<String> {
	private String  userId;
	private String  organizationStructureId;
	private String  role;
}
