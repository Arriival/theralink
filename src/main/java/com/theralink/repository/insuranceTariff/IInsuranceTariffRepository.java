package com.theralink.repository.insuranceTariff;

import com.theralink.repository.IGenericRepository;
import com.theralink.domain.InsuranceTariff;
import com.theralink.web.dto.IChartDto;
import lombok.Data;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IInsuranceTariffRepository extends IGenericRepository<InsuranceTariff, String> {

	@Query("select e from InsuranceTariff e where e.isDisabled <> true ")
	List<InsuranceTariff> findAllEnableList();

	@Query(value = "select  t.title as label, count(s) as value from InsuranceTariff t inner join CounselingSession s on s.insuranceTariff = t where s.start between :from and :to group by t.title ")
	List<IChartDto> chart(@Param("from") Date from, @Param("to") Date to);
}
