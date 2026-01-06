package com.theralink.repository.baseInformation;

import com.theralink.domain.BaseInformation;
import com.theralink.repository.IGenericRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBaseInformationRepository extends IGenericRepository<BaseInformation, String>, JpaSpecificationExecutor<BaseInformation> {

	@Query(value = "select e from BaseInformation e where e.parent.id  = :headerId ")
	List<BaseInformation> getAll(@Param("headerId") String headerId);

	@Query(value = "select e from BaseInformation e where e.parent.id =:headerId  and e.masterBaseInformation is null and e.active = true")
	List<BaseInformation> rootList(@Param("headerId") String headerId);

	@Query(value = "select e from BaseInformation e where e.masterBaseInformation.id  = :id")
	List<BaseInformation> listByMasterId(@Param("id") String id);
}
