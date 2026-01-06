package com.theralink.web.controller;

import com.theralink.common.jasperReport.JasperPrint;
import com.theralink.common.jasperReport.ReportParameterList;
import com.theralink.common.mapping.ModelMapperUtil;
import com.theralink.service.reportService.IReportService;
import com.theralink.utils.DateUtility;
import com.theralink.web.controller.BaseController;
import com.theralink.domain.CounselingSession;
import com.theralink.service.counselingSession.ICounselingSessionService;
import com.theralink.web.dto.ConsultantSessionSumDto;
import com.theralink.web.dto.NumberOfCustomerSessionDto;
import com.theralink.web.dto.ISessionDescriptionsDto;
import com.theralink.web.viewModel.CounselingSessionViewModel;
import com.theralink.domain.client.dto.ClientViewModel;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

	@PostMapping(value = "/save")
	@ResponseBody
	public String save(@RequestBody CounselingSessionViewModel entity) {
		return iCounselingSessionService.save(ModelMapperUtil.map(entity, CounselingSession.class));
	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return iCounselingSessionService.deleteById(id);
	}

	@GetMapping(value = "/customer/history")
	public Page<CounselingSessionViewModel> customerSessionHistory(String customerId, boolean withAuthorize, Pageable pageable) {
		return ModelMapperUtil.mapPage(iCounselingSessionService.customerSessionHistory(customerId,withAuthorize, pageable), CounselingSessionViewModel.class);
	}

	@GetMapping(value = "/customer/sessions/{customerId}")
	public NumberOfCustomerSessionDto numberOfCustomerSession(@PathVariable String customerId) {
		return iCounselingSessionService.numberOfCustomerSession(customerId);
	}

	@GetMapping(value = "/current")
	public CounselingSessionViewModel currentSession() {
		return ModelMapperUtil.map(iCounselingSessionService.currentSession(), CounselingSessionViewModel.class);
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

	@GetMapping(value = "/consultant/customers")
	public Page<ClientViewModel> getConsultantCustomers(String personnelId, String search, Pageable pageable) {
		return ModelMapperUtil.mapPage(iCounselingSessionService.consultantCustomers(personnelId, search, pageable), ClientViewModel.class);
	}


	@GetMapping(value = "/customer/descriptions/timeline")
	public List<ISessionDescriptionsDto> sessionDescriptions(String customerId) {
		return iCounselingSessionService.sessionDescriptions(customerId);
	}


	@GetMapping(value = "/excel")
	public void report(String fromDate, String toDate, String personnelId, HttpServletResponse response) throws JRException, IOException {
		ReportParameterList parameterList = new ReportParameterList();
		List<JasperPrint> jasperPrintList = new ArrayList<>();
		response.setHeader("Set-Cookie", "fileDownload=true; path=/");
		parameterList.addParameter("fromDate", DateUtility.jalaliToDate(fromDate));
		Date var_toDate = DateUtility.jalaliToDate(toDate);
		var_toDate.setHours(23);
		var_toDate.setMinutes(59);
		var_toDate.setSeconds(59);
		parameterList.addParameter("toDate", var_toDate);
		parameterList.addParameter("personnelId", personnelId);
		String jrxmlPath = "/mehrazin-report.jrxml";
		jasperPrintList.add(new JasperPrint(jrxmlPath, parameterList));
		iReportService.exportXlsxJasperPrintList(response, "report", jasperPrintList);
	}
}
