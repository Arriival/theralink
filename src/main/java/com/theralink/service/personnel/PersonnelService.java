package com.theralink.service.personnel;

import com.core.framework.domain.OrganizationStructure;
import com.core.framework.domain.Person;
import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.core.framework.service.person.IPersonService;
import com.theralink.domain.Personnel;
import com.theralink.domain.PersonnelType;
import com.theralink.repository.personnel.IPersonnelRepository;
import com.theralink.utils.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonnelService extends GenericService<Personnel, String> implements IPersonnelService {

	@Autowired
	private IPersonnelRepository iPersonnelRepository;

	@Autowired
	private IPersonService iPersonService;

	@Override
	protected IGenericRepository<Personnel, String> getGenericRepo() {
		return iPersonnelRepository;
	}

	@Override
	public Page<Personnel> getAllGrid(PersonnelType type, Pageable pageable) {
		return iPersonnelRepository.getAllGrid(type, UserContextHolder.getClinic(), pageable);
	}

	@Override
	public Personnel loadByPersonId(String personId) {
		return iPersonnelRepository.loadByPersonId(personId);
	}

	@Override
	public List<Personnel> consultantsList() {
		return iPersonnelRepository.consultantsList(UserContextHolder.getClinic());
	}

	@Override
	@Transactional
	public String save(Personnel entity) {
		// بررسی اینکه شخص با این کد ملی وجود دارد یا نه
		Person loadedByNationalCode = iPersonService.loadByNationalCode(entity.getPerson().getNationalCode());

		// اگر شخص وجود نداشت → ساخت شخص جدید
		if (loadedByNationalCode == null) {
			Person person = entity.getPerson();

			// ساخت لیست سازمان و اضافه کردن کلینیک جاری
			OrganizationStructure organizationStructure = new OrganizationStructure();
			organizationStructure.setId(UserContextHolder.getClinic());
			List<OrganizationStructure> organizationStructures = new ArrayList<>();
			organizationStructures.add(organizationStructure);

			person.setOrganizations(organizationStructures);
			person.setActive(true);

			// ذخیره شخص و انتساب ID
			String personId = iPersonService.save(person);
			person.setId(personId);

			// اتصال شخص به پرسنل
			entity.setPerson(person);

			// ذخیره Personnel و برگرداندن ID آن
			return super.save(entity);
		}

		// اگر شخص وجود داشت → اضافه کردن سازمان جدید در صورت نبود
		if (loadedByNationalCode.getOrganizations() == null) {
			loadedByNationalCode.setOrganizations(new ArrayList<>());
		}

		boolean exists = loadedByNationalCode.getOrganizations().stream().anyMatch(o -> o.getId().equals(UserContextHolder.getClinic()));

		if (!exists) {
			OrganizationStructure newOrg = new OrganizationStructure();
			newOrg.setId(UserContextHolder.getClinic());
			loadedByNationalCode.getOrganizations().add(newOrg);
		}

		// ذخیره شخص
		iPersonService.save(loadedByNationalCode);

		// حالا باید Personnel رو ذخیره کنیم یا ID موجودش رو برگردونیم
		entity.setPerson(loadedByNationalCode);
		return super.save(entity);
	}

}
