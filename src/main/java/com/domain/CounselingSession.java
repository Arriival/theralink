package com.domain;

import com.core.framework.domain.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "MAC_COUNSELING_SESSION")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class CounselingSession extends BaseEntity<String> {

	@ManyToOne
	@JoinColumn(name = "PERSONNEL_ID", nullable = false)
	private Personnel consultant;

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "INSURANCE_TARIFF_ID", nullable = false)
	private InsuranceTariff insuranceTariff;

	@Column(name = "START")
	private Date start;

	@Column(name = "END")
	private Date end;

	@Column(name = "CUSTOMER_FEE")
	private Float customerFee;

	@Column(name = "CONSULTANT_FEE")
	private Float consultantFee;

}
