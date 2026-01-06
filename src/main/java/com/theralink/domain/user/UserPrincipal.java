package com.theralink.domain.user;

import com.theralink.domain.OrganizationStructure;
import com.theralink.domain.Person;
import com.theralink.domain.UserOrganizationStructureRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserPrincipal implements UserDetails {
	private String                                 id;
	private String                                 username;
	private String                                 password;
	private Person                                 person;
	private User                                   user;
	private List<Role>                             roles;
	private List<? extends OrganizationStructure>  organizations;
	private List<UserOrganizationStructureRole>    structureRoles;
	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
