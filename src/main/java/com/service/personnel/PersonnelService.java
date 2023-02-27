package com.service.personnel;

import com.core.framework.domain.person.Person;
import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.core.framework.service.person.IPersonService;
import com.domain.Personnel;
import com.domain.PersonnelType;
import com.repository.personnel.IPersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PersonnelService extends GenericService<Personnel, String> implements IPersonnelService {

    @Autowired
    private IPersonnelRepository iPersonnelRepository;

    @Autowired
    private IPersonService iPersonService;

    @Override
    protected IGenericRepository<Personnel, String> getGenericRepo() {
        return iPersonnelRepository;
    }

    @Override
    public Page<Personnel> getAllGrid(PersonnelType type, Pageable pageable) {
        return iPersonnelRepository.getAllGrid(type, pageable);
    }

    @Override
    public Personnel loadByPersonId(String personId){
        return iPersonnelRepository.loadByPersonId(personId);
    }

    @Override
    @Transactional
    public String save(Personnel entity) {
        Person person = entity.getPerson();
        String save = iPersonService.save(person);
        person.setId(save);
        entity.setPerson(person);
        return super.save(entity);
    }
}
