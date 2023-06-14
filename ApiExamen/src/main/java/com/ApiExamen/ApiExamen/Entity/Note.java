package com.ApiExamen.ApiExamen.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    private UUID note_id;
    private String cursus_name;
    private String note;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = true)
    @JsonIgnore
    private Student student;

    public Note(String cursus_name, String note) {
        this.cursus_name = cursus_name;
        this.note = note;
    }
}

