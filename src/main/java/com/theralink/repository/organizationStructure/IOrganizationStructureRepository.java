package com.theralink.repository.organizationStructure;

import com.theralink.domain.OrganizationStructure;
import com.theralink.domain.UserOrganizationStructureRole;
import com.theralink.repository.IGenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrganizationStructureRepository extends IGenericRepository<OrganizationStructure, String> {

    @Query(value = "select e from OrganizationStructure e where e.parent.id  = :parentId")
    List<OrganizationStructure> getAll(@Param("parentId") String parentId);

    @Query(value = "select e from OrganizationStructure e where e.parent.id is null ")
    List<OrganizationStructure> rootList();

    @Query("SELECT uosr FROM UserOrganizationStructureRole uosr " +
            "JOIN FETCH uosr.organizationStructure " +
            "WHERE uosr.user.id = :userId")
    List<UserOrganizationStructureRole> findByUserWithOrg(@Param("userId") String userId);


}
