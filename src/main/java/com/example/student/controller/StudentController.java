package com.example.student.controller;

import com.example.student.entity.StudentEntity;
import com.example.student.service.StudentService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
//@Api(tags = "Student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    //@ApiOperation(value = "Return all students", notes = "Service that return all students saved")
    public Flux<StudentEntity> getAllStudents(){
        return studentService.getAll();
    }

    @GetMapping("/student/{id}")
    //@ApiOperation(value = "Return a student by id", notes = "Service that return a student based on their id")
    public Mono<StudentEntity> getStudentById(@PathVariable("id") Long id){
        return studentService.getById(id);
    }

    @PostMapping("/student")
    //@ApiOperation(value = "Save a student", notes = "Service that save a student and then return it")
    public Mono<StudentEntity> insertStudent(@RequestBody StudentEntity studentRequest){
        return studentService.save(studentRequest);
    }

    @PutMapping("/student/{id}")
    //@ApiOperation(value = "Update a student", notes = "Service that update a student and then return it")
    public Mono<StudentEntity> updateStudent(@PathVariable("id") Long id, @RequestBody StudentEntity studentUpdate){
        return studentService.update(id,studentUpdate);
    }

    @DeleteMapping("/student/{id}")
    //@ApiOperation(value = "Delete a student", notes = "Service that delete a student")
    public Mono<Void> deleteStudent(@PathVariable("id") Long id){
        return studentService.delete(id);
    }

}
