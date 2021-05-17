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
        personList.add(new Person(++PERSON_COUNT, "Сергей",25,"ser@dsfsd.com"));
        personList.add(new Person(++PERSON_COUNT, "Sergei",56,"gri@dsfsd.com"));
        personList.add(new Person(++PERSON_COUNT, "Ivan",76,"ivan@dsfsd.com"));
        personList.add(new Person(++PERSON_COUNT, "Bobby",23,"bob@dsfsd.com"));
        personList.add(new Person(++PERSON_COUNT, "Denis",43,"denis@dsfsd.com"));
    }

    public List<Person> getAllpersonsList(){
        return personList;
    }


    public Person getPersonById(int id){
        return personList.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);
    }

    public void save(Person person){
          person.setId(++PERSON_COUNT);
          personList.add(person);
    }

    public void edit(int id, Person updatedPerson){
          Person personForUpdate = getPersonById(id);

          personForUpdate.setAge(updatedPerson.getAge());
          personForUpdate.setName(updatedPerson.getName());
          personForUpdate.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id){
          Person personToDelete = getPersonById(id);
          personList.removeIf(person -> person.getId() == id);
    }
}
