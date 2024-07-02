package com.example.habittracker.service.person;

import com.example.habittracker.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> getPersons(String search);
}
