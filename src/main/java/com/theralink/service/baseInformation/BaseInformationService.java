package com.theralink.service.baseInformation;

import com.theralink.domain.BaseInformation;
import com.theralink.repository.IGenericRepository;
import com.theralink.repository.baseInformation.IBaseInformationRepository;
import com.theralink.service.GenericService;
import com.theralink.utils.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BaseInformationService extends GenericService<BaseInformation, String> implements IBaseInformationService {

	@Autowired
	private IBaseInformationRepository iBaseInformationRepository;

	@Override
	protected IGenericRepository<BaseInformation, String> getGenericRepo() {
		return iBaseInformationRepository;
	}

	@Override
	public List<BaseInformation> getAll(String headerId) {
		enableClinicFilter();
		return iBaseInformationRepository.getAll(headerId);
	}

	@Override
	public List<BaseInformation> rootListByHeaderId(String headerId) {
		enableClinicFilter();
		return iBaseInformationRepository.rootList(headerId);
	}

	@Override
	public List<BaseInformation> listByMasterId(String id) {
		enableClinicFilter();
		return iBaseInformationRepository.listByMasterId(id);
	}
}
