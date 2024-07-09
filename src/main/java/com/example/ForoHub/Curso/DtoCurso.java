/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ForoHub.Curso;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author Faby
 */
public record DtoCurso(
        @NotBlank
        String nombreCurso,

        @NotBlank
        String nombreProfesor){
}
