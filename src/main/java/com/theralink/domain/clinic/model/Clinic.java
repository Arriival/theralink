package com.theralink.domain.clinic.model;

import com.core.framework.domain.BaseInformation;
import com.core.framework.domain.OrganizationStructure;
import com.theralink.domain.Document;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
//@Table(name = "MAC_CLINIC")
//@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class Clinic extends OrganizationStructure {
/*
	@Column(name = "NAME")
	private String name;*/

	@Column(name = "MANAGER")
	private String manager;

	@ManyToOne
	@JoinColumn(name = "PROVINCE")
	private BaseInformation province;

	@ManyToOne
	@JoinColumn(name = "CITY")
	private BaseInformation city;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "PHONE1")
	private String phone1;

	@Column(name = "PHONE2")
	private String phone2;

	@ManyToOne
	@JoinColumn(name = "LOGO_ID")
	private Document logo;

}
