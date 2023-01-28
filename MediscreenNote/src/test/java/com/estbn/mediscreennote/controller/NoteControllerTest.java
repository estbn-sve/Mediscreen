package com.estbn.mediscreennote.controller;

import com.estbn.mediscreennote.entity.Note;
import com.estbn.mediscreennote.service.NoteService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(NoteController.class)
@ContextConfiguration
class NoteControllerTest {

    @Autowired
    public MockMvc mockMvc;
    @MockBean
    public NoteService service;

    final private String requestMapping = "/note/";

    //getAll
    @Test
    void getAllNotes() throws Exception {
        when(service.getAllNotes()).thenReturn(new ArrayList<>());
        mockMvc.perform(get(requestMapping))
                .andExpect(status().isOk());
    }

    //getNote
    @Test
    void getNote_shouldReturnOk() throws Exception {
        when(service.getNote(any())).thenReturn(new Note());
        mockMvc.perform(get(requestMapping+"1"))
                .andExpect(status().isOk());
    }
    @Test
    public void getNote_shouldReturnNotFound() throws Exception{
        when(service.getNote(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(get(requestMapping+"1"))
                .andExpect(status().isNotFound());
    }

    //addNote
    @Test
    void addNote_shouldReturnOk() throws Exception {
        when(service.addNote(any())).thenReturn(new Note());
        mockMvc.perform(post(requestMapping).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    void addNote_shouldReturnNotFound() throws Exception {
        when(service.addNote(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(post(requestMapping).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }

    //putNote
    @Test
    void putNote_shouldReturnOk() throws Exception {
        when(service.putNote(any())).thenReturn(new Note());
        mockMvc.perform(put(requestMapping).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());

    }
    @Test
    void putNote_shouldReturnNotFound() throws Exception {
        when(service.putNote(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(put(requestMapping).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }

    //delete
    @Test
    public void deleteBidList_shouldReturnOk() throws Exception{
        when(service.deleteNote(any())).thenReturn(new Note());
        mockMvc.perform(delete(requestMapping+"1"))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteBidList_shouldReturnNotFound() throws Exception{
        when(service.deleteNote(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(delete(requestMapping+"1"))
                .andExpect(status().isNotFound());
    }

    //getNoteByPatient
    @Test
    void getNoteByPatient_shouldReturnOk() throws Exception {
        when(service.getNotesByPatient(any())).thenReturn(new ArrayList<>());
        mockMvc.perform(get(requestMapping+"patient/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void getNoteByPatient_shouldReturnNotFound() throws Exception{
        when(service.getNotesByPatient(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(get(requestMapping+"patient/1"))
                .andExpect(status().isNotFound());
    }
    //getMiniNoteByPatient
    @Test
    void getMiniNoteByPatient_shouldReturnOk() throws Exception {
        when(service.getMiniNotesByPatient(any())).thenReturn(new ArrayList<>());
        mockMvc.perform(get(requestMapping+"patient/miniNotes/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void getMiniNoteByPatient_shouldReturnNotFound() throws Exception{
        when(service.getMiniNotesByPatient(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(get(requestMapping+"patient/miniNotes/1"))
                .andExpect(status().isNotFound());
    }
}