package com.theralink.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "APP_GROUP")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class Group extends BaseEntity<String> {

    @Column(name = "name")
    private String name;

    @Column(name = "ACTIVE")
    private Boolean active;

}
