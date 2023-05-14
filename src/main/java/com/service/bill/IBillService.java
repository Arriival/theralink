package com.service.bill;

import com.core.framework.service.IGenericService;
import com.domain.Bill;
import com.web.dto.IBillDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBillService extends IGenericService<Bill, String> {
	Page<Bill> search(String fromDate, String toDate, Pageable pageable);

	IBillDto sum(String fromDate, String toDate);
}
