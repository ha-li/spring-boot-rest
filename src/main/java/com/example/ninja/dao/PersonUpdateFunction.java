package com.example.ninja.dao;

import com.example.ninja.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public class PersonUpdateFunction implements Function<Person, Optional<Integer>> {

    private List<Person> db;
    private Person update;
    public PersonUpdateFunction(Person p, List<Person> DB) {
        this.db = DB;
        update = p;
    }

    @Override
    public Optional<Integer> apply(Person person) {
        int i = this.db.indexOf(person);
        if (i >= 0) {
            //Person original = this.db.get(i);
            //update.setId(person.getId());
            Person p = new Person(person.getId(), update.getName());
            db.set(i, p);
            return Optional.of(i);
        }
        return Optional.of(null);
    }
}
