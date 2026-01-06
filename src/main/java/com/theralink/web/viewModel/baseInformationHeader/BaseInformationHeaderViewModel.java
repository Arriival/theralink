package com.theralink.web.viewModel.baseInformationHeader;

import com.theralink.web.viewModel.BaseEntityViewModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseInformationHeaderViewModel extends BaseEntityViewModel<String> {
    private String topic;
    private String code;
    private boolean active;
}
