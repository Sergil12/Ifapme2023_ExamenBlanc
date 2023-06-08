package com.ApiExamen.ApiExamen.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Note {

    @Id
@GeneratedValue(generator = "UUID")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
@Column(name = "note_id", updatable = false, nullable = false)

    UUID note_id;
    String cursus_name;
    String note;

    public Note(String cursus_name, String note) {
        this.cursus_name = cursus_name;
        this.note = note;
    }
}

