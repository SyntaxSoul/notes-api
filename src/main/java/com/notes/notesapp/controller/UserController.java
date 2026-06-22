package com.notes.notesapp.controller;

import com.notes.notesapp.dto.NoteRequest;
import com.notes.notesapp.dto.NoteResponse;
import com.notes.notesapp.dto.UserRequest;
import com.notes.notesapp.dto.UserResponse;
import com.notes.notesapp.model.Note;
import com.notes.notesapp.model.User;
import com.notes.notesapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
//@RequestMapping("/users") // This prevents from adding ("/users") for each mapping Ex: @PostMapping is enough.
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request){
        UserResponse savedUser=userService.addUser(request);
        URI uri= URI.create("/users/"+savedUser.getId());
        return ResponseEntity.created(uri).body(savedUser);
    }
    // For PostMapping it is good to use ResponseEntity as it gives more detail

    @GetMapping("/users")
    public Page<UserResponse> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }
    // It's fine to return Page<> without ResponseEntity as it definitely return something or else exception
    // To know more about pageable check getNotes in NotesController

    @GetMapping("/users/{id}")
    public UserResponse getUser(@PathVariable int id){
        return userService.getUser(id);
    }

    @GetMapping("/users/{id}/notes")
    public Page<NoteResponse> getAllNotes(@PathVariable int id, Pageable pageable){
        return userService.getUserNotes(id,pageable);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable int id, @RequestBody @Valid UserRequest request){
        return ResponseEntity.ok(userService.updateUser(id,request));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}










