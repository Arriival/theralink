package com.theralink.domain;

import com.theralink.domain.user.Role;
import com.theralink.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "APP_USER_ORG_ROLE")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class UserOrganizationStructureRole extends BaseEntity<String> {

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "org_id")
	private OrganizationStructure organizationStructure;

	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE")
	private Role role;

}
