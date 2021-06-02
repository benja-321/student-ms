package com.example.student.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class StudentEntity {

    @Id
    private Long id;
    private String documentNumber;
    private String documentType;
    private Integer age;
    private String university;
    private String civilStatus;
    private String status;
    private LocalDate birthDate;
    private String address;
    private Character gender;
}
