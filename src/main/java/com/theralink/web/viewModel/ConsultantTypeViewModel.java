package com.theralink.web.viewModel;

import com.core.framework.web.viewModel.BaseEntityViewModel;
import lombok.Data;

@Data
public class ConsultantTypeViewModel extends BaseEntityViewModel<String> {
    private String educationLevelId;
    private String educationLevelTitle;
    private Float amount;
}
