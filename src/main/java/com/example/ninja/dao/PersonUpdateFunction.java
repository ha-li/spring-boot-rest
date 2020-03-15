package com.example.ninja.dao;

import com.example.ninja.model.Person;

import java.util.List;
import java.util.function.Function;


public class PersonUpdateFunction implements Function<Person, Integer> {

    private List<Person> db;
    private Person update;
    public PersonUpdateFunction(Person p, List<Person> DB) {
        this.db = DB;
        update = p;
    }

    @Override
    public Integer apply(Person person) {
        int i = this.db.indexOf(person);
        if (i >= 0) {
            Person p = new Person(person.getId(), update.getName());
            db.set(i, p);
            return i;
        }
        return 0;
    }
}
