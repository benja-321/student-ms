package com.example.student.dto;

import java.time.LocalDate;

public class RequestDto {

    private String numeroDocuemnto;
    private String tipoDocumento;
    private Integer edad;
    private String universidad;
    private String estadoCivil;
    private String estado;
    private LocalDate fechanNacimiento;
    private String direccion;
    private Character genero;

    public String getNumeroDocuemnto() {
        return numeroDocuemnto;
    }

    public void setNumeroDocuemnto(String numeroDocuemnto) {
        this.numeroDocuemnto = numeroDocuemnto;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechanNacimiento() {
        return fechanNacimiento;
    }

    public void setFechanNacimiento(LocalDate fechanNacimiento) {
        this.fechanNacimiento = fechanNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }
}
