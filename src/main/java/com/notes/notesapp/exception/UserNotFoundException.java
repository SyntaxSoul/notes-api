package com.notes.notesapp.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id){
        super("user with id:"+id+" not found.");
    }
}
