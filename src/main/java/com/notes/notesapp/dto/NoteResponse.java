package com.notes.notesapp.dto;

import com.notes.notesapp.model.Note;

public class NoteResponse {
    private int id;
    private String title;
    private String content;
    private int userId;

    public NoteResponse(Note note) {
        this.id = note.getId();
        this.title = note.getTitle();
        this.content = note.getContent();
        this.userId=note.getUser().getId();
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

    public int getUserId(){
        return this.userId;
    }

}
