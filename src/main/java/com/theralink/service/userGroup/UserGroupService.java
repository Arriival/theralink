package com.theralink.service.userGroup;

import com.theralink.domain.Group;
import com.theralink.domain.UserGroup;
import com.theralink.domain.user.User;
import com.theralink.repository.IGenericRepository;
import com.theralink.repository.userGroup.IUserGroupRepository;
import com.theralink.service.GenericService;

import com.theralink.service.userGroup.IUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupService extends GenericService<UserGroup, String> implements IUserGroupService {

    @Autowired
    private IUserGroupRepository iUserGroupRepository;


    @Override
    protected IGenericRepository<UserGroup, String> getGenericRepo() {
        return iUserGroupRepository;
    }

    @Override
    public List<Group> loadByUser(String userId) {
        return iUserGroupRepository.loadByUser(userId);
    }

    @Override
    public List<UserGroup> getAllByGroupId(String groupId) {
        return iUserGroupRepository.getAllByGroupId(groupId);
    }

    @Override
    public List<User> getAllOutsideUsers(String groupId) {
        return iUserGroupRepository.getAllOutsideUsers(groupId);
    }
}
