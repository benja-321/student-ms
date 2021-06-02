package com.example.student.service;

import com.example.student.entity.StudentEntity;
import com.example.student.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class TestStudentServiceImpl {

    @InjectMocks
    StudentServiceImpl studentService;

    @Mock
    StudentRepository studentRepository;

    @Test
    public void testGetAllSuccessfully(){
        StudentEntity student = new StudentEntity();
        student.setId(1L);
        student.setAddress("qwe");
        student.setAge(21);
        student.setBirthDate(LocalDate.now());
        student.setCivilStatus("qwe");
        student.setStatus("asd");
        student.setDocumentNumber("1234");
        student.setGender('M');
        student.setDocumentType("asd");
        student.setUniversity("asd");
        Flux<StudentEntity> fluxStudent = Flux.just(student);

        Mockito.when(studentRepository.findAll()).thenReturn(fluxStudent);

        Assertions.assertThat(studentService.getAll()).isEqualTo(fluxStudent);
    }

    @Test
    public void testGetByIdSuccessfully(){
        StudentEntity student = new StudentEntity();
        student.setId(1L);
        student.setAddress("qwe");
        student.setAge(21);
        student.setBirthDate(LocalDate.now());
        student.setCivilStatus("qwe");
        student.setStatus("asd");
        student.setDocumentNumber("1234");
        student.setGender('M');
        student.setDocumentType("asd");
        student.setUniversity("asd");
        Mono<StudentEntity> monoStudent = Mono.just(student);

        Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(monoStudent);

        Assertions.assertThat(studentService.getById(1L)).isEqualTo(monoStudent);
    }

    @Test
    public void testSaveSuccessfully(){
        StudentEntity student = new StudentEntity();
        student.setId(1L);
        student.setAddress("qwe");
        student.setAge(21);
        student.setBirthDate(LocalDate.now());
        student.setCivilStatus("qwe");
        student.setStatus("asd");
        student.setDocumentNumber("1234");
        student.setGender('M');
        student.setDocumentType("asd");
        student.setUniversity("asd");

        Mono<StudentEntity> monoStudent = Mono.just(student);

        Mockito.when(studentRepository.save(Mockito.refEq(student))).thenReturn(monoStudent);

        Assertions.assertThat(studentService.save(student)).isEqualTo(monoStudent);
    }

    @Test
    public void testUpdateSuccessfully(){
        StudentEntity studentUpdates = new StudentEntity();
        studentUpdates.setAddress("qwe");
        studentUpdates.setAge(21);
        studentUpdates.setBirthDate(LocalDate.now());
        studentUpdates.setCivilStatus("qwe");
        studentUpdates.setStatus("asd");
        studentUpdates.setDocumentNumber("1234");
        studentUpdates.setGender('M');
        studentUpdates.setDocumentType("asd");
        studentUpdates.setUniversity("asd");

        StudentEntity student = new StudentEntity();
        student.setId(1L);
        student.setAddress("asd");
        student.setAge(14);
        student.setBirthDate(LocalDate.now());
        student.setCivilStatus("asd");
        student.setStatus("qwe");
        student.setDocumentNumber("9876");
        student.setGender('F');
        student.setDocumentType("qwe");
        student.setUniversity("qwe");

        StudentEntity studentUpdated = new StudentEntity();
        studentUpdated.setId(1L);
        studentUpdated.setAddress("qwe");
        studentUpdated.setAge(21);
        studentUpdated.setBirthDate(LocalDate.now());
        studentUpdated.setCivilStatus("qwe");
        studentUpdated.setStatus("asd");
        studentUpdated.setDocumentNumber("1234");
        studentUpdated.setGender('M');
        studentUpdated.setDocumentType("asd");
        studentUpdated.setDocumentType("asd");
        studentUpdated.setUniversity("asd");

        Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Mono.just(student));
        Mockito.when(studentRepository.save(Mockito.refEq(studentUpdates))).thenReturn(Mono.just(studentUpdated));

        studentService.update(1L,studentUpdates).doOnNext(studentEntity->Assertions.assertThat(studentEntity).isEqualTo(studentUpdated));
    }

    @Test
    public void testDeleteSuccessfully(){
        studentService.delete(1L);
        Mockito.verify(studentRepository,Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}
