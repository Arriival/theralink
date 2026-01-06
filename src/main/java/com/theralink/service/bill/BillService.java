package com.theralink.service.bill;

import com.theralink.common.annotations.ClinicFilter;
import com.theralink.domain.Bill;
import com.theralink.repository.IGenericRepository;
import com.theralink.repository.bill.IBillRepository;
import com.theralink.service.GenericService;
import com.theralink.web.dto.IBillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BillService extends GenericService<Bill, String> implements IBillService {

	@Autowired
	private IBillRepository iBillRepository;

	@Override
	protected IGenericRepository<Bill, String> getGenericRepo() {
		return iBillRepository;
	}

	@ClinicFilter
	@Override
	public Page<Bill> search(String fromDate, String toDate, Pageable pageable) {
		return iBillRepository.search(fromDate, toDate, pageable);
	}

	@ClinicFilter
	@Override
	public IBillDto sum(String fromDate, String toDate) {
		return iBillRepository.sum(fromDate, toDate);
	}
}
