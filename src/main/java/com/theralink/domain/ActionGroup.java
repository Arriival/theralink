package com.theralink.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "APP_ACTION_GROUP")
@GenericGenerator(name = "sequence_db", strategy = "org.hibernate.id.UUIDGenerator")
public class ActionGroup extends BaseEntity<String> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTION_ID")
    private Action action;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID")
    private Group group;

}
