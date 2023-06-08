package com.ApiExamen.ApiExamen.Service;

import com.ApiExamen.ApiExamen.Entity.Note;
import com.ApiExamen.ApiExamen.Entity.Payload.NotePayload.NoteCreatePayload;
import com.ApiExamen.ApiExamen.Entity.Payload.NotePayload.NoteUpdatePayload;

import java.util.List;
import java.util.UUID;

public interface NoteService {
    Note create(NoteCreatePayload note);

    Note update(NoteUpdatePayload note);

    Note  detail(UUID note_id);
    void delete(UUID note_id);
    List<Note> list();
}
