package com.example.student.repository;

import com.example.student.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentEntity,Long> {

}
