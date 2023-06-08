package com.ApiExamen.ApiExamen.Service.impl;
import com.ApiExamen.ApiExamen.Entity.Builder.StudentBuilder;
import com.ApiExamen.ApiExamen.Entity.Payload.StudentPayload.StudentCreatePayload;
import com.ApiExamen.ApiExamen.Entity.Payload.StudentPayload.StudentUpdatePayload;
import com.ApiExamen.ApiExamen.Entity.Student;
import com.ApiExamen.ApiExamen.Service.StudentService;
import com.ApiExamen.ApiExamen.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl  implements StudentService {
    @Override
    public Student create(StudentCreatePayload student) {
        Student newStudent= new StudentBuilder()
                .setFirstname(student.getFirstname())
                .setLastname(student.getLastname())
                .build();
        return this.studentRepository.save(newStudent);
    }

    @Override
    public Student update(StudentUpdatePayload student) {
        Student detail = this.detail(student.getStudent_id());
        if (detail != null) {
            detail.setFirstname(student.setFirstname());
            detail.setLastname(student.setLastname());
            return this.studentRepository.save(detail);
        }
        return detail;
    }

    @Override
    public Student detail(UUID student_id) {
        return this.studentRepository.findById(student_id).orElse(null);
    }

    @Override
    public void delete(UUID student_id) {
        Student detail = this.detail(student_id);
        if (detail != null) {
            this.studentRepository.delete(detail);
        }

    }

    @Override
    public List<Student> list() {
        return this.studentRepository.findAll();
    }
    @Autowired
    StudentRepository studentRepository;
}
