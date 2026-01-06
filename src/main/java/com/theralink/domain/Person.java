package com.theralink.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Blob;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "APP_PERSON", uniqueConstraints = { @UniqueConstraint(columnNames = "PERSON_CODE"),
												  @UniqueConstraint(columnNames = "NATIONAL_CODE"),
												  @UniqueConstraint(columnNames = "CELLPHONE") })
@Filter(name = "clinicFilter", condition = "(exists(select * from APP_PERSON_ORGANIZATION po where po.person_id = id and po.org_id = :clinicId ))")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class Person extends BaseEntity<String> {

	@Column(name = "PERSON_CODE", updatable = false)
	private String personCode;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "NATIONAL_CODE")
	private String nationalCode;

	@Column(name = "FATHER_NAME")
	private String fatherName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GENDER_ID")
	private BaseInformation gender;

	@Column(name = "ACTIVE")
	private Boolean active;

	@Email
	@Size(min = 5, max = 254)
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PERSON_IMAGE")
	private Blob personImage;

	@Column(name = "CELLPHONE")
	private String cellPhone;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MARRIAGE_STATUS_ID")
	private BaseInformation marriageStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EDUCATION_LEVEL_ID")
	private BaseInformation educationLevel;

	@Column(name = "BIRTH_DATE")
	private String birthDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BIRTH_PLACE_STATE_ID")
	private BaseInformation birthPlaceState;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BIRTH_PLACE_CITY_ID")
	private BaseInformation birthPlaceCity;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "DESCRIPTION")
	private String description;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "APP_PERSON_ORGANIZATION", joinColumns = @JoinColumn(name = "PERSON_ID", unique = false), inverseJoinColumns = @JoinColumn(name = "ORG_ID", unique = false))
	private List<OrganizationStructure> organizations;

	/*@OneToMany(mappedBy = "person")
	private List<PersonOrganization> personOrganizations;*/
}
