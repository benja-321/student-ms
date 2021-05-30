package com.example.student.mapper;

import com.example.student.dto.RequestDto;
import com.example.student.dto.ResponseDto;
import com.example.student.entity.StudentEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "numeroDocuemnto", target = "documentNumber")
    @Mapping(source = "tipoDocumento", target = "documentType")
    @Mapping(source = "edad", target = "age")
    @Mapping(source = "universidad", target = "university")
    @Mapping(source = "estadoCivil", target = "civilStatus")
    @Mapping(source = "estado", target = "status")
    @Mapping(source = "fechanNacimiento", target = "birthDate")
    @Mapping(source = "direccion", target = "address")
    @Mapping(source = "genero", target = "gender")
    StudentEntity mapRequestDtoToEntity(RequestDto requestDto);

    @Mapping(source = "documentNumber", target = "numeroDocuemnto")
    @Mapping(source = "documentType", target = "tipoDocumento")
    @Mapping(source = "age", target = "edad")
    @Mapping(source = "university", target = "universidad")
    @Mapping(source = "civilStatus", target = "estadoCivil")
    @Mapping(source = "status", target = "estado")
    @Mapping(source = "birthDate", target = "fechanNacimiento")
    @Mapping(source = "address", target = "direccion")
    @Mapping(source = "gender", target = "genero")
    ResponseDto mapEntityToResponseDto(StudentEntity studentEntity);

    List<ResponseDto> mapIterableToListResponseDto(Iterable<StudentEntity> studentEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "numeroDocuemnto", target = "documentNumber")
    @Mapping(source = "tipoDocumento", target = "documentType")
    @Mapping(source = "edad", target = "age")
    @Mapping(source = "universidad", target = "university")
    @Mapping(source = "estadoCivil", target = "civilStatus")
    @Mapping(source = "estado", target = "status")
    @Mapping(source = "fechanNacimiento", target = "birthDate")
    @Mapping(source = "direccion", target = "address")
    @Mapping(source = "genero", target = "gender")
    StudentEntity mapUpdateEntity(RequestDto requestDto, @MappingTarget StudentEntity studentEntity);
}
