package com.example.ninja.dao;

import com.example.ninja.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeDao")  // an alias for @Component
public class FakePersonDataAccessService implements PersonDao {
    private static List<Person> DB;

    static {
        DB = new ArrayList<>();
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add (new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAll(){
        return DB;
    }
}
