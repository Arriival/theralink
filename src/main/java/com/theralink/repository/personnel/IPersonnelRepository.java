package com.theralink.repository.personnel;

import com.core.framework.repository.IGenericRepository;
import com.theralink.domain.Personnel;
import com.theralink.domain.PersonnelType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPersonnelRepository extends IGenericRepository<Personnel, String> {
    @Query(value = " from Personnel e where e.personnelType = :type and :clinicId in (select os.id from e.person.organizations os)")
    Page<Personnel> getAllGrid(@Param("type") PersonnelType type, @Param("clinicId") String clinicId, Pageable pageable);

    @Query("select e from Personnel e where e.person.id = :personId")
    Personnel loadByPersonId(@Param("personId") String personId);

	@Query(value = " from Personnel e where e.personnelType = 'CONSULTANT' and :clinicId in (select os.id from e.person.organizations os)")
	List<Personnel> consultantsList(@Param("clinicId") String clinicId);
}
