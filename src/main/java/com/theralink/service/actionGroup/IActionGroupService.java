package com.theralink.service.actionGroup;


import com.theralink.domain.Action;
import com.theralink.domain.ActionGroup;
import com.theralink.service.IGenericService;

import java.util.List;

public interface IActionGroupService extends IGenericService<ActionGroup, String> {

	List<Action> loadActionsByGroup(String groupId);

}
