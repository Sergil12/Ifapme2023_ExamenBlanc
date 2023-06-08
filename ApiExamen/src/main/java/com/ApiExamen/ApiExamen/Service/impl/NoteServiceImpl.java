package com.ApiExamen.ApiExamen.Service.impl;
import com.ApiExamen.ApiExamen.Service.NoteService;
import com.ApiExamen.ApiExamen.Entity.Builder.NoteBuilder;
import com.ApiExamen.ApiExamen.Entity.Note;
import com.ApiExamen.ApiExamen.Entity.Payload.NotePayload.NoteCreatePayload;
import com.ApiExamen.ApiExamen.Entity.Payload.NotePayload.NoteUpdatePayload;
import com.ApiExamen.ApiExamen.Repository.NoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {

    private Note note;

    @Override
    public Note create(NoteCreatePayload note) {
        Note newNote= new NoteBuilder()
                .setCursus_name(note.getCursus_name())
                .setNote(note.getNote())
                .build();
        return this.noteRepository.save(newNote);
    }



    @Override
    public Note update(NoteUpdatePayload note) {
         Note detail = this.detail(note.getNote_id());
        if (detail != null) {
            detail.setCursus_name(note.getCursus_name());
            detail.setNote(note.getNote());
            return this.noteRepository.save(detail);
        }
        return detail;
    }

    @Override
    public Note detail(UUID note_id) {
        return this.noteRepository.findById(note_id).orElse(null);
    }

    @Override
    public void delete(UUID note_id) {
        Note detail = this.detail(note_id);
        if (detail != null) {
            this.noteRepository.delete(detail);
        }

    }

    @Override
    public List<Note> list() {
        return this.noteRepository.findAll();
    }
    @Autowired
    NoteRepository noteRepository;
}
