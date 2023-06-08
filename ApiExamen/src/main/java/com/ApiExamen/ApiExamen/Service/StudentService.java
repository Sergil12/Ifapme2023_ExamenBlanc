package com.ApiExamen.ApiExamen.Service;
import com.ApiExamen.ApiExamen.Entity.Payload.StudentPayload.StudentCreatePayload;
import com.ApiExamen.ApiExamen.Entity.Payload.StudentPayload.StudentUpdatePayload;
import com.ApiExamen.ApiExamen.Entity.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    Student create(StudentCreatePayload student);
    Student  update(StudentUpdatePayload student);
    Student  detail(UUID student_id);
    void delete(UUID student_id);
    List<Student> list();
}
