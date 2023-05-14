package com.service.bill;

import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.domain.Bill;
import com.domain.Customer;
import com.repository.bill.IBillRepository;
import com.repository.customer.ICustomerRepository;
import com.service.customer.ICustomerService;
import com.web.dto.IBillDto;
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

	@Override
	public Page<Bill> search(String fromDate, String toDate, Pageable pageable) {
		return iBillRepository.search(fromDate, toDate, pageable);
	}

	@Override
	public IBillDto sum(String fromDate, String toDate) {
		return iBillRepository.sum(fromDate, toDate);
	}
}
