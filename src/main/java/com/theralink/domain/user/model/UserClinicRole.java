package com.theralink.domain.user.model;

import com.core.framework.domain.BaseEntity;
import com.theralink.domain.clinic.model.Clinic;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "USER_CLINIC_ROLE")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class UserClinicRole extends BaseEntity<String> {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERPROFILE_ID")
	private UserProfile userProfile;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLINIC_ID")
	private Clinic clinic;

	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE")
	private UserRole role;

	@Column(name = "ACTIVE",nullable = false)
	private Boolean active = true;
}
