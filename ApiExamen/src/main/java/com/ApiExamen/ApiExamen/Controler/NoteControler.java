package com.ApiExamen.ApiExamen.Controler;

import com.ApiExamen.ApiExamen.Entity.ApiResponse;
import com.ApiExamen.ApiExamen.Entity.Payload.NotePayload.NoteCreatePayload;
import com.ApiExamen.ApiExamen.Entity.Payload.NotePayload.NoteUpdatePayload;
import com.ApiExamen.ApiExamen.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableResourceServer
@RestController
@RequestMapping("/note")
public class NoteControler {
    @Autowired
    NoteService noteService;

    @PostMapping("create")
    ApiResponse create(@RequestBody NoteCreatePayload note) {


        return new ApiResponse(true,this.noteService.create(note),"");
    }
    @PutMapping("update")
    ApiResponse update(@RequestBody NoteUpdatePayload note) {
        return new ApiResponse(true, this.noteService.update(note),"");
    }

    @GetMapping("detail/{id}")
    ApiResponse detail(@PathVariable("id") UUID note_id) {
        return new ApiResponse(true, this.noteService.detail(note_id),"");
    }

    @DeleteMapping("delete/{id}")
    void delete(@PathVariable("id") UUID note_id) {
        this.noteService.delete(note_id);

    }

    @GetMapping("list")
    ApiResponse list() {
        return new ApiResponse(true,this.noteService.list(),"");
    }
}
