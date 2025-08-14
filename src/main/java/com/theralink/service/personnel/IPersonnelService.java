package com.theralink.service.personnel;

import com.core.framework.service.IGenericService;
import com.theralink.domain.Personnel;
import com.theralink.domain.PersonnelType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPersonnelService extends IGenericService<Personnel, String> {
    Page<Personnel> getAllGrid(PersonnelType type, Pageable pageable);

    Personnel loadByPersonId(String personId);

	List<Personnel> consultantsList();
}
