package org.sergei.spring.lesson.DAO;

import org.sergei.spring.lesson.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PERSON_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/SpringMVC_DB";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "bangkok7";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


//    private List<Person> personList;
//      {
//        personList = new ArrayList<>();
//        personList.add(new Person(++PERSON_COUNT, "Сергей",25,"ser@dsfsd.com"));
//        personList.add(new Person(++PERSON_COUNT, "Sergei",56,"gri@dsfsd.com"));
//        personList.add(new Person(++PERSON_COUNT, "Ivan",76,"ivan@dsfsd.com"));
//        personList.add(new Person(++PERSON_COUNT, "Bobby",23,"bob@dsfsd.com"));
//        personList.add(new Person(++PERSON_COUNT, "Denis",43,"denis@dsfsd.com"));
//    }

    public List<Person> getAllpersonsList() {
        List<Person> personList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                personList.add(person);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personList;
    }


    public Person getPersonById(int id) {
//        return personList.stream()
//                .filter(person -> person.getId() == id)
//                .findAny()
//                .orElse(null);
        return null;
    }

    public void save(Person person) {
//        person.setId(++PERSON_COUNT);
//        personList.add(person);

        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES(" + 1
                    + ",'" + person.getName()
                    + "'," + person.getAge()
                    + ",'" + person.getEmail()
                    + "')";
            statement.executeUpdate(SQL);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void edit(int id, Person updatedPerson) {
        Person personForUpdate = getPersonById(id);

        personForUpdate.setAge(updatedPerson.getAge());
        personForUpdate.setName(updatedPerson.getName());
        personForUpdate.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
//        personList.removeIf(person -> person.getId() == id);
    }
}
