package com.theralink.service.action;

import com.theralink.domain.Action;
import com.theralink.repository.IGenericRepository;
import com.theralink.repository.action.IActionRepository;
import com.theralink.service.GenericService;

import com.theralink.service.action.IActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionService extends GenericService<Action, String> implements IActionService {

    @Autowired
    private IActionRepository iActionRepository;

    @Override
    protected IGenericRepository<Action, String> getGenericRepo() {
        return iActionRepository;
    }
}
