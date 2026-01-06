package com.theralink.service.group;

import com.theralink.domain.Group;
import com.theralink.repository.IGenericRepository;
import com.theralink.repository.group.IGroupRepository;
import com.theralink.service.GenericService;

import com.theralink.service.group.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService extends GenericService<Group, String> implements IGroupService {

	@Autowired
	private IGroupRepository iGroupRepository;

	@Override
	protected IGenericRepository<Group, String> getGenericRepo() {
		return iGroupRepository;
	}
}
