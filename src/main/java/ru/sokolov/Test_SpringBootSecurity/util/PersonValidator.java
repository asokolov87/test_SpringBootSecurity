package ru.sokolov.Test_SpringBootSecurity.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sokolov.Test_SpringBootSecurity.dao.PersonDAO;
import ru.sokolov.Test_SpringBootSecurity.models.Person;
import ru.sokolov.Test_SpringBootSecurity.services.PersonService;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonDAO personDAO, PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (personService.getPersonByUsername(person.getUsername()).isPresent())
            errors.rejectValue("username", "", "Человек с таким username уже существует");
    }
}