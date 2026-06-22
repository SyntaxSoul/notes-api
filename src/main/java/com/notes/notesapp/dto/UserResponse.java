package com.notes.notesapp.dto;

import com.notes.notesapp.model.User;

public class UserResponse {
    private int id;
    private String name;
    private String address;
    private String email;

    public UserResponse(User user){
        this.id=user.getId();
        this.name=user.getName();
        this.address=user.getAddress();
        this.email=user.getEmail();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
