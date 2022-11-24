package com.estbn.mediscreenotes.mediscreennote.controller;

import com.estbn.mediscreenotes.mediscreennote.entity.Note;
import com.estbn.mediscreenotes.mediscreennote.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/note")
@CrossOrigin("http://localhost:4200")
public class NoteController {

    final String requestMapping ="/note";

    @Autowired
    private NoteService service;

    @GetMapping("")
    public List<Note> getAllNotes(){
        log.info("GET : "+requestMapping);
        return service.getAllNotes();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNote(@PathVariable("id") final Integer id){
        log.info("GET : "+requestMapping+"/"+id);
        try {
            return ResponseEntity.ok(service.getNote(id));
        } catch (NoSuchElementException e){
            log.error("GET "+requestMapping+"/"+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/")
    public ResponseEntity<Note> addNote(@RequestBody Note Note){
        log.info("POST : "+requestMapping+"/"+Note.getId());
        try {
            return ResponseEntity.ok(service.addNote(Note));
        } catch (NoSuchElementException e){
            log.error("POST "+requestMapping+"/"+Note.getId()+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Note> putNote(@PathVariable("id") final Integer id, @RequestBody Note Note){
        log.info("PUT : "+requestMapping+"/"+id);
        try {
            return ResponseEntity.ok(service.putNote(Note, id));
        } catch (NoSuchElementException e){
            log.error("PUT "+requestMapping+"/"+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable("id") final Integer id){
        log.info("DELETE "+requestMapping+"/"+id);
        try {
            return ResponseEntity.ok(service.deleteNote(id));
        }catch (NoSuchElementException e){
            log.error("DELETE "+requestMapping+"/"+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
