package com.theralink.domain;

import com.theralink.domain.client.model.Client;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "MAC_REFERENCES")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class References extends BaseEntity<String> {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REFERRER_ID", nullable = false)
	private Personnel referrer; // erja dahandeh

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REFERRED_TO", nullable = false)
	private Personnel referredTo; // erja shode be

	@Lob
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "iS_VISITED")
	private Boolean isVisited;

}
