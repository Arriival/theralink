package com.theralink.interceptor;

import com.theralink.utils.UserContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class OrganizationInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String org = request.getHeader("X-Organization-ID");
		if (org != null) {
			UserContextHolder.setClinic(org);
		}
		return true;
	}
}
