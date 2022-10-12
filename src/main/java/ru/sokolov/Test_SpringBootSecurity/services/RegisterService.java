package ru.sokolov.Test_SpringBootSecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sokolov.Test_SpringBootSecurity.models.Person;
import ru.sokolov.Test_SpringBootSecurity.repositories.PersonRepository;


@Service
public class RegisterService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }


}
