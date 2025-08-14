package com.theralink.domain.user.model;

import com.core.framework.domain.BaseEntity;
import com.theralink.domain.Client;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "CLIENT_PROFILE")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class ClientProfile extends BaseEntity<String> {

	@OneToOne
	@JoinColumn(name = "user_profile_id")
	private UserProfile userProfile;

	@OneToOne
	@JoinColumn(name = "client_id")
	private Client client;

}
