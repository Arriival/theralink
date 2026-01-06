package com.theralink.domain.user.service;

import com.theralink.repository.IGenericRepository;
import com.theralink.service.GenericService;

import com.theralink.domain.user.repo.IUserProfileRepository;
import com.theralink.domain.user.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserProfileService extends GenericService<UserProfile, String> implements IUserProfileService {

	@Autowired
	private IUserProfileRepository iUserProfileRepository;

	@Override
	protected IGenericRepository<UserProfile, String> getGenericRepo() {
		return iUserProfileRepository;
	}

	@Transactional
	@Override
	public String save(UserProfile entity) {
		if (entity.getId() != null) {
			UserProfile load = load(entity.getId());
			entity.getUser().setId(load.getUser().getId());
		}
		return super.save(entity);
	}
}
