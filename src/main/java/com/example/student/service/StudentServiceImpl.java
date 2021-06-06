package com.example.student.service;

import com.example.student.entity.StudentEntity;
import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private KafkaTemplate producer;

    @Override
    public Flux<StudentEntity> getAll(){
        return studentRepository.findAll();
    }

    @Override
    public Mono<StudentEntity> getById(Long id){
        return studentRepository.findById(id);
    }

    @Override
    public Mono<StudentEntity> save(StudentEntity studentEntity){
        return studentRepository.save(studentEntity);
    }

    @Override
    public Mono<StudentEntity> update(Long id, StudentEntity studentUpdate){
        return studentRepository.findById(id).map(student -> {
            student.setAddress(studentUpdate.getAddress());
            student.setAge(studentUpdate.getAge());
            student.setBirthDate(studentUpdate.getBirthDate());
            student.setCivilStatus(studentUpdate.getCivilStatus());
            student.setStatus(studentUpdate.getStatus());
            student.setDocumentNumber(studentUpdate.getDocumentNumber());
            student.setGender(studentUpdate.getGender());
            student.setDocumentType(studentUpdate.getDocumentType());
            student.setUniversity(studentUpdate.getUniversity());
            return student;
        }).flatMap(studentUpdated->studentRepository.save(studentUpdated));
    }

    @Override
    public Mono<Void> delete(Long id){
        return studentRepository.deleteById(id);
    }

    @Override
    public Mono<StudentEntity> getByDocumentNumber(String documentNumber){
        return studentRepository.findByDocumentNumber(documentNumber)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found")));
    }

    @Override
    public void sendMessage(String message, String topic){
        producer.send(topic,message);
    }
}
