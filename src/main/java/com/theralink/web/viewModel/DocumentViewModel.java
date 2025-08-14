package com.theralink.web.viewModel;

import com.core.framework.web.viewModel.BaseEntityViewModel;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

@Data
public class DocumentViewModel extends BaseEntityViewModel<String> {
	private String className;
	private String objectId;
	private String name;
	private String type;
}
