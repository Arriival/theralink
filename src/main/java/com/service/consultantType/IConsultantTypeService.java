package com.service.consultantType;

import com.core.framework.service.IGenericService;
import com.domain.ConsultantType;

import java.util.List;

public interface IConsultantTypeService extends IGenericService<ConsultantType, String> {
    List<ConsultantType> getAllList();
}
