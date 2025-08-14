package com.theralink.repository.bill;

import com.core.framework.repository.IGenericRepository;
import com.theralink.domain.Bill;
import com.theralink.web.dto.IBillDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IBillRepository extends IGenericRepository<Bill, String> {

	@Query("select e from Bill e where ( :fromDate is null  or e.date >= :fromDate) and (:toDate is null or e.date <= :toDate ) order by e.createdDate desc ")
	Page<Bill> search(@Param("fromDate") String fromDate, @Param("toDate") String toDate, Pageable pageable);

	@Query("select sum(e.cost) as cost, sum (e.income) as income from Bill e where (:fromDate is null  or e.date >= :fromDate) and (:toDate is null or e.date <= :toDate ) ")
	IBillDto sum(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
}

