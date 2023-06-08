package com.ApiExamen.ApiExamen.Entity.Payload.NotePayload;

import java.util.UUID;

public class NoteUpdatePayload {
    UUID note_id;
    String cursus_name;
    String note;

    public UUID getNote_id() {
        return note_id;
    }

    public void setNote_id(UUID note_id) {
        this.note_id = note_id;
    }

    public String getCursus_name() {
        return cursus_name;
    }

    public void setCursus_name(String cursus_name) {
        this.cursus_name = cursus_name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
