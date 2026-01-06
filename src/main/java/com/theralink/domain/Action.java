package com.theralink.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
@Table(name = "APP_ACTION")
public class Action extends BaseEntity<String> {

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Action parent;

}
