package com.domain;

import com.core.framework.domain.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "MAC_SETTING")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class Setting extends BaseEntity<String> {

    @Column(name = "S_KEY", nullable = false)
    private String key;

    @Column(name = "S_VALUE", nullable = false)
    private String value;

}
