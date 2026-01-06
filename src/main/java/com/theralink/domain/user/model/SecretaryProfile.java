package com.theralink.domain.user.model;

import com.theralink.domain.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "SECRETARY_PROFILE")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class SecretaryProfile extends BaseEntity<String> {

	@OneToOne
	@JoinColumn(name = "user_profile_id")
	private UserProfile userProfile;

	@Column(name = "hourly_Wage")
	private Float hourlyWage;

}
