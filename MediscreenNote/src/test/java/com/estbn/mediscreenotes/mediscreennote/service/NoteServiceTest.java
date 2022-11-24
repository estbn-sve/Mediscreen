package com.estbn.mediscreenotes.mediscreennote.service;

import com.estbn.mediscreenotes.mediscreennote.entity.Note;
import com.estbn.mediscreenotes.mediscreennote.repository.NoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {
    @InjectMocks
    private NoteService service;
    @Mock
    private NoteRepository repository;

    @Test
    void getAllNotes() {
        List<Note> note = new ArrayList<>();
        when(repository.findAll()).thenReturn(note);
        Assertions.assertEquals(service.getAllNotes(),note);
    }


    @Test
    void getNote_shouldReturnOk() {
        Note note = new Note();
        when(repository.findById(any())).thenReturn(Optional.of(note));
        Assertions.assertEquals(service.getNote(1),note);
    }
    @Test
    void getNote_shouldThrowNoSuchElement() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () ->
                service.getNote(1));
    }

    @Test
    void addNote_shouldReturnOk() {
        Note note = new Note();
        note.setId(1);
        when(repository.existsById(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(note);
        Assertions.assertEquals(service.addNote(note),note);
    }
    @Test
    void addNote_shouldThrowNoSuchElement() {
        Note note = new Note();
        note.setId(1);
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () ->
                service.addNote(note));
    }

    @Test
    void putNote_shouldReturnOk() {
        Note note = new Note();
        when(repository.existsById(any())).thenReturn(true);
        Assertions.assertEquals(service.putNote(note,1),note);
    }
    @Test
    void putNote_shouldThrowNoSuchElement() {
        Note note = new Note();
        when(repository.existsById(any())).thenReturn(false);
        assertThrows(NoSuchElementException.class, () ->
                service.putNote(note,1));
    }

    @Test
    void deleteNote_shouldReturnOk() {
        Note note = new Note();
        note.setNote("Jonh");
        when(repository.findById(any())).thenReturn(Optional.of(note));
        doNothing().when(repository).delete(any());
        Note noteResult = service.deleteNote(1);
        Assertions.assertEquals(note.getNote(), noteResult.getNote());
    }
    @Test
    void deleteNote_shouldThrowNoSuchElement() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () ->
                service.deleteNote(1));
    }
}