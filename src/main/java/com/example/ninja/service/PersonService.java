package com.example.ninja.service;

import com.example.ninja.dao.PersonDao;
import com.example.ninja.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service  // an alias for component
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService (@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(UUID.randomUUID(), person);
    }


    public List<Person> selectAllPerson() {
        return personDao.selectAll();
    }
}
