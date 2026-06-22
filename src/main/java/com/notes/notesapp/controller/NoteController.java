package com.notes.notesapp.controller;

import com.notes.notesapp.dto.NoteRequest;
import com.notes.notesapp.dto.NoteResponse;
import com.notes.notesapp.model.Note;
import com.notes.notesapp.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/notes")
    public ResponseEntity<NoteResponse> createNote(@RequestBody @Valid NoteRequest request) {
        NoteResponse savedNote = noteService.addNote(request);
        URI uri = URI.create("/notes/" + savedNote.getId());
        return ResponseEntity.created(uri).body(savedNote);
    }

    @GetMapping("/notes")
    public Page<NoteResponse> getNotes(@RequestParam(required = false) String title,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "2") int size,
                                       Sort sort){
        Pageable pageable = PageRequest.of(page, size, sort);
        if(title == null|| title.isBlank()){
            return noteService.getNotes(pageable);
        }
        return noteService.getNoteByTitle(title,pageable);
        //return noteService.getNotes(page,size,sort); this without pageable means we are creating pageable in service layer
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNote(@PathVariable int id) {
        return ResponseEntity.ok(noteService.getNote(id));
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<NoteResponse> updateNote(@PathVariable int id, @RequestBody @Valid NoteRequest request) {
        NoteResponse updatedNote = noteService.updateNote(id, request);
        if (updatedNote != null) {
            return ResponseEntity.ok(updatedNote);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable int id) {
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}