package com.theralink.service.person;

import com.theralink.domain.OrganizationStructure;
import com.theralink.domain.Person;
import com.theralink.repository.IGenericRepository;
import com.theralink.repository.person.IPersonRepository;
import com.theralink.repository.person.PersonSpecification;
import com.theralink.service.GenericService;

import com.theralink.service.person.IPersonService;
import com.theralink.utils.SecurityUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService extends GenericService<Person, String> implements IPersonService {

	@Autowired
	private IPersonRepository iPersonRepository;

	@Autowired
	private EntityManager entityManager;

	protected IGenericRepository<Person, String> getGenericRepo() {
		return iPersonRepository;
	}

	@Override
	public Page<Person> getAllGrid(Pageable pageable) {
		//		return super.getAllGrid(pageable);
		return super.getAllGrid(PersonSpecification.hasAccessToPersonOrganizations(), pageable);
	}

	@Override
	public Person loadByNationalCode(String NationalCode) {
		return iPersonRepository.loadByNationalCode(NationalCode);
	}

	@Override
	@Transactional
	public String save(Person entity) {
		if (entity.getId() == null) {
			entity.setPersonCode(generateNewPersonCode());
			return super.save(entity);
		}
		else {
			return updatePerson(entity);
		}

	}

	@Transactional
	public String updatePerson(Person person) {
		if (person.getId() == null) {
			throw new IllegalArgumentException("ID is required to update a person");
		}
		Person managed = entityManager.find(Person.class, person.getId());

		if (managed == null) {
			throw new EntityNotFoundException("No person found with id " + person.getId());
		}
		Set<String> personOrgIds = person.getOrganizations().stream().map(OrganizationStructure::getId).collect(Collectors.toSet());
		List<OrganizationStructure> removedOrg = managed.getOrganizations().stream().filter(item -> !personOrgIds.contains(item.getId())).collect(Collectors.toList());
		// ToDo : bayad removedOrgs az UserOrganizationStructureRole hazf beshe

		managed.setOrganizations(person.getOrganizations());
		managed.setFirstName(person.getFirstName());
		managed.setLastName(person.getLastName());
		managed.setNationalCode(person.getNationalCode());
		managed.setEmail(person.getEmail());
		managed.setCellPhone(person.getCellPhone());
		managed.setBirthDate(person.getBirthDate());
		managed.setGender(person.getGender());
		managed.setMarriageStatus(person.getMarriageStatus());
		return managed.getId();
	}

	@Override
	public String generateNewPersonCode() {
		String latestPersonCode = iPersonRepository.getLatestPersonCode();
		if (latestPersonCode == null) {
			return "10000";
		}
		else {
			return String.valueOf(Integer.valueOf(latestPersonCode) + 1);
		}
	}

	@Override
	public List<Person> unRegisteredPersons() {
		return iPersonRepository.unRegisteredPersons(SecurityUtil.getAuthenticatedUserOrganizations().stream().map(OrganizationStructure::getId).toList(), SecurityUtil.getAuthenticatedUserRoles().stream().map(Enum::toString).collect(Collectors.toList()));
	}
}
