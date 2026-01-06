package com.theralink.web.controller;

import com.theralink.common.mapping.ModelMapperUtil;
import com.theralink.web.controller.BaseController;
import com.theralink.domain.Bill;
import com.theralink.service.bill.IBillService;
import com.theralink.web.dto.IBillDto;
import com.theralink.web.viewModel.BillViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bill")
public class BillController extends BaseController {

	@Autowired
	private IBillService iBillService;

	@GetMapping(value = "/grid")
	public Page<BillViewModel> pagination(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "date"));
		return ModelMapperUtil.mapPage(iBillService.getAllGrid(pageable), BillViewModel.class);
	}

	@GetMapping(value = "/search")
	public Page<BillViewModel> search(String fromDate, String toDate, Pageable pageable) {
		return ModelMapperUtil.mapPage(iBillService.search(fromDate, toDate, pageable), BillViewModel.class);
	}

	@GetMapping(value = "/sum")
	public IBillDto sum(String fromDate, String toDate) {
		return iBillService.sum(fromDate, toDate);
	}

	@GetMapping(value = "/load/{id}")
	public BillViewModel load(@PathVariable String id) {
		return ModelMapperUtil.map(iBillService.load(id), BillViewModel.class);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public String save(@RequestBody BillViewModel entity) {
		return iBillService.save(ModelMapperUtil.map(entity, Bill.class));
	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return iBillService.deleteById(id);
	}

}
