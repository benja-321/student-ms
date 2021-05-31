package com.example.student.controller;

import com.example.student.dto.RequestDto;
import com.example.student.dto.ResponseDto;
import com.example.student.mapper.StudentMapper;
import com.example.student.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentMapper studentMapper;

    @GetMapping("/students")
    @ApiOperation(value = "Return all students", notes = "Service that return all students saved")
    public List<ResponseDto> getAllStudents(){
        return studentMapper.mapIterableToListResponseDto(studentService.getAll());
    }

    @GetMapping("/student/{id}")
    @ApiOperation(value = "Return a student by id", notes = "Service that return a student based on their id")
    public ResponseDto getStudentById(@PathVariable("id") Long id){
        return studentMapper.mapEntityToResponseDto(studentService.getById(id));
    }

    @PostMapping("/student")
    @ApiOperation(value = "Save a student", notes = "Service that save a student and then return it")
    public ResponseDto insertStudent(@RequestBody RequestDto request){
        return studentMapper.mapEntityToResponseDto(studentService.save(studentMapper.mapRequestDtoToEntity(request)));
    }

    @PutMapping("/student/{id}")
    @ApiOperation(value = "Update a student", notes = "Service that update a student and then return it")
    public ResponseDto updateStudent(@RequestBody RequestDto request, @PathVariable("id") Long id){
        return studentMapper.mapEntityToResponseDto(studentService.save(studentMapper.mapUpdateEntity(request,studentService.getById(id))));
    }

    @DeleteMapping("/student/{id}")
    @ApiOperation(value = "Delete a student", notes = "Service that delete a student")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id){
        studentService.delete(id);
        return new ResponseEntity<>("Exito", HttpStatus.OK);
    }

}
