package com.domain;

import com.core.framework.domain.BaseEntity;
import com.core.framework.domain.baseInformation.BaseInformation;
import com.core.framework.domain.person.Person;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MAC_CUSTOMER", uniqueConstraints = { @UniqueConstraint(columnNames = { "NATIONAL_CODE" }) })
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
	@JoinColumn(name = "INSURANCE_TARIFF_ID", nullable = false)
	private InsuranceTariff insuranceTariff;

	@ManyToOne
	@JoinColumn(name = "EDUCATION_LEVEL_ID")
	private BaseInformation educationLevel;

	@Column(name = "AGE", nullable = false)
	private int age;

	@Column(name = "JOB")
	private String job;

	@Column(name = "MARRIED")
	private Boolean married;

	@Column(name = "MARRIAGE_HISTORY")
	private Boolean marriageHistory;

	@Column(name = "GENO_GRAM")
	private String genoGram;

	@Column(name = "PSYCHOLOGIST_VISIT_HISTORY")
	private String psychologistVisitHistory;

	@Column(name = "DRUG_USE_HISTORY")
	private String drugUseHistory;

	@Column(name = "SICKNESS_HISTORY")
	private String sicknessHistory;

	@Column(name = "SLEEPING_STATUS")
	private String sleepingStatus;

	@Column(name = "LIFE_STYLE")
	private String lifeStyle;

	@Column(name = "PROBLEMS")
	private String problems;

	@Column(name = "PROBLEM_BEGINNING")
	private String problemBeginning;

	@Column(name = "PROBLEM_CAUSE")
	private String problemCause;

	@Column(name = "TESTS_DONE")
	private String testsDone;

	@Column(name = "FIRST_RECOGNITION")
	private String firstRecognition;

}
