package com.service.customer;

import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.domain.Customer;
import com.repository.customer.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerService extends GenericService<Customer, String> implements ICustomerService {

	@Autowired
	private ICustomerRepository iCustomerRepository;

	@Override
	protected IGenericRepository<Customer, String> getGenericRepo() {
		return iCustomerRepository;
	}

	@Override
	public Customer find(String nationalCode) {
		return iCustomerRepository.find(nationalCode);
	}

	@Override
	public long count() {
		return iCustomerRepository.count();
	}

	@Override
	public Page<Customer> getAllGrid(Pageable pageable, String searchTxt) {
		return iCustomerRepository.getAllGrid(searchTxt, pageable);
	}

	@Transactional
	@Override
	public String save(Customer entity) {
		if (entity.getId() != null) {
			Customer loadedEntity = load(entity.getId());
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
			return super.save(entity);
		}

	}
}
