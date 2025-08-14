package com.theralink.service.customer;

import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.core.framework.service.person.IPersonService;
import com.theralink.domain.Client;
import com.theralink.repository.customer.ICustomerRepository;
import com.theralink.service.personnel.IPersonnelService;
import com.theralink.service.personnel.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CustomerService extends GenericService<Client, String> implements ICustomerService {

	@Autowired
	private ICustomerRepository iCustomerRepository;

	@Autowired
	private IPersonService iPersonService;


	@Override
	protected IGenericRepository<Client, String> getGenericRepo() {
		return iCustomerRepository;
	}

	@Override
	public Client find(String nationalCode) {
		return iCustomerRepository.find(nationalCode);
	}

	@Override
	public long count() {
		return iCustomerRepository.count();
	}

	@Override
	public Page<Client> getAllGrid(Pageable pageable, String searchTxt) {
		return iCustomerRepository.getAllGrid(searchTxt, pageable);
	}

	@Transactional
	@Override
	public String save(Client entity) {
		if (entity.getId() != null) {
			Client loadedEntity = load(entity.getId());
			loadedEntity = entity;
			loadedEntity.setGenoGram(entity.getGenoGram());
			loadedEntity.setPsychologistVisitHistory(entity.getPsychologistVisitHistory());
			loadedEntity.setDrugUseHistory(entity.getDrugUseHistory());
			loadedEntity.setSicknessHistory(entity.getSicknessHistory());
			loadedEntity.setSleepingStatus(entity.getSleepingStatus());
			loadedEntity.setLifeStyle(entity.getLifeStyle());
			loadedEntity.setProblems(entity.getProblems());
			loadedEntity.setProblemBeginning(entity.getProblemBeginning());
			loadedEntity.setProblemCause(entity.getProblemCause());
			loadedEntity.setTestsDone(entity.getTestsDone());
			loadedEntity.setFirstRecognition(entity.getFirstRecognition());
			return super.save(loadedEntity);
		}
		else {
			entity.setPersonCode(iPersonService.generateNewPersonCode());
			return super.save(entity);
		}

	}
}
