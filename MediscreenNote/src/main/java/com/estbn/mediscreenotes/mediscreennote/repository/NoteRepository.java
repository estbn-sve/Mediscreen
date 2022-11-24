package com.estbn.mediscreenotes.mediscreennote.repository;

import com.estbn.mediscreenotes.mediscreennote.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
}
