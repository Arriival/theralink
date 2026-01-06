package com.theralink.service.actionGroup;

import com.theralink.domain.Action;
import com.theralink.domain.ActionGroup;
import com.theralink.repository.IGenericRepository;
import com.theralink.repository.actionGroup.IActionGroupRepository;
import com.theralink.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionGroupService extends GenericService<ActionGroup, String> implements IActionGroupService {

	@Autowired
	private IActionGroupRepository iActionGroupRepository;

	@Override
	protected IGenericRepository<ActionGroup, String> getGenericRepo() {
		return iActionGroupRepository;
	}

	@Override
	public List<Action> loadActionsByGroup(String groupId) {
		return iActionGroupRepository.loadActionsByGroup(groupId);
	}
}
