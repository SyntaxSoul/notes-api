package com.notes.notesapp.service;

import com.notes.notesapp.dto.NoteRequest;
import com.notes.notesapp.dto.NoteResponse;
import com.notes.notesapp.dto.UserRequest;
import com.notes.notesapp.dto.UserResponse;
import com.notes.notesapp.exception.UserNotFoundException;
import com.notes.notesapp.model.User;
import com.notes.notesapp.repository.NoteRepository;
import com.notes.notesapp.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    public UserService(UserRepository userRepository, NoteRepository noteRepository){
        this.userRepository=userRepository;
        this.noteRepository=noteRepository;
    }

    public UserResponse getUser(int id){
        User user=userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        return new UserResponse(user);
    }

    public Page<UserResponse> getUsers(Pageable pageable){
        return userRepository.findAll(pageable).map(UserResponse::new);
    }

    public UserResponse addUser(UserRequest request){
        User user=new User();
        user.setName(request.getName());
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        return new UserResponse(userRepository.save(user));
    }

    public Page<NoteResponse> getUserNotes(int id,Pageable pageable){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        return noteRepository.findByUser_Id(id,pageable).map(NoteResponse::new);
    }

    public UserResponse updateUser(int id, UserRequest request){
        User user=userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        user.setName(request.getName());
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        return new UserResponse(userRepository.save(user));
    }

    public void deleteUser(int id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}