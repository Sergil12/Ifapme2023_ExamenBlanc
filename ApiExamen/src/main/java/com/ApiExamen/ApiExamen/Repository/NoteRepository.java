package com.ApiExamen.ApiExamen.Repository;

import com.ApiExamen.ApiExamen.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {
}
