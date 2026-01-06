package com.theralink.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "APP_ORGANIZATION_STRUCTURE")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class OrganizationStructure extends BaseEntity<String> {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private OrganizationStructure parent;

	@NotNull
	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "CODE", nullable = false, unique = true)
	private String code;

	@Column(name = "ACTIVE")
	private Boolean active;

	@Column(name = "HIERARCHY_CODE")
	private String hierarchyCode;

	@Column(name = "DESCRIPTION")
	private String description;

	@ManyToMany(mappedBy = "organizations")
	private List<Person> persons;
}
