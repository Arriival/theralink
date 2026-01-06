package com.theralink.aop;

import com.theralink.utils.UserContextHolder;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class ClinicFilterAspect {

	private final EntityManager entityManager;

	@Before("@annotation(com.theralink.common.annotations.ClinicFilter)")
	public void applyClinicFilter() {
		String clinicId = UserContextHolder.getClinic();
		if (clinicId != null) {
			Session session = entityManager.unwrap(Session.class);
			session.enableFilter("clinicFilter").setParameter("clinicId", clinicId);
			log.debug("clinicFilter enabled for clinicId={}", clinicId);
		} else {
			log.debug("clinicFilter NOT enabled (clinicId is null)");
		}
	}
}
