package com.example.student.repository;

import com.example.student.entity.StudentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveCrudRepository<StudentEntity,Long> {

    public Mono<StudentEntity> findByDocumentNumber(String documentNumber);
}
