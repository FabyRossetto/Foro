/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ForoHub.Topico;

import com.example.ForoHub.Autor.DtoAutor;
import com.example.ForoHub.Curso.DtoCurso;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Faby
 */
public record DatosRegistroTopico (
        @NotBlank
        String titulo,
        
        @NotNull
        Estado estado,
        
        @NotBlank
        String mensaje,

        @NotNull @Valid DtoAutor autor,
        @NotNull @Valid DtoCurso curso){

   
    
}
