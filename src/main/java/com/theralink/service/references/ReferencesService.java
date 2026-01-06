package com.theralink.service.references;

import com.theralink.repository.IGenericRepository;
import com.theralink.service.GenericService;

import com.theralink.utils.SecurityUtil;
import com.theralink.domain.References;
import com.theralink.repository.references.IReferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ReferencesService extends GenericService<References, String> implements IReferencesService {

	@Autowired
	private IReferencesRepository iReferencesRepository;

	@Override
	protected IGenericRepository<References, String> getGenericRepo() {
		return iReferencesRepository;
	}

	@Override
	public Page<References> referredToGrid(Pageable pageable) {
		String referredToPersonId = SecurityUtil.getAuthenticatedUser().getPerson().getId();
		return iReferencesRepository.referredToGrid(referredToPersonId, pageable);
	}

	@Override
	public Page<References> referredFromGrid(String customerId, Pageable pageable) {
		String referredFromPersonId = SecurityUtil.getAuthenticatedUser().getPerson().getId();
		return iReferencesRepository.referredFromGrid(referredFromPersonId, customerId, pageable);
	}

	@Override
	@Transactional
	public String visit(String id) {
		References load = load(id);
		load.setIsVisited(true);
		return super.save(load);
	}
}
