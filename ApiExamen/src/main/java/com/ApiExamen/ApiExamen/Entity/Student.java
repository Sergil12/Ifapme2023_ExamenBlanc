package com.ApiExamen.ApiExamen.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Student {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "student_id", updatable = false, nullable = false)

    private UUID student_id;
    private String firstname;
    private String lastname;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Account> accounts;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Note> notes;

    public Student ( String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
