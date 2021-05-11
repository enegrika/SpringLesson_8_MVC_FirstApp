package org.sergei.spring.lesson.DAO;

import org.sergei.spring.lesson.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PERSON_COUNT;
    private List<Person> personList;


      {
        personList = new ArrayList<>();
        personList.add(new Person(++PERSON_COUNT, "Andrei"));
        personList.add(new Person(++PERSON_COUNT, "Sergei"));
        personList.add(new Person(++PERSON_COUNT, "Ivan"));
        personList.add(new Person(++PERSON_COUNT, "Vladimir"));
        personList.add(new Person(++PERSON_COUNT, "Denis"));
    }

    public List<Person> personList(){
        return personList;
    }


    public Person show(int id){
        return personList.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);
    }
}
