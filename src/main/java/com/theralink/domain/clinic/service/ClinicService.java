package com.theralink.domain.clinic.service;

import com.theralink.domain.clinic.model.Clinic;
import com.theralink.domain.clinic.repo.IClinicRepository;
import com.theralink.domain.user.Role;
import com.theralink.repository.IGenericRepository;
import com.theralink.service.GenericService;
import com.theralink.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClinicService extends GenericService<Clinic, String> implements IClinicService {

	@Autowired
	private IClinicRepository iClinicRepository;

	@Override
	protected IGenericRepository<Clinic, String> getGenericRepo() {
		return iClinicRepository;
	}

	@Override
	public Page<Clinic> getAllGrid(Pageable pageable, String searchTxt) {
		return iClinicRepository.getAllGrid(searchTxt, pageable);
	}

	@Override
	public String generateNewPersonCode() {
		String latestCode = iClinicRepository.getLastCode();
		if (latestCode == null) {
			return "10000";
		}
		else {
			return String.valueOf(Integer.valueOf(latestCode) + 1);
		}
	}

	@Override
	public List<Clinic> findAllByName(String name) {
		return iClinicRepository.findAllByNameContains(name);
	}

	@Override
	public List<Clinic> findAllByAuthorize() {
		return iClinicRepository.findAllByAuthorize(SecurityUtil.getAuthenticatedUser().getPerson().getId(), SecurityUtil.getAuthenticatedUserRoles().stream().anyMatch(r->r.equals(Role.ADMIN)));
	}

	@Transactional
	@Override
	public String save(Clinic entity) {
		if (entity.getId() == null) {
			entity.setCode(generateNewPersonCode());
		}
		return super.save(entity);
	}
}
