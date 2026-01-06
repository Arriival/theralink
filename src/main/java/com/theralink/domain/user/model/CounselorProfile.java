package com.theralink.domain.user.model;

import com.theralink.domain.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "COUNSELOR_PROFILE")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class CounselorProfile extends BaseEntity<String> {

	@OneToOne
	@JoinColumn(name = "user_profile_id")
	private UserProfile userProfile;

	@Column(name = "EXPERTISE_AREA")
	private String expertiseArea;  // تخصص مشاور

	@Column(name = "BIO")
	private String bio;  // بیوگرافی

}
