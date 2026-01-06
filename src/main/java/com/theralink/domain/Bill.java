package com.theralink.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "MAC_BILL")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class Bill extends BaseEntity<String> {

	@Column(name = "SUBJECT", nullable = false)
	private String subject;

	@Column(name = "F_DATE", nullable = false)
	private String date;

	@Column(name = "INCOME")
	private Double income;

	@Column(name = "COST")
	private Double cost;

	@Column(name = "DESCRIPTION")
	private String description;

}
