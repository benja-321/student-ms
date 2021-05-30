package com.example.student.service;

import com.example.student.entity.StudentEntity;

public interface StudentService {

    public Iterable<StudentEntity> getAll();

    public StudentEntity getById(Long id);

    public StudentEntity save(StudentEntity studentEntity);

    public void delete(Long id);
}
