package com.domain;

import com.core.framework.domain.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

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
