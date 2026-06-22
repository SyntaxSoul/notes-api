package com.notes.notesapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name="user_id") //This is explicit. But jpa creates automatically also
    // JPA takes field name i.e user and primary key of user i.e id and add a '_' between them.
    private User user;

    public Note(){
    }

    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getContent(){
        return this.content;
    }

    public User getUser() {
        return this.user;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setContent(String content){
        this.content=content;
    }

    public void setUser(User user){
        this.user=user;
    }
}
