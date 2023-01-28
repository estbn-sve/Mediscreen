package com.estbn.mediscreennote.service;

import com.estbn.mediscreennote.entity.Note;
import com.estbn.mediscreennote.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NoteService {

    @Autowired()
    private NoteRepository repository;

    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    public Note getNote(String id) {
        return repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Error with getNote " + id));
    }

    public List<Note> getNotesByPatient(String id) {
        return repository.findByIdPatient(id).orElseThrow(() ->
                new NoSuchElementException("Error with getNotesByPatient " + id));
    }

    public List<String> getMiniNotesByPatient(String id) {
        List<Note> notes = repository.findByIdPatient(id).orElseThrow(() ->
                new NoSuchElementException("Error with getNotesByPatient " + id));
        List<String> miniNote = new ArrayList<>();
        notes.stream().forEach(n -> miniNote.add(n.getNote()));
        return miniNote;
    }


    public Note addNote(Note Note) {
        if (!repository.existsById(Note.getId())) {
            return repository.save(Note);

        } else {
            return repository.findById(Note.getId()).orElseThrow(() ->
                    new NoSuchElementException("Error with addNote " + Note.getId()));
        }
    }

    public Note putNote(Note currentNote) {
        if (!repository.existsById(currentNote.getId())) {
            return repository.save(currentNote);
        } else {
            return repository.findById(currentNote.getId()).orElseThrow(() ->
                    new NoSuchElementException("Error with addNote " + currentNote.getId()));
        }
    }

    public Note deleteNote(final String id) {
        Note p = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Error with deleteNote " + id));
        Note copy = Note.builder()
                .id(p.getId())
                .idPatient(p.getIdPatient())
                .note(p.getNote())
                .build();
        repository.delete(p);
        return copy;
    }
}
