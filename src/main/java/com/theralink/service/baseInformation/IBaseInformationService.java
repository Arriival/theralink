package com.theralink.service.baseInformation;

import com.theralink.domain.BaseInformation;
import com.theralink.service.IGenericService;

import java.util.List;

public interface IBaseInformationService extends IGenericService<BaseInformation, String> {


    List<BaseInformation> getAll(String headerId);

    List<BaseInformation> rootListByHeaderId(String headerId);

    List<BaseInformation> listByMasterId(String id);
}
