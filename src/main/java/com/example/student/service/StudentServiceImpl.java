package com.example.student.service;

import com.example.student.entity.StudentEntity;
import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Iterable<StudentEntity> getAll(){
        return studentRepository.findAll();
    }

    @Override
    public StudentEntity getById(Long id){
        return studentRepository.findById(id).get();
    }

    @Override
    public StudentEntity save(StudentEntity studentEntity){
        return studentRepository.save(studentEntity);
    }

    @Override
    public void delete(Long id){
        studentRepository.deleteById(id);
    }
}
