package com.example.student.repository;

import com.example.student.entity.StudentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

public interface StudentRepository extends ReactiveCrudRepository<StudentEntity,Long> {

}
