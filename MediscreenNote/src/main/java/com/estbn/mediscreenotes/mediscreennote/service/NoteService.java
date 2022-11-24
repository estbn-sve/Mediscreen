package com.estbn.mediscreenotes.mediscreennote.service;

import com.estbn.mediscreenotes.mediscreennote.entity.Note;
import com.estbn.mediscreenotes.mediscreennote.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NoteService {

    @Autowired
    private NoteRepository repository;

    public List<Note> getAllNotes(){
        return repository.findAll();
    }

    public Note getNote(Integer id){
        return repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with getNote "+ id));
    }

    public Note addNote(Note Note){
        if(!repository.existsById(Note.getId())){
            return repository.save(Note);

        } else {
            return repository.findById(Note.getId()).orElseThrow(() ->
                    new NoSuchElementException("Error with addNote " + Note.getId()));
        }
    }

    public Note putNote(Note currentNote, final Integer id){
        if (repository.existsById(id)){
            repository.save(currentNote);
            return  currentNote;
        }else {
            return repository.findById(id).orElseThrow(() ->
                    new NoSuchElementException("Error with addNote " + currentNote.getId()));
        }
    }

    public Note deleteNote (final Integer id){
        Note p = repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with deleteNote "+id));
        Note copy = Note.builder()
                .id(p.getId())
                .idPatient(p.getIdPatient())
                .note(p.getNote())
                .build();
        repository.delete(p);
        return copy;
    }
}
