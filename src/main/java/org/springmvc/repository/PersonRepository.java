package org.springmvc.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springmvc.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonRepository {
    JdbcTemplate jdbcTemplate;

    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    private static final String URL = "jdbc:postgresql://localhost:5432/javatest";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "dsrnjh1";

//    @Value("${db.url}")
//    private  static String URL;
//    @Value("${db.user}")
//    private  static String USERNAME;
//    @Value("${db.pass}")
//    private static String PASSWORD;

//    private static Connection connection;
//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public List<Person> getPeople() throws SQLException {
//        List<Person> people = new ArrayList<>();
//        Statement statement = connection.createStatement();
//        String SQL = "SELECT * FROM person";
//        ResultSet resultSet = statement.executeQuery(SQL);
//        while (resultSet.next()) {
//            Person person = new Person();
//            person.setId(resultSet.getInt("id"));
//            person.setName(resultSet.getString("name"));
//            person.setEmail(resultSet.getString("email"));
//            person.setAge(resultSet.getInt("age"));
//            people.add(person);
//        }
//        return people;

            return jdbcTemplate.query("SELECT * FROM person",
                    new BeanPropertyRowMapper<>(Person.class));
}

    public Person getPerson(int id) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE id = ?");
//        preparedStatement.setInt(1, id);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        resultSet.next();
//        Person person = new Person();
//        person.setId(resultSet.getInt("id"));
//        person.setName(resultSet.getString("name"));
//        person.setEmail(resultSet.getString("email"));
//        person.setAge(resultSet.getInt("age"));
//
//        return person;

        return jdbcTemplate.query("SELECT * FROM person WHERE id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void deletePerson(int id) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id = ?");
//        preparedStatement.setInt(1, id);
//        preparedStatement.executeUpdate();
        jdbcTemplate.update("DELETE  FROM  person WHERE id = ?", id);
    }

    public void createPerson(Person person) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO person VALUES(1, ?, ?, ?)");
//        preparedStatement.setString(1, person.getName());
//        preparedStatement.setInt(2, person.getAge());
//        preparedStatement.setString(3, person.getEmail());
//        preparedStatement.executeUpdate();
        jdbcTemplate.update("INSERT INTO  person VALUES(1,?,?,?)",
                person.getName(), person.getAge(), person.getEmail());

    }

    public void updatePerson(Person person, int id) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE person SET name= ?, email = ?, age = ? WHERE id = ?");
//        preparedStatement.setString(1, person.getName());
//        preparedStatement.setString(2, person.getEmail());
//        preparedStatement.setInt(3, person.getAge());
//        preparedStatement.setInt(4, id);
//        preparedStatement.executeUpdate();

        jdbcTemplate.update("UPDATE  person SET name = ?, age = ?, email = ? WHERE id = ?",
                person.getName(), person.getAge(), person.getEmail(), id);


//        Person beUpdatedPerson = getPerson(id);
//        beUpdatedPerson.setName(person.getName());
//        beUpdatedPerson.setEmail(person.getEmail());
//        beUpdatedPerson.setAge(person.getAge());
    }


}
