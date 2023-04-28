package com.web.controller;

import com.core.framework.common.mapping.ModelMapperUtil;
import com.core.framework.web.controller.BaseController;
import com.domain.CounselingSession;
import com.service.counselingSession.ICounselingSessionService;
import com.web.dto.ConsultantSessionSumDto;
import com.web.viewModel.CounselingSessionViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("counselingSession")
public class CounselingSessionController extends BaseController {

	@Autowired
	private ICounselingSessionService iCounselingSessionService;

	@GetMapping(value = "/grid")
	public Page<CounselingSessionViewModel> pagination(Pageable pageable) {
		return ModelMapperUtil.mapPage(iCounselingSessionService.getAllGrid(pageable), CounselingSessionViewModel.class);
	}

	@GetMapping(value = "/customer/history")
	public Page<CounselingSessionViewModel> customerSessionHistory(String customerId, Pageable pageable) {
		return ModelMapperUtil.mapPage(iCounselingSessionService.customerSessionHistory(customerId, pageable), CounselingSessionViewModel.class);
	}

	@GetMapping(value = "/consultant/history")
	public Page<CounselingSessionViewModel> consultantSessionHistory(String personnelId, String insuranceTariffId, String fromDate, String toDate, Pageable pageable) {
		return ModelMapperUtil.mapPage(iCounselingSessionService.consultantSessionHistory(personnelId, insuranceTariffId, fromDate, toDate, pageable), CounselingSessionViewModel.class);
	}

	@GetMapping(value = "/consultant/sum")
	public ConsultantSessionSumDto consultantSessionSum(String personnelId, String insuranceTariffId, String fromDate, String toDate) {
		return iCounselingSessionService.consultantSessionSum(personnelId, fromDate, toDate, insuranceTariffId);
	}

	@GetMapping(value = "/inProcess")
	public List<CounselingSessionViewModel> inProcessList() {
		return ModelMapperUtil.mapList(iCounselingSessionService.inProcessList(), CounselingSessionViewModel.class);
	}

	@GetMapping(value = "/finish/{id}")
	public boolean finish(@PathVariable String id) {
		return iCounselingSessionService.finishSession(id);
	}

	@GetMapping(value = "/load/{id}")
	public CounselingSessionViewModel load(@PathVariable String id) {
		return ModelMapperUtil.map(iCounselingSessionService.load(id), CounselingSessionViewModel.class);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public String save(@RequestBody CounselingSessionViewModel entity) {
		return iCounselingSessionService.save(ModelMapperUtil.map(entity, CounselingSession.class));
	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return iCounselingSessionService.deleteById(id);
	}

}
