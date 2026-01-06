package com.theralink.web.controller;

import com.theralink.common.mapping.ModelMapperUtil;
import com.theralink.domain.Person;
import com.theralink.service.person.IPersonService;
import com.theralink.web.controller.BaseController;
import com.theralink.web.viewModel.organizationStructure.OrganizationStructureLiteViewModel;
import com.theralink.web.viewModel.person.LimitedPersonViewModel;
import com.theralink.web.viewModel.person.PersonViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController extends BaseController {
	@Autowired
	private IPersonService iPersonService;

	@GetMapping(value = "/grid")
	public Page<PersonViewModel> pagination(Pageable pageable) {
		return ModelMapperUtil.mapPage(iPersonService.getAllGridByClinicFilter(pageable), PersonViewModel.class);
	}

	@GetMapping(value = "/list")
	public List<PersonViewModel> list() {
		return ModelMapperUtil.mapList(iPersonService.getAll(), PersonViewModel.class);
	}

	@GetMapping(value = "/unRegisteredPersons")
	public List<LimitedPersonViewModel> unRegisteredPersons() {
		return ModelMapperUtil.mapList(iPersonService.unRegisteredPersons(), LimitedPersonViewModel.class);
	}

	@GetMapping(value = "/load/{id}")
	public PersonViewModel load(@PathVariable String id) {
		return ModelMapperUtil.map(iPersonService.load(id), PersonViewModel.class);
	}

	@GetMapping(value = "/orgStruct/list/{personId}")
	public List<OrganizationStructureLiteViewModel> personOrgStructList(@PathVariable String personId) {
		return ModelMapperUtil.mapList(iPersonService.load(personId).getOrganizations(), OrganizationStructureLiteViewModel.class);
	}

	@GetMapping(value = "/loadByNationalCode/{nationalCode}")
	public PersonViewModel loadByNationalCode(@PathVariable String nationalCode) {
		return ModelMapperUtil.map(iPersonService.loadByNationalCode(nationalCode), PersonViewModel.class);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public String save(@RequestBody PersonViewModel entity) {
		return iPersonService.save(ModelMapperUtil.map(entity, Person.class));
	}

	@DeleteMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable String id) {
		return iPersonService.deleteById(id);
	}

}
