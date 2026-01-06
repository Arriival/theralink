package com.theralink.web.viewModel.file;

import com.theralink.web.viewModel.BaseEntityViewModel;
import lombok.Data;

@Data
public class FileViewModel extends BaseEntityViewModel<String> {
	private String className;
	private String objectId;
	private String name;
	private String type;
}
