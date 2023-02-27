package com.domain;

import com.core.framework.domain.BaseEntity;
import com.core.framework.domain.person.Person;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MAC_PERSONNEL")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class Personnel extends BaseEntity<String> {
    @ManyToOne
    @JoinColumn(name = "person_Id")
    private Person person;

    @Column
    @Enumerated(EnumType.STRING)
    private PersonnelType personnelType;

}
