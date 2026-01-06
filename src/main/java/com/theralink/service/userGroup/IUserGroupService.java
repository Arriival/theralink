package com.theralink.service.userGroup;

import com.theralink.domain.Group;
import com.theralink.domain.UserGroup;
import com.theralink.domain.user.User;
import com.theralink.service.IGenericService;

import java.util.List;

public interface IUserGroupService extends IGenericService<UserGroup, String> {

    List<Group> loadByUser(String userId);

    List<UserGroup> getAllByGroupId(String groupId);

    List<User> getAllOutsideUsers(String groupId);
}
