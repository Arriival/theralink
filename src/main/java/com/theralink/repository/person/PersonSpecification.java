package com.theralink.repository.person;

import com.theralink.domain.OrganizationStructure;
import com.theralink.domain.Person;
import com.theralink.domain.user.Role;
import com.theralink.utils.SecurityUtil;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

public class PersonSpecification {

	public static Specification<Person> hasAccessToPersonOrganizations() {
		String personId = SecurityUtil.getAuthenticatedUser().getPerson().getId();
		return (root, query, cb) -> {
			// اگر نقش ADMIN بود، همه اشخاص بدون فیلتر برگردان
			if (SecurityUtil.getAuthenticatedUserRoles().contains(Role.ADMIN)) {
				return null; // بدون شرط = همه رکوردها
			}


			// حذف اشخاص تکراری (در صورت وجود چند سازمان مشترک)
			query.distinct(true);

			// زیر کوئری: گرفتن سازمان‌های شخص مورد نظر
			Subquery<String> subquery = query.subquery(String.class);
			Root<Person> subPerson = subquery.from(Person.class);
			Join<Person, OrganizationStructure> subOrgJoin = subPerson.join("organizations");

			subquery.select(subOrgJoin.get("id")) // ستون ID از OrganizationStructure
					.where(cb.equal(subPerson.get("id"), personId));

			// جوین اصلی برای بررسی سازمان‌های هر شخص
			Join<Person, OrganizationStructure> orgJoin = root.join("organizations");

			// شرط سازمان مشترک
			Predicate hasCommonOrg = orgJoin.get("id").in(subquery);

			// حذف خود شخص از نتایج
			//			Predicate notSelf = cb.notEqual(root.get("id"), personId);
			return hasCommonOrg;
		};
	}
}
