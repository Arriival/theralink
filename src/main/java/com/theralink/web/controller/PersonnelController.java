package com.theralink.web.controller;

import com.core.framework.common.jasperReport.JasperPrint;
import com.core.framework.common.jasperReport.ReportParameterList;
import com.core.framework.common.mapping.ModelMapperUtil;
import com.core.framework.service.reportService.IReportService;
import com.core.framework.utils.SecurityUtil;
import com.core.framework.web.controller.BaseController;
import com.theralink.domain.Personnel;
import com.theralink.domain.PersonnelType;
import com.theralink.service.personnel.IPersonnelService;
import com.theralink.web.viewModel.PersonnelViewModel;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("personnel")
public class PersonnelController extends BaseController {

	@Autowired
	private IPersonnelService iPersonnelService;

	@Autowired
	private IReportService iReportService;

	@GetMapping(value = "/grid/{type}")
	public Page<PersonnelViewModel> pagination(@PathVariable PersonnelType type, Pageable pageable) {
		return ModelMapperUtil.mapPage(iPersonnelService.getAllGrid(type, pageable), PersonnelViewModel.class);
	}

	@GetMapping(value = "/consultants")
	public List<PersonnelViewModel> consultantsList() {
		return ModelMapperUtil.mapList(iPersonnelService.consultantsList(), PersonnelViewModel.class);
	}

	@GetMapping(value = "/load/{id}")
	public PersonnelViewModel load(@PathVariable String id) {
		return ModelMapperUtil.map(iPersonnelService.load(id), PersonnelViewModel.class);
	}

	@GetMapping(value = "/authenticated/info")
	public PersonnelViewModel getPersonnelId() {
		return ModelMapperUtil.map(iPersonnelService.loadByPersonId(SecurityUtil.getAuthenticatedUser().getPerson().getId()), PersonnelViewModel.class);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public String save(@RequestBody PersonnelViewModel entity) {
		return iPersonnelService.save(ModelMapperUtil.map(entity, Personnel.class));
	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return iPersonnelService.deleteById(id);
	}

	@GetMapping(value = "/consultant/report")
	public void report(String year, String month, HttpServletResponse response) throws JRException, IOException {
		ReportParameterList parameterList = new ReportParameterList();
		List<JasperPrint> jasperPrintList = new ArrayList<>();
		response.setHeader("Set-Cookie", "fileDownload=true; path=/");
		parameterList.addParameter("var_year", year);
		parameterList.addParameter("var_month", month);
		String jrxmlPath = "/mehrazin-report.jrxml";
		jasperPrintList.add(new JasperPrint(jrxmlPath, parameterList));
		iReportService.exportXlsxJasperPrintList(response, "report", jasperPrintList);
	}
}
