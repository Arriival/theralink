package com.theralink.repository;

import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericRepository {
	//public abstract class GenericRepository<T extends BaseEntity, PK extends Serializable> implements IGenericRepository<T, PK> {
/*	protected Class<T> domainClass = getDomainClass();

	@PersistenceContext
	protected EntityManager em;

	protected abstract Class<T> getDomainClass();

	protected Session getSession() {
		return em.unwrap(Session.class);
	}*/

}
