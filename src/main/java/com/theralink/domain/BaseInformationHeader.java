package com.theralink.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "APP_BASE_INFORMATION_HEADER")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class BaseInformationHeader extends BaseEntity<String> {

	@NotNull
	@Column(name = "TOPIC", nullable = false)
	private String topic;

	@Column(name = "active")
	private Boolean active;
}
