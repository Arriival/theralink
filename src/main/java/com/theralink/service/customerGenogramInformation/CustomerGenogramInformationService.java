package com.theralink.service.customerGenogramInformation;

import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.theralink.domain.CustomerGenogramInformation;
import com.theralink.repository.customerGenogramInformation.ICustomerGenogramInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class CustomerGenogramInformationService extends GenericService<CustomerGenogramInformation, String> implements ICustomerGenogramInformationService {

	@Autowired
	private ICustomerGenogramInformationRepository iCustomerGenogramInformationRepository;

	@Override
	protected IGenericRepository<CustomerGenogramInformation, String> getGenericRepo() {
		return iCustomerGenogramInformationRepository;
	}

	@Override
	public List<CustomerGenogramInformation> list(String customerId) {
		return iCustomerGenogramInformationRepository.getAllByClientId(customerId);
	}

	@Override
	@Transactional
	public Integer saveList(List<CustomerGenogramInformation> entities) {
		entities.forEach(e -> {
			super.save(e);
		});
		return entities.size();
	}
}
