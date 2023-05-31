package com.domain;

import com.core.framework.domain.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "MAC_INSURANCE_TARIFF")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class InsuranceTariff extends BaseEntity<String> {

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "SESSION_TIME")
	private Integer sessionTime;

	@Column(name = "CUSTOMER_RECEIVED_FACTOR")
	private Float customerReceivedFactor;

	@Column(name = "LISANC_PAYMENT_FACTOR", nullable = false)
	private Float lisancPaymentFactor;

	@Column(name = "ARSHAD_PAYMENT_FACTOR", nullable = false)
	private Float arshadPaymentFactor;

	@Column(name = "DRSTD_PAYMENT_FACTOR", nullable = false)
	private Float drStdPaymentFactor;

	@Column(name = "DR_PAYMENT_FACTOR", nullable = false)
	private Float drPaymentFactor;

	@Column(name = "POSTDR_PAYMENT_FACTOR", nullable = false)
	private Float postDrPaymentFactor;

}
