package com.web.controller;

import com.core.framework.common.jasperReport.JasperPrint;
import com.core.framework.common.jasperReport.ReportParameterList;
import com.core.framework.common.mapping.ModelMapperUtil;
import com.core.framework.service.reportService.IReportService;
import com.core.framework.utils.DateUtility;
import com.core.framework.web.controller.BaseController;
import com.domain.CounselingSession;
import com.service.counselingSession.ICounselingSessionService;
import com.web.dto.ConsultantSessionSumDto;
import com.web.dto.NumberOfCustomerSessionDto;
import com.web.viewModel.CounselingSessionViewModel;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("counselingSession")
public class CounselingSessionController extends BaseController {

	@Autowired
	private ICounselingSessionService iCounselingSessionService;

	@Autowired
	private IReportService iReportService;

	@GetMapping(value = "/grid")
	public Page<CounselingSessionViewModel> pagination(Pageable pageable) {
		return ModelMapperUtil.mapPage(iCounselingSessionService.getAllGrid(pageable), CounselingSessionViewModel.class);
	}

	@GetMapping(value = "/customer/history")
	public Page<CounselingSessionViewModel> customerSessionHistory(String customerId, Pageable pageable) {
		return ModelMapperUtil.mapPage(iCounselingSessionService.customerSessionHistory(customerId, pageable), CounselingSessionViewModel.class);
	}

	@GetMapping(value = "/customer/sessions/{customerId}")
	public NumberOfCustomerSessionDto numberOfCustomerSession(@PathVariable String customerId) {
		return iCounselingSessionService.numberOfCustomerSession(customerId);
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

	@GetMapping(value = "/excel")
	public void report(String fromDate, String toDate,String personnelId, HttpServletResponse response) throws JRException, IOException {
		ReportParameterList parameterList = new ReportParameterList();
		List<JasperPrint> jasperPrintList = new ArrayList<>();
		response.setHeader("Set-Cookie", "fileDownload=true; path=/");
		parameterList.addParameter("fromDate", DateUtility.jalaliToDate(fromDate));
		parameterList.addParameter("toDate", DateUtility.jalaliToDate(toDate));
		parameterList.addParameter("personnelId", personnelId);
		String jrxmlPath = "/mehrazin-report.jrxml";
		jasperPrintList.add(new JasperPrint(jrxmlPath, parameterList));
		iReportService.exportXlsxJasperPrintList(response, "report", jasperPrintList);
	}

}
