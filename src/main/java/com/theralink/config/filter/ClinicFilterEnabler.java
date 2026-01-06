package com.theralink.config.filter;

import com.theralink.utils.UserContextHolder;
import jakarta.persistence.EntityManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Slf4j
//@Component
//@RequiredArgsConstructor
public class ClinicFilterEnabler  {

	/*private final EntityManager entityManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		String clinicId = UserContextHolder.getClinic();
		if (clinicId != null) {
			Session session = entityManager.unwrap(Session.class);
			session.enableFilter("clinicFilter").setParameter("clinicId", clinicId);
			log.info("Filter clinicFilter enabled");
		}else {
			log.info("Filter clinicFilter disabled");
		}
		filterChain.doFilter(request, response);
	}*/
}
