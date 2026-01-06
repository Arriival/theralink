package com.theralink.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "MAC_SETTING")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class Setting extends BaseEntity<String> {

	@Column(name = "S_KEY", nullable = false)
	private String key;

	@Column(name = "S_VALUE", nullable = false)
	private String value;

	@Column(name = "TITLE")
	private String title;

}
