package com.theralink.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "APP_FILE")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class File extends BaseEntity<String> {


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
