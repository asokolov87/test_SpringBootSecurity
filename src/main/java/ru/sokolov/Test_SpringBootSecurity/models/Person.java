package ru.sokolov.Test_SpringBootSecurity.models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity  //объявление сущности
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "name_of_sequence_generator") как генерируется новый id (SEQUENCE - если генерировать по определенному SEQUENCE)
    //@SequenceGenerator(name = "name_of_sequence_generator",sequenceName = "name_of_sequence_bd",allocationSize = 1)  //если генерация sequence указываем её название из бд
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "age")
    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    @Column(name = "email")
    @Email
    private String email;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 5, max = 100, message = "длина должна быть от 5 до 100 символов")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {

    }
    public Person(String name, int age, String email, String username) {
        this.name = name;
        this.username = username;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}