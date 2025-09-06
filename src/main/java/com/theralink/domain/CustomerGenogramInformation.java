package com.theralink.domain;

import com.core.framework.domain.BaseEntity;
import com.core.framework.domain.BaseInformation;
import com.theralink.domain.client.model.Client;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "MAC_CUSTOMER_GENOGRAM_INFORMATION")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class CustomerGenogramInformation extends BaseEntity<String> {

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "FIELD")
	private BaseInformation field;

	@Column(name = "DESCRIPTION", length = 99999)
	private String description;

}
