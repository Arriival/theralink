package com.theralink.domain.user;


import com.theralink.domain.Action;
import com.theralink.domain.BaseEntity;
import com.theralink.domain.Person;
import com.theralink.domain.UserOrganizationStructureRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "APP_USER")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
@Filter(name = "clinicFilter", condition = "(exists(select * from APP_USER_ORG_ROLE ur where ur.user_id = id and ur.org_id = :clinicId ))")
public class User extends BaseEntity<String> {

	@Size(max = 50)
	@Column(name = "USER_NAME", unique = true, nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Person_ID", unique = true, updatable = false)
	private Person person;

	@Column(name = "ACTIVATED")
	private boolean activated = false;

	@Column(name = "IS_LOCK")
	private boolean isLock = false;

	@Column(name = "FORCE_UPDATE")
	private boolean forceUpdate = false;

	@Column(name = "USER_LOCK_DATE")
	private String userLockDate;

	@Column(name = "USER_CREDIT")
	private String userCredit;

	@Column(name = "PASSWORD_CREDIT")
	private String passwordCredit;

	//    @Formula("SELECT A.* FROM APP_ACTION A INNER JOIN APP_ACTION_GROUP AG ON AG.ACTION_ID = A.ID INNER JOIN APP_USER_GROUP UG ON UG.GROUP_ID = AG.GROUP_ID WHERE UG.USER_ID = ID")
	@Transient
	private List<Action> actions;

	@ElementCollection(targetClass = Role.class)
	@CollectionTable(name = "APP_USER_ROLE", joinColumns = @JoinColumn(name = "ID"))
	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE")
	private List<Role> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserOrganizationStructureRole> structureRoleSet;

	public User(String id) {
		super.setId(id);
	}

	public User() {

	}
}
