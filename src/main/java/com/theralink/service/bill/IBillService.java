package com.theralink.service.bill;

import com.theralink.service.IGenericService;
import com.theralink.domain.Bill;
import com.theralink.web.dto.IBillDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBillService extends IGenericService<Bill, String> {
	Page<Bill> search(String fromDate, String toDate, Pageable pageable);

	IBillDto sum(String fromDate, String toDate);
}
