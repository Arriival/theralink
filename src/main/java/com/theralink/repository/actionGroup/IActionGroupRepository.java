package com.theralink.repository.actionGroup;

import com.theralink.domain.Action;
import com.theralink.domain.ActionGroup;
import com.theralink.repository.IGenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IActionGroupRepository extends IGenericRepository<ActionGroup, String> {

    @Query(value = "select e.action from ActionGroup e  where e.group.id = :groupId")
    List<Action> loadActionsByGroup(@Param("groupId") String groupId);

}
