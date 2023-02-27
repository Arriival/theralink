package com.repository.personnel;

import com.core.framework.repository.IGenericRepository;
import com.domain.Personnel;
import com.domain.PersonnelType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPersonnelRepository extends IGenericRepository<Personnel, String> {
    @Query(value = " from Personnel e where e.personnelType = :type")
    Page<Personnel> getAllGrid(@Param("type") PersonnelType type, Pageable pageable);

    @Query("select e from Personnel e where e.person.id = :personId")
    Personnel loadByPersonId(@Param("personId") String personId);

}
