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

    @Operation(summary = "Return a student by id", description = "Service that return a student based on their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found a student with their id",
                        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StudentEntity.class))})
    })
    @GetMapping("/student/{id}")
    public ResponseEntity<Mono<StudentEntity>> getStudentById(@PathVariable("id") Long id){
        return new ResponseEntity<>(studentService.getById(id),HttpStatus.OK);
    }

    @Operation(summary = "Save a student", description = "Service that save a student and then return it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Insert a student",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StudentEntity.class))})
    })
    @PostMapping("/student")
    public ResponseEntity<Mono<StudentEntity>> insertStudent(@RequestBody StudentEntity studentRequest){
        return new ResponseEntity<>(studentService.save(studentRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a student", description = "Service that update a student and then return it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update a student",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StudentEntity.class))})
    })
    @PutMapping("/student/{id}")
    public ResponseEntity<Mono<StudentEntity>> updateStudent(@PathVariable("id") Long id, @RequestBody StudentEntity studentUpdate){
        return new ResponseEntity<>(studentService.update(id,studentUpdate),HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a student", description = "Service that delete a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete a student",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StudentEntity.class))})
    })
    @DeleteMapping("/student/{id}")
    public ResponseEntity<Mono<Void>> deleteStudent(@PathVariable("id") Long id){
        return new ResponseEntity<>(studentService.delete(id), HttpStatus.OK);
    }

    @Operation(summary = "Return a student by document number", description = "Service that return a student based on their document number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found a student with their document number",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StudentEntity.class))})
    })
    @GetMapping("/student/document/{document-number}")
    public ResponseEntity<Mono<StudentEntity>> getStudentByDocumentNumber(@PathVariable("document-number") String documentNumber){
        Mono<StudentEntity> mono = studentService.getByDocumentNumber(documentNumber);
        return new ResponseEntity<>(mono, HttpStatus.OK);
    }

    @Bean
    public NewTopic topicCommunicate(){
        return new NewTopic(communicateTopic,3,(short)1);
    }
}
