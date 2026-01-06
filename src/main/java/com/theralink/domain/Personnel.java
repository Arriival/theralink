package com.theralink.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "MAC_PERSONNEL")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class Personnel extends BaseEntity<String> {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID")
	private Person person;

	@Column
	@Enumerated(EnumType.STRING)
	private PersonnelType personnelType;

	@Column(name = "SECRETARY_WAGE")
	private Float secretaryHourlyWage;

}
