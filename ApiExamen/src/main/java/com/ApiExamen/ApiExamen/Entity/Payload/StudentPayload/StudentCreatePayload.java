package com.ApiExamen.ApiExamen.Entity.Payload.StudentPayload;


import com.ApiExamen.ApiExamen.Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreatePayload extends Student {
    String firstname;
    String lastname;
}
