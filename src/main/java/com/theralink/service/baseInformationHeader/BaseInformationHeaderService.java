package com.theralink.service.baseInformationHeader;

import com.theralink.domain.BaseInformationHeader;
import com.theralink.repository.IGenericRepository;
import com.theralink.repository.baseInformationHeader.IBaseInformationHeaderRepository;
import com.theralink.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BaseInformationHeaderService extends GenericService<BaseInformationHeader, String> implements IBaseInformationHeaderService {

    @Autowired
    private IBaseInformationHeaderRepository iBaseInformationHeaderRepository;

    @Override
    protected IGenericRepository<BaseInformationHeader, String> getGenericRepo() {
        return iBaseInformationHeaderRepository;
    }

    @Override
    public BaseInformationHeader loadByTitle(String title) {
        return iBaseInformationHeaderRepository.loadByTitle(title);
    }

    @Override
    public Page<BaseInformationHeader> getAllGrid(Pageable pageable, String topic) {
        return iBaseInformationHeaderRepository.getAllGrid(pageable, topic);
    }
}
