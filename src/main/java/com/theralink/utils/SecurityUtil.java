package com.theralink.utils;

import com.theralink.domain.OrganizationStructure;
import com.theralink.domain.user.Role;
import com.theralink.domain.user.User;
import com.theralink.domain.user.UserPrincipal;
import com.theralink.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class SecurityUtil {

	private static IUserService userService;

	@Autowired
	public SecurityUtil(IUserService userService) {
		SecurityUtil.userService = userService;
	}

	// ------------------- UserPrincipal -------------------

	public static UserPrincipal getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (UserPrincipal) authentication.getPrincipal();
	}

	public static String getAuthenticatedUserId() {
		return getAuthenticatedUser().getId();
	}

	public static List<? extends OrganizationStructure> getAuthenticatedUserOrganizations() {
		return getAuthenticatedUser().getOrganizations();
	}

	public static Collection<? extends GrantedAuthority> getAuthenticatedUserAuthorities() {
		return getAuthenticatedUser().getAuthorities();
	}

	public static boolean hasCurrentUserThisAuthority(String authority) {
		authority = "ROLE_" + authority;
		return getAuthenticatedUserAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(authority::equals);
	}

	public static List<Role> getAuthenticatedUserRoles() {
		return getAuthenticatedUser().getRoles();
	}

	// ------------------- User Entity -------------------

	public static User getAuthenticatedUserEntity() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !(authentication.getPrincipal() instanceof UserPrincipal)) {
			return null;
		}
		return ((UserPrincipal) authentication.getPrincipal()).getUser();
	}
}
