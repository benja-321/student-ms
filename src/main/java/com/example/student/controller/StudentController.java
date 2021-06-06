package com.example.student.controller;

import com.example.student.entity.StudentEntity;
import com.example.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Tags(value = {
        @Tag(name = "student")
})
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Value("${kafka.topic.communicate}")
    private String communicateTopic;

    @Operation(summary = "Return all students", description = "Service that return all students saved")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all students",
                        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StudentEntity.class))})
    })
    @GetMapping("/students")
    public ResponseEntity<Flux<StudentEntity>> getAllStudents(){
        return new ResponseEntity<>(studentService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    //@ApiOperation(value = "Return a student by id", notes = "Service that return a student based on their id")
    public ResponseEntity<Mono<StudentEntity>> getStudentById(@PathVariable("id") Long id){
        return new ResponseEntity<>(studentService.getById(id),HttpStatus.OK);
    }

    @PostMapping("/student")
    //@ApiOperation(value = "Save a student", notes = "Service that save a student and then return it")
    public ResponseEntity<Mono<StudentEntity>> insertStudent(@RequestBody StudentEntity studentRequest){
        return new ResponseEntity<>(studentService.save(studentRequest), HttpStatus.CREATED);
    }

    @PutMapping("/student/{id}")
    //@ApiOperation(value = "Update a student", notes = "Service that update a student and then return it")
    public ResponseEntity<Mono<StudentEntity>> updateStudent(@PathVariable("id") Long id, @RequestBody StudentEntity studentUpdate){
        return new ResponseEntity<>(studentService.update(id,studentUpdate),HttpStatus.CREATED);
    }

    @DeleteMapping("/student/{id}")
    //@ApiOperation(value = "Delete a student", notes = "Service that delete a student")
    public ResponseEntity<Mono<Void>> deleteStudent(@PathVariable("id") Long id){
        return new ResponseEntity<>(studentService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/student/document/{document-number}")
    public ResponseEntity<Mono<StudentEntity>> getStudentByDocumentNumber(@PathVariable("document-number") String documentNumber){
        Mono<StudentEntity> mono = studentService.getByDocumentNumber(documentNumber);
        mono.doOnNext(student -> studentService.sendMessage(student.getDocumentNumber(), communicateTopic)).subscribe();
        return new ResponseEntity<>(mono, HttpStatus.OK);
    }

    @Bean
    public NewTopic topicCommunicate(){
        return new NewTopic(communicateTopic,3,(short)1);
    }
}
