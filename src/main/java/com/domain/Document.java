package com.domain;

import com.core.framework.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "APP_DOCUMENT")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class Document extends BaseEntity<String> {


	@Column(name = "CLASS_NAME")
	private String className;

	@Column(name = "OBJECT_ID")
	private String objectId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "TYPE")
	private String type;

	@Lob
	@Column(name = "DATA", length = Integer.MAX_VALUE)
	private byte[] data;
}
