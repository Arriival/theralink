package com.theralink.service.consultantType;

import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.theralink.domain.ConsultantType;
import com.theralink.repository.consultantType.IConsultantTypeRepository;
import org.dozer.util.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultantTypeService extends GenericService<ConsultantType, String> implements IConsultantTypeService {

    @Autowired
    private IConsultantTypeRepository iConsultantTypeRepository;

    @Override
    protected IGenericRepository<ConsultantType, String> getGenericRepo() {
        return iConsultantTypeRepository;
    }

    @Override
    public List<ConsultantType> getAllList() {
        return IteratorUtils.toList(iConsultantTypeRepository.findAll().iterator());
    }
}
