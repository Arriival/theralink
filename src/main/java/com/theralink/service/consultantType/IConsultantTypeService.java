package com.theralink.service.consultantType;

import com.theralink.service.IGenericService;
import com.theralink.domain.ConsultantType;

import java.util.List;

public interface IConsultantTypeService extends IGenericService<ConsultantType, String> {
    List<ConsultantType> getAllList();
}
