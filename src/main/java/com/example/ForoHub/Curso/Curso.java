/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ForoHub.Curso;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author Faby
 */
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    
    @Column(name = "nombre_curso")
    private String nombreCurso;
    
    @Column(name = "nombre_profesor")
    private String nombreProfesor;
    
    
    public Curso(DtoCurso curso) {
        this.nombreCurso = curso.nombreCurso();
        this.nombreProfesor = curso.nombreProfesor();
        
    }
    
    //actualizar datos

    public Curso actualizarCurso(DtoCurso curso) {
        this.nombreCurso= curso.nombreCurso();
        this.nombreProfesor=curso.nombreProfesor();
        return this;
    }
    
    }

