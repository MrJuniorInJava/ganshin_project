package com.example.Ganshin.SecurityForApp.services;

import com.example.Ganshin.SecurityForApp.models.Person;
import com.example.Ganshin.SecurityForApp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonRegisterService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public PersonRegisterService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;

        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public void register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));//шифруем пароль
        person.setRole("ROLE_USER");
        personRepository.save(person);
    }
}
