package com.theralink.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "MAC_SECRETARY_WORK_TIME")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class SecretaryWorkTime extends BaseEntity<String> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSONNEL_ID", nullable = false)
    private Personnel secretary;

    @Column(name = "START", nullable = false)
    private Date start;

    @Column(name = "END")
    private Date end;

    @Column(name = "SALARY")
    private Float salary;

}
