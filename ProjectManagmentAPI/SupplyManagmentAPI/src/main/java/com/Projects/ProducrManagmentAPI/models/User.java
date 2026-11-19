package com.Projects.ProducrManagmentAPI.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "users")
public class User {

    @Id 
    private String email;
    private String username;
    private int age;
    private String role;
    private String hashedPassword;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        if(age > 0) this.age = age;
        else age = 1;
    }
    public String getRole() {        
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

}
