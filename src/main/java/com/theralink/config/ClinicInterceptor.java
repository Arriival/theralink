package com.theralink.config;

import com.theralink.utils.UserContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
@Component
@Order(2)
public class ClinicInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String clinic = request.getHeader("clinic");
		log.info("clinic : {}", clinic);
		if (clinic != null) {
			UserContextHolder.setClinic(clinic);
		}
		return true;
	}
}
