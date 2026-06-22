package com.notes.notesapp.service;

import com.notes.notesapp.dto.NoteRequest;
import com.notes.notesapp.dto.NoteResponse;
import com.notes.notesapp.exception.NoteNotFoundException;
import com.notes.notesapp.exception.UserNotFoundException;
import com.notes.notesapp.model.Note;
import com.notes.notesapp.model.User;
import com.notes.notesapp.repository.NoteRepository;
import com.notes.notesapp.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository=userRepository;
    }

    public Page<NoteResponse> getNotes(Pageable pageable) {
        Page<Note> pages = noteRepository.findAll(pageable);
        return pages.map(NoteResponse::new);
    }

    public Page<NoteResponse> getNoteByTitle(String title,Pageable pageable) {

        return noteRepository.findByTitleContainsIgnoreCase(title,pageable).map(NoteResponse::new);
        // We can do object type convertion Note-> NoteResponse using .map() for Page object

//        List<NoteResponse> notes = new ArrayList<>();
//        for (Note note : notesRepository.findByTitleContainsIgnoreCase(title,pageable)) {
//            notes.add(new NoteResponse(note));
//        }
//        return notes;
    }

    public Note getNote(int id) {
        Optional<Note> fetchedNote = noteRepository.findById(id);
        if (fetchedNote.isEmpty()) {
            throw new NoteNotFoundException(id);
        }
        return fetchedNote.get();
    }

    public NoteResponse addNote(NoteRequest request) {
        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        User user= userRepository.findById(request.getUserId()).orElseThrow(()-> new UserNotFoundException(request.getUserId()));
        note.setUser(user);
        return new NoteResponse(noteRepository.save(note));
    }

    public NoteResponse updateNote(int id, NoteRequest request) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        if (noteOptional.isEmpty()) {
            throw new NoteNotFoundException(id);
        }
        Note note = noteOptional.get();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        return new NoteResponse(noteRepository.save(note));
    }

    public void deleteNote(int id) {
        if (!noteRepository.existsById(id)) {
            throw new NoteNotFoundException(id);
        }
        noteRepository.deleteById(id);
    }
}
