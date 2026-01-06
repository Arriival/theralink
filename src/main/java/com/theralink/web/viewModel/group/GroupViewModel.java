package com.theralink.web.viewModel.group;

import com.theralink.web.viewModel.BaseEntityViewModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupViewModel extends BaseEntityViewModel<String> {
	private String  name;
	private Boolean active;
}
