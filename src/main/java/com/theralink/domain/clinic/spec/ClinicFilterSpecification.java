package com.theralink.domain.clinic.spec;

import com.theralink.domain.BaseEntity;
import com.theralink.domain.clinic.model.Clinic;
import com.theralink.utils.SecurityUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ClinicFilterSpecification {

	public static <T> Specification<T> hasClinicAuthority() {
		List<Clinic> clinics = (List<Clinic>) SecurityUtil.getAuthenticatedUserOrganizations();
		return (root, query, builder) -> {
			// اگر لیست کلینیک خالی بود، هیچ چیزی برنگرداند (تطابقی وجود ندارد)
			if (clinics == null || clinics.isEmpty()) {
				return builder.disjunction(); // معادل FALSE
			}

			// استخراج ID کلینیک‌ها
			List<String> clinicIds = clinics.stream().map(Clinic::getId).toList();

			// فرض: موجودیت مربوطه یک فیلد به نام "clinic" دارد که به Clinic نگاشت دارد
			Join<T, Clinic> clinicJoin = root.join("clinic");

			// بررسی وجود clinicId در لیست
			return clinicJoin.get("id").in(clinicIds);
		};
	}

	public static <T extends BaseEntity<?>> Specification<T> byClinicId(String clinicId) {
		return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			if (clinicId == null) {
				return cb.conjunction(); // شرط خالی اگر clinicId null باشد
			}
			return cb.equal(root.get("clinicId"), clinicId);
		};
	}
}
