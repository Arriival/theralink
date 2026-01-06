package com.theralink.web.viewModel.organizationStructure;

import com.theralink.web.viewModel.BaseEntityViewModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationStructureViewModel extends BaseEntityViewModel<String> {
	private String  name;
	private String  code;
	private Boolean active;
	private String  hierarchyCode;
	private String  description;
}
