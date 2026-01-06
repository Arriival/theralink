package com.theralink.web.viewModel;

import lombok.Data;

@Data
public class PersonnelViewModel extends BaseEntityViewModel<String> {
    private String personId;
    private String firstName;
    private String lastName;
    private String cellPhone;
    private String nationalCode;
    private String educationLevelId;
    private String educationLevelTitle;
    private String personnelType;
    private Float secretaryHourlyWage;
//    private String personnelTypeTitle;
//    private String personnelTypePersianTitle;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
