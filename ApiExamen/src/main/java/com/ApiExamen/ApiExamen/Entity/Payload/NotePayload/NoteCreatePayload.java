package com.ApiExamen.ApiExamen.Entity.Payload.NotePayload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteCreatePayload {
    String cursus_name;
    String note;
}
