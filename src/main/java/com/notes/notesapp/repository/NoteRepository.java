package com.notes.notesapp.repository;


import com.notes.notesapp.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    Page<Note> findByTitleContainsIgnoreCase(String title, Pageable pageable);

    Page<Note> findByUser_Id(int userId, Pageable pageable);

}
