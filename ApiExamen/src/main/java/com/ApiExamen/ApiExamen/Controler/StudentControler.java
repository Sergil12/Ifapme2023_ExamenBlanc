package com.ApiExamen.ApiExamen.Controler;

import com.ApiExamen.ApiExamen.Entity.Payload.StudentPayload.StudentCreatePayload;
import com.ApiExamen.ApiExamen.Entity.Payload.StudentPayload.StudentUpdatePayload;
import com.ApiExamen.ApiExamen.Entity.ApiResponse;
import com.ApiExamen.ApiExamen.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableResourceServer
@RestController
@RequestMapping("/student")
public class StudentControler {
    @Autowired
    StudentService studentService;

    @PostMapping("create")
    ApiResponse create(@RequestBody StudentCreatePayload student) {


        return new ApiResponse(true,this.studentService.create(student),"");
    }
    @PutMapping("update")
    ApiResponse update(@RequestBody StudentUpdatePayload student) {
        return new ApiResponse(true, this.studentService.update(student),"");
    }

    @GetMapping("detail/{id}")
    ApiResponse detail(@PathVariable("id") UUID student_id) {
        return new ApiResponse(true, this.studentService.detail(student_id),"");
    }

    @DeleteMapping("delete/{id}")
    void delete(@PathVariable("id") UUID student__id) {
        UUID student_id = null;
        this.studentService.delete(null);

    }

    @GetMapping("list")
    ApiResponse list() {
        return new ApiResponse(true,this.studentService.list(),"");
    }
}
