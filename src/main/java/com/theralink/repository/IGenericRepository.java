package com.theralink.repository;

import com.theralink.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface IGenericRepository<T extends BaseEntity<PK>, PK extends Serializable> extends JpaRepository<T, PK>, JpaSpecificationExecutor<T> {
}
