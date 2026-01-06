package com.theralink.domain;

import com.theralink.domain.clinic.model.Clinic;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;

@Getter
@Setter
@Entity
@Table(name = "APP_BASE_INFORMATION")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class BaseInformation extends BaseEntity<String> {

    @NotNull
    @Column(name = "TOPIC", nullable = false)
    private String topic;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private BaseInformationHeader parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "master_information_id")
    private BaseInformation masterBaseInformation;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "description")
    private String description;
    //hierarchicode
    //islock
}
