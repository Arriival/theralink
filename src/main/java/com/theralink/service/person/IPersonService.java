package com.theralink.service.person;

import com.theralink.domain.Person;
import com.theralink.service.IGenericService;

import java.util.List;

public interface IPersonService extends IGenericService<Person, String> {

    Person loadByNationalCode(String title);

    String save(Person person);

    String generateNewPersonCode();

    List<Person> unRegisteredPersons();
}
