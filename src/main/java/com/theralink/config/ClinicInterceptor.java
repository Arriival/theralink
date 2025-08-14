package com.theralink.config;

import com.theralink.utils.UserContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ClinicInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String clinic = request.getHeader("clinic");
		if (clinic != null) {
			UserContextHolder.setClinic(clinic);
		}
		return true;
	}
}
