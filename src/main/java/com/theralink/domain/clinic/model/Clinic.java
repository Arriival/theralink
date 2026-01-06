package com.theralink.domain.clinic.model;

import com.theralink.domain.BaseInformation;
import com.theralink.domain.Document;
import com.theralink.domain.OrganizationStructure;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROVINCE")
	private BaseInformation province;

	@ManyToOne(fetch = FetchType.LAZY)
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOGO_ID")
	private Document logo;

	public Clinic(String id) {
		super.setId(id);
	}

	public Clinic() {

	}
}
