package com.theralink.domain.user.model;

import com.core.framework.domain.BaseEntity;
import com.core.framework.domain.user.Role;
import com.core.framework.domain.user.User;
import com.theralink.domain.clinic.model.Clinic;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "USER_PROFILE")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class UserProfile extends BaseEntity<String> {
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLINIC_ID")
	private Clinic clinic;

	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE")
	private Role role;

	@Column(name = "ACTIVE", nullable = false)
	private Boolean active = true;

	@OneToOne(mappedBy = "userProfile")
	private CounselorProfile counselorProfile;

	@OneToOne(mappedBy = "userProfile")
	private ClientProfile clientProfile;  // مراجع (Client)

	@OneToOne(mappedBy = "userProfile")
	private SecretaryProfile secretaryProfile;  // منشی (Secretary)

}
