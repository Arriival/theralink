package com.theralink.domain.clinic.dto;

import com.theralink.web.viewModel.BaseEntityViewModel;
import lombok.Data;

@Data
public class ClinicViewModel extends BaseEntityViewModel<String> {
	private String name;
	private String code;
	private String manager;
	private String provinceId;
	private String provinceTitle;
	private String cityId;
	private String cityTitle;
	private String address;
	private String location;
	private String phone1;
	private String phone2;
	private String documentId;

}
