package com.theralink.domain;

import com.theralink.domain.client.model.Client;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "MAC_COUNSELING_SESSION")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class CounselingSession extends BaseEntity<String> {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSONNEL_ID", nullable = false)
	private Personnel consultant;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSURANCE_TARIFF_ID", nullable = false)
	private InsuranceTariff insuranceTariff;

	@Column(name = "START")
	private Date start;

	@Column(name = "END")
	private Date end;

	@Column(name = "CUSTOMER_FEE")
	private Float customerFee;

	@Column(name = "SESSION_COUNT")
	private Float sessionCount;

	@Column(name = "CONSULTANT_FEE")
	private Float consultantFee;

	@Lob
	@Column(name = "SESSION_DESCRIPTION" )
	private String sessionDescription;

	@Lob
	@Column(name = "NEXT_MEETING_AGENDA")
	private String nextMeetingAgenda;

}
