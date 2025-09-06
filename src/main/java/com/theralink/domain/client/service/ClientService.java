package com.theralink.domain.client.service;

import com.core.framework.domain.Person;
import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.core.framework.service.person.IPersonService;
import com.theralink.domain.client.model.Client;
import com.theralink.domain.client.repo.IClientRepository;
import com.theralink.domain.clinic.model.Clinic;
import com.theralink.utils.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ClientService extends GenericService<Client, String> implements IClientService {

	@Autowired
	private IClientRepository iCustomerRepository;

	@Autowired
	private IPersonService iPersonService;

	@Override
	protected IGenericRepository<Client, String> getGenericRepo() {
		return iCustomerRepository;
	}

	@Override
	public Client find(String nationalCode) {
		return iCustomerRepository.find(nationalCode, UserContextHolder.getClinic());
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
			Person person = iPersonService.loadByNationalCode(entity.getNationalCode());
			if (person != null) {
				entity.setId(person.getId());

			}
			entity.setClinic(new Clinic(UserContextHolder.getClinic()));
			return super.save(entity);
		}

	}
}
