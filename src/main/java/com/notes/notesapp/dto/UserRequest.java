package com.notes.notesapp.dto;

import com.notes.notesapp.model.User;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String email;

    public UserRequest(){

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

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
