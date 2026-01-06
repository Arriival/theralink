package com.theralink.web.viewModel.organizationStructure;

import com.theralink.web.viewModel.BaseEntityViewModel;
import com.theralink.web.viewModel.organizationStructure.OrganizationStructureViewModel;
import com.theralink.web.viewModel.person.PersonViewModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonOrganizationStructureViewModel extends BaseEntityViewModel<String> {
	private OrganizationStructureViewModel organizationStructureViewModel;
	private PersonViewModel                personViewModel;
}
