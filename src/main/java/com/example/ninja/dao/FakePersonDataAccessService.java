package com.example.ninja.dao;

import com.example.ninja.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

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

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> maybe = selectPersonById(id);
        if(maybe.isPresent()){
            DB.remove(maybe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        Function<Person, Optional<Integer>> f = new PersonUpdateFunction(update, DB);
        /* p -> {
                    int indexOfPersonToDelete = DB.indexOf(person);
                    if (indexOfPersonToDelete >= 0) {
                        DB.set(indexOfPersonToDelete, person);
                        return 1;
                    }
                    return 0;
                }*/

        return selectPersonById(id).map(p -> f.apply(p))
                .orElse(Optional.of(0)).get();
        //return 0;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
