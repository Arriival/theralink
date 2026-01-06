package com.theralink.service;

import com.theralink.common.exception.ApplicationException;
import com.theralink.domain.BaseEntity;
import com.theralink.domain.clinic.model.Clinic;
import com.theralink.domain.user.User;
import com.theralink.repository.IGenericRepository;
import com.theralink.utils.DateUtility;
import com.theralink.utils.SecurityUtil;
import com.theralink.utils.UserContextHolder;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Slf4j
@Service
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public abstract class GenericService<T extends BaseEntity<PK>, PK extends Serializable> implements IGenericService<T, PK> {

	@Autowired
	private EntityManager entityManager;

	public GenericService() {

	}

	protected void enableClinicFilter() {
		String clinicId = UserContextHolder.getClinic();
		if (clinicId != null) {
			Session session = entityManager.unwrap(Session.class);
			session.enableFilter("clinicFilter").setParameter("clinicId", clinicId);
			log.debug("clinicFilter enabled for clinicId={}", clinicId);
		} else {
			log.debug("clinicFilter not enabled (clinicId is null)");
		}
	}

	protected void enableFilter(String filterName, Map<String, Object> params) {
		Session session = entityManager.unwrap(Session.class);
		var filter = session.enableFilter(filterName);
		params.forEach((k, v) -> {
			if (v != null)
				filter.setParameter(k, v);
		});
		log.debug("{} enabled with params={}", filterName, params);
	}

	protected abstract IGenericRepository<T, PK> getGenericRepo();

	@Override
	public T load(PK entityId) {
		return (T) this.getGenericRepo().findById(entityId).get();
	}

	@Override
	public T authorizedLoad(PK entityId) {
		boolean isAuthorize = SecurityUtil.getAuthenticatedUserOrganizations().stream().anyMatch(organization -> organization.getId().equals(UserContextHolder.getClinic()));
		if (isAuthorize) {
			return (T) this.getGenericRepo().findById(entityId).get();
		}
		else {
			throw new ApplicationException("You are not authorized to perform this operation");
		}
	}

	@Override
	public List<T> getAllByFilter(String filterName, Map<String, Object> params) {
		enableFilter(filterName, params);
		return toList(this.getGenericRepo().findAll());
	}

	@Override
	public List<T> getAllByClinicFilter() {
		enableClinicFilter();
		return toList(this.getGenericRepo().findAll());
	}

	@Override
	public List<T> getAll() {
		return toList(this.getGenericRepo().findAll());
	}

	@Override
	public Page<T> getAllGrid(Pageable pageable) {
		return this.getGenericRepo().findAll(pageable);
	}

	@Override
	public Page<T> getAllGridByClinicFilter(Pageable pageable) {
		enableClinicFilter();
		return this.getGenericRepo().findAll(pageable);
	}

	@Override
	public Page<T> getAllGrid(Specification<T> specification, Pageable pageable) {
		return this.getGenericRepo().findAll(specification, pageable);
	}

	@Transactional
	@Override
	public PK save(T entity) {
		User authenticatedUser = SecurityUtil.getAuthenticatedUserEntity();
		if (entity.getId() == null) {
			entity.setCreatedBy(authenticatedUser);
			entity.setCreatedDate(DateUtility.todayJalaliDateAndTime());
			entity.setClinic(new Clinic(UserContextHolder.getClinic()));
		}
		entity.setUpdatedBy(authenticatedUser);
		entity.setUpdatedDate(DateUtility.todayJalaliDateAndTime());
		return (PK) this.getGenericRepo().save(entity).getId();
	}

	@Transactional
	@Override
	public boolean deleteById(PK id) {
		this.getGenericRepo().deleteById(id);
		return true;
	}

	@Transactional
	@Override
	public boolean deleteByAuthorizedId(PK id) {
		boolean isAuthorize = SecurityUtil.getAuthenticatedUserOrganizations().stream().anyMatch(organization -> organization.getId().equals(UserContextHolder.getClinic()));
		if (isAuthorize) {
			this.getGenericRepo().deleteById(id);
			return true;
		}
		else {
			throw new ApplicationException("You are not authorized to delete this entity");
		}
	}

	public static <T> List<T> toList(final Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
	}
}
