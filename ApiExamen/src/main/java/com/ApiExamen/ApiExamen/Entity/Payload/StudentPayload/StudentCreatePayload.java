package com.ApiExamen.ApiExamen.Entity.Payload.StudentPayload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreatePayload {
    String firstname;
    String lastname;
}
