/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ForoHub.Topico;

import com.example.ForoHub.Autor.DtoAutor;
import com.example.ForoHub.Curso.DtoCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Faby
 */
public record DatosActualizarTopico (
    @NotBlank String titulo,
    @NotBlank String mensaje,
    @NotNull String estado,
    @NotNull DtoAutor autor,
    @NotNull DtoCurso curso
    
        )
{}

