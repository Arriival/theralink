package com.theralink.domain;

import com.core.framework.domain.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "MAC_CUSTOMER_LEVELING")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class CustomerLeveling extends BaseEntity<String> {

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "DURATION", nullable = false)
	private int duration;

	@Column(name = "COLOR")
	private String color;
}
