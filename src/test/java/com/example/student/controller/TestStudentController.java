package com.example.student.controller;

import com.example.student.entity.StudentEntity;
import com.example.student.service.StudentService;
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
public class TestStudentController {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @Test
    public void testGetAllStudentsSuccessfully(){
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

        Mockito.when(studentService.getAll()).thenReturn(fluxStudent);

        Assertions.assertThat(studentController.getAllStudents()).isEqualTo(fluxStudent);
    }

    @Test
    public void testGetStudentByIdSuccessfully(){
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

        Mockito.when(studentService.getById(Mockito.anyLong())).thenReturn(monoStudent);

        Assertions.assertThat(studentController.getStudentById(1L)).isEqualTo(monoStudent);
    }

    @Test
    public void testInsertStudentSuccessfully(){
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

        Mockito.when(studentService.save(Mockito.refEq(student))).thenReturn(monoStudent);

        Assertions.assertThat(studentController.insertStudent(student)).isEqualTo(monoStudent);
    }

    @Test
    public void testUpdateStudentSuccessfully(){
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
        studentUpdated.setUniversity("asd");

        Mono<StudentEntity> monoStudent = Mono.just(studentUpdated);

        Mockito.when(studentService.update(Mockito.anyLong(),Mockito.refEq(studentUpdates))).thenReturn(monoStudent);

        Assertions.assertThat(studentController.updateStudent(1L,studentUpdates)).isEqualTo(monoStudent);

    }

    @Test
    public void testDeleteStudentSuccessfully(){
        studentController.deleteStudent(1L);
        Mockito.verify(studentService,Mockito.times(1)).delete(Mockito.anyLong());
    }
}
