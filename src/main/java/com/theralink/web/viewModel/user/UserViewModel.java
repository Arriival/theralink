package com.theralink.web.viewModel.user;

import com.theralink.web.viewModel.BaseEntityViewModel;
import com.theralink.web.viewModel.organizationStructure.OrganizationStructureLiteViewModel;
import com.theralink.web.viewModel.organizationStructure.UserOrganizationStructureRoleViewModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserViewModel extends BaseEntityViewModel<String> {
	private String                                       personId;
	private String                                       personCode;
	private String                                       firstName;
	private String                                       lastName;
	private String                                       fullName;
	private String                                       username;
	private String                                       password;
	private boolean                                      activated;
	private boolean                                      isLock;
	private boolean                                      forceUpdate;
	private String                                       userLockDate;
	private String                                       userCredit;
	private String                                       passwordCredit;
	private List<String>                                 roles;
	private List<OrganizationStructureLiteViewModel>     organizations;
	private List<UserOrganizationStructureRoleViewModel> structureRoleSet;

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
}
