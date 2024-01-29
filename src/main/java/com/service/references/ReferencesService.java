package com.service.references;

import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.core.framework.utils.SecurityUtil;
import com.domain.References;
import com.repository.references.IReferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
