package com.web.viewModel;

import com.core.framework.web.viewModel.BaseEntityViewModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;

@Data
public class DocumentViewModel extends BaseEntityViewModel<String> {
	private String className;
	private String objectId;
	private String name;
	private String type;
}
