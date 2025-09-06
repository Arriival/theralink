package com.theralink.domain;

import com.core.framework.domain.BaseEntity;
import com.theralink.domain.client.model.Client;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "MAC_CUSTOMER_LEVELING_DESCRIPTION")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class CustomerLevelingDescription extends BaseEntity<String> {

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private Client client;

	@Column(name = "DESCRIPTION", nullable = false, length = 1000)
	private String description;

}
