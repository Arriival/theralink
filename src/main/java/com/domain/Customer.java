package com.domain;

import com.core.framework.domain.BaseEntity;
import com.core.framework.domain.person.Person;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MAC_CUSTOMER",uniqueConstraints = { @UniqueConstraint(columnNames = { "NATIONAL_CODE" }) })
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class Customer extends BaseEntity<String> {

	@Column(name = "FIRSTNAME", nullable = false)
	private String firstname;

	@Column(name = "LASTNAME", nullable = false)
	private String lastname;

	@Column(name = "NATIONAL_CODE", nullable = false)
	private String nationalCode;

	@Column(name = "PHONE", nullable = false)
	private String phone;

	@Column(name = "ADDRESS")
	private String address;

	@ManyToOne
	@JoinColumn(name = "INSURANCE_TARIFF_ID",nullable = false)
	private InsuranceTariff insuranceTariff;

}
