package org.sergei.spring.lesson.DAO;

import org.sergei.spring.lesson.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    // LIST simple implementation of Database
//    private static int PERSON_COUNT;
//    private List<Person> personList;
//      {
//        personList = new ArrayList<>();
//        personList.add(new Person(++PERSON_COUNT, "Сергей",25,"ser@dsfsd.com"));
//        personList.add(new Person(++PERSON_COUNT, "Sergei",56,"gri@dsfsd.com"));
//        personList.add(new Person(++PERSON_COUNT, "Ivan",76,"ivan@dsfsd.com"));
//        personList.add(new Person(++PERSON_COUNT, "Bobby",23,"bob@dsfsd.com"));
//        personList.add(new Person(++PERSON_COUNT, "Denis",43,"denis@dsfsd.com"));
//    }

    // JDBC API implementation for receiving CONNECTION to DATABASE
//    private static final String URL = "jdbc:postgresql://localhost:5433/SpringMVC_DB";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "bangkok7";
//
//    private static Connection connection;
//
//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }


    //SPRING JDBCTemplate implementation for connect to Database

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // METHODS to work with DATABASE
    public List<Person> getAllpersonsList() {
        // We should use PersonMapper if our Person fields names are DIFFER from Database FIELDS NAMES
        return jdbcTemplate.query("SELECT * From Person;", new PersonMapper());
    }

    public Person getPersonById(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        // FOR CHANGE DATABASE we should use UPDATE method
        jdbcTemplate.update("INSERT INTO Person VALUES (1, ?, ?, ?)",
                person.getName(), person.getAge(), person.getEmail());

    }

    public void edit(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?",
                new Object[]{id}, new PersonMapper());
    }
}
