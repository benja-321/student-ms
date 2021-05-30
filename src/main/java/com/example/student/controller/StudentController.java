package com.example.student.controller;

import com.example.student.dto.RequestDto;
import com.example.student.dto.ResponseDto;
import com.example.student.mapper.StudentMapper;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentMapper studentMapper;

    @GetMapping("/students")
    public List<ResponseDto> getAllStudents(){
        return studentMapper.mapIterableToListResponseDto(studentService.getAll());
    }

    @GetMapping("/student/{id}")
    public ResponseDto getStudentById(@PathVariable("id") Long id){
        return studentMapper.mapEntityToResponseDto(studentService.getById(id));
    }

    @PostMapping("/student")
    public ResponseDto insertStudent(@RequestBody RequestDto request){
        return studentMapper.mapEntityToResponseDto(studentService.save(studentMapper.mapRequestDtoToEntity(request)));
    }

    @PutMapping("/student/{id}")
    public ResponseDto updateStudent(@RequestBody RequestDto request, @PathVariable("id") Long id){
        return studentMapper.mapEntityToResponseDto(studentService.save(studentMapper.mapUpdateEntity(request,studentService.getById(id))));
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id){
        studentService.delete(id);
        return new ResponseEntity<>("Exito", HttpStatus.OK);
    }

}
