package com.theralink.domain.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

	public static UserPrincipal userToPrincipal(User user) {
		UserPrincipal userPrincipal = new UserPrincipal();

		// Force initialize lazy fields before leaving session
		if (user.getPerson() != null) {
			user.getPerson().getOrganizations().size();
		}
		user.getStructureRoleSet().size();

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.addAll(user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList()));

		userPrincipal.setId(user.getId());
		userPrincipal.setUsername(user.getUsername());
		userPrincipal.setPassword(user.getPassword());
		userPrincipal.setAuthorities(authorities);
		userPrincipal.setRoles(user.getRoles());
		userPrincipal.setPerson(user.getPerson());
		userPrincipal.setOrganizations(user.getPerson() != null ? user.getPerson().getOrganizations() : null);
		userPrincipal.setStructureRoles(user.getStructureRoleSet());
		userPrincipal.setUser(user);

		return userPrincipal;
	}
}
