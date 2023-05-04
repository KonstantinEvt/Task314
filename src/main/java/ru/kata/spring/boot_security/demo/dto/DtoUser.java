package ru.kata.spring.boot_security.demo.dto;

import java.util.HashSet;
import java.util.Set;

public class DtoUser {

    private Long id;
    private String firstName="FirstName";

    private String lastName="LastName";

    private int age=50;

    private String email="email@gmail.com";

    private String password="Password";

    private String username="Username";
    private Set<String> roles = new HashSet<>();

    public DtoUser() {
    }

    public DtoUser(String password, String username, Set<String> roles) {
        this(1L,"firstName", "lastName", 30, "email", username, password, roles);
    }

    public DtoUser(Long id, String firstName, String lastName, int age, String email, String password, String username, Set<String> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.username = username;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }


}
