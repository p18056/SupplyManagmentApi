package com.Projects.ProducrManagmentAPI.DtoObjects;

import com.Projects.ProducrManagmentAPI.models.User;

public class UserDto {

    public UserDto(User user)
    {
        email = user.getEmail();
        username = user.getUsername();
        age = user.getAge();
        role = user.getRole();
    }

    private String email;
    private String username;
    private int age;
    private String role;


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
        this.age = age;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
}
