package com.repository.insuranceTariff;

import com.core.framework.repository.IGenericRepository;
import com.domain.InsuranceTariff;
import com.web.dto.IChartDto;
import lombok.Data;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IInsuranceTariffRepository extends IGenericRepository<InsuranceTariff, String> {

	@Query(value = "select  t.title as label, count(s) as value from InsuranceTariff t inner join CounselingSession s on s.insuranceTariff = t where s.start between :from and :to group by t.title ")
	List<IChartDto> chart(@Param("from") Date from, @Param("to") Date to);
}
