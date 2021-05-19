package org.sergei.spring.lesson.DAO;

import org.sergei.spring.lesson.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

//    private static int PERSON_COUNT;
//    private List<Person> personList;
//
//    {
//        personList = new ArrayList<>();
//        personList.add(new Person(++PERSON_COUNT, "Сергей", 25, "ser@dsfsd.com"));
//        personList.add(new Person(++PERSON_COUNT, "Sergei", 56, "gri@dsfsd.com"));
//        personList.add(new Person(++PERSON_COUNT, "Ivan", 76, "ivan@dsfsd.com"));
//        personList.add(new Person(++PERSON_COUNT, "Bobby", 23, "bob@dsfsd.com"));
//        personList.add(new Person(++PERSON_COUNT, "Denis", 43, "denis@dsfsd.com"));
//    }


//    private static final String URL = "jdbc:postgresql://localhost:5433/SpringMVC_DB";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "bangkok7";
//    private static final String databaseDriver = "org.postgresql.Driver";

    private static final String URL = "jdbc:mysql://localhost:3306/springmvc_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "UserAccount@1975";
    private static final String databaseDriver = "com.mysql.cj.jdbc.Driver";

    private static Connection connection;

    static {
        try {
            Class.forName(databaseDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Person> getAllpersonsList() {
        List<Person> personsList = new ArrayList<>();

        // HERE WE CAN USE normal STATEMENT object because SQL query here is STATIC - can not change dynamically
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                personsList.add(person);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personsList;
    }


    public Person getPersonById(int id) {
        Person person = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Person WHERE id=?");
            preparedStatement.setInt(1, id);

            // POLUCHAEM posle vypolneniya SQL zaprosa - RESULTSET object
            // berem iz nego znacheniya kolonok  i prisvaivaem vremennomu obiektu person

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return person;
    }

    public void save(Person person) {
//        person.setId(++PERSON_COUNT);
//        personList.add(person);

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Person VALUES ( 1, ?, ?, ?);");

            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());

            preparedStatement.executeUpdate();


//            Statement statement = connection.createStatement();
//            String SQL = "INSERT INTO Person VALUES(" + 1
//                    + ",'" + person.getName()
//                    + "'," + person.getAge()
//                    + ",'" + person.getEmail()
//                    + "')";
//            statement.executeUpdate(SQL);//

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void edit(int id, Person updatedPerson) {
//        Person personForUpdate = getPersonById(id);
//
//        personForUpdate.setAge(updatedPerson.getAge());
//        personForUpdate.setName(updatedPerson.getName());
//        personForUpdate.setEmail(updatedPerson.getEmail());


        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");

            preparedStatement.setString(1, updatedPerson.getName());
            preparedStatement.setInt(2, updatedPerson.getAge());
            preparedStatement.setString(3, updatedPerson.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void delete(int id) {
//        personList.removeIf(person -> person.getId() == id);
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Person WHERE id=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
