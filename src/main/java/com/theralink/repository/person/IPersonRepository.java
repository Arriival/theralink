package com.theralink.repository.person;

import com.theralink.domain.Person;
import com.theralink.repository.IGenericRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPersonRepository extends IGenericRepository<Person, String>, JpaSpecificationExecutor<Person> {

	@Query(value = "select e from Person e where e.nationalCode =:nationalCode")
	Person loadByNationalCode(@Param("nationalCode") String nationalCode);

	@Query(value = "select max(e.personCode) from Person e ")
	String getLatestPersonCode();

	@Query(value = "select e from Person e inner join e.organizations o where e.id not in (select u.person.id from  User u ) and ( o.id in :oIds or 'ADMIN' in :roles )")
	List<Person> unRegisteredPersons(@Param("oIds") List<String> oIds, @Param("roles") List<String> roles);

}
