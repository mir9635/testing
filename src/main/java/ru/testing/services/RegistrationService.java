package ru.testing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testing.models.Person;
import ru.testing.repositories.PeopleRepository;

import java.util.Date;

@Service
public class RegistrationService {

    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public  void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        person.setBan(false);
        person.setRegistrayionDate(new Date());
        person.setPhoto("https://img2.freepng.ru/20180327/ziq/kisspng-computer-icons-user-profile-avatar-profile-5ab9c9868b8c84.1893767815221251905716.jpg");
        peopleRepository.save(person);
    }
}
