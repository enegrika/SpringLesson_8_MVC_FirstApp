package org.sergei.spring.lesson.DAO;

import org.sergei.spring.lesson.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> persons;
    private static int PERSON_COUNT;

    {
        persons = new ArrayList<>();
        persons.add(new Person(++PERSON_COUNT, "Andrei"));
        persons.add(new Person(++PERSON_COUNT, "Sergei"));
        persons.add(new Person(++PERSON_COUNT, "Ivan"));
        persons.add(new Person(++PERSON_COUNT, "Vladimir"));
        persons.add(new Person(++PERSON_COUNT, "Denis"));
    }

    public List<Person> personList(){
        return persons;
    }


    public Person show(int id){
        return persons.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);
    }
}
