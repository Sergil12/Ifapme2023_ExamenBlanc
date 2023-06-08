package com.ApiExamen.ApiExamen.Entity.Builder;

import com.ApiExamen.ApiExamen.Entity.Note;

public class NoteBuilder implements Builder<Note>{
    String cursus_name;
    String note;


    public NoteBuilder setCursus_name(String cursus_name) {
        this.cursus_name = cursus_name;
        return this;
    }

    public NoteBuilder setNote(String note) {
        this.note = note;
        return this;
    }


    @Override
    public Note build() {
        return new Note(this.cursus_name,this.note);
    }

}
