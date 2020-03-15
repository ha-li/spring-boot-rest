package com.example.ninja.service;

import com.example.ninja.dao.PersonDao;
import com.example.ninja.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Optional<Person> getPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }

    public int deletePerson(UUID id) {
        return personDao.deletePersonById(id);
    }

    public int updatePerson(UUID id, Person person) {
        return personDao.updatePersonById(id, person);
    }
}
