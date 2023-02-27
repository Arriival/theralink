package com.domain;

import com.core.framework.domain.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table(name = "MAC_SECRETARY_WORK_TIME")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class SecretaryWorkTime extends BaseEntity<String> {
    @ManyToOne
    @JoinColumn(name = "PERSONNEL_ID", nullable = false)
    private Personnel secretary;

    @Column(name = "START", nullable = false)
    private Timestamp start;

    @Column(name = "END")
    private Timestamp end;

}
