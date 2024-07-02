package com.example.habittracker.service.person;

import com.example.habittracker.entity.Person;
import com.example.habittracker.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;


    @Override
    public List<Person> getPersons(String search) {
        return null;
    }
}
