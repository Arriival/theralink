package com.theralink.domain;

import com.core.framework.domain.BaseEntity;
import com.core.framework.domain.BaseInformation;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "MAC_CONSULTANT_TYPE")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class ConsultantType extends BaseEntity<String> {
	@ManyToOne
	@JoinColumn(name = "EDUCATION_LEVEL_ID", nullable = false)
	private BaseInformation educationLevel;

	@Column(name = "AMOUNT", nullable = false)
	private Float amount;

}
