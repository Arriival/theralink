package com.theralink.service.baseInformationHeader;

import com.theralink.domain.BaseInformationHeader;
import com.theralink.service.IGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBaseInformationHeaderService extends IGenericService<BaseInformationHeader, String> {

    public BaseInformationHeader loadByTitle(String title);

    Page<BaseInformationHeader> getAllGrid(Pageable pageable, String topic);
}
