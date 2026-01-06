package com.theralink.repository.organizationStructure;

import com.theralink.domain.OrganizationStructure;
import com.theralink.utils.SecurityUtil;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class OrganizationFilterSpecification {

	public static <T> Specification<T> hasOrganizationAuthority() {
		List<OrganizationStructure> organizations = (List<OrganizationStructure>) SecurityUtil.getAuthenticatedUserOrganizations();
		return (root, query, builder) -> {
			if (organizations == null || organizations.isEmpty()) {
				return builder.disjunction(); // معادل FALSE
			}

			List<String> Ids = organizations.stream().map(OrganizationStructure::getId).toList();

			// فرض: موجودیت مربوطه یک فیلد به نام "clinic" دارد که به Clinic نگاشت دارد
			Join<T, OrganizationStructure> clinicJoin = root.join("organizations");

			// بررسی وجود clinicId در لیست
			return clinicJoin.get("id").in(Ids);
		};
	}
}
