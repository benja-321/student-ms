package com.example.student.service;

import com.example.student.entity.StudentEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

    public Flux<StudentEntity> getAll();

    public Mono<StudentEntity> getById(Long id);

    public Mono<StudentEntity> save(StudentEntity studentEntity);

    public Mono<StudentEntity> update(Long id, StudentEntity studentEntity);

    public Mono<Void> delete(Long id);
}
