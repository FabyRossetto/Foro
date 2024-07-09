/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ForoHub.Topico;

import com.example.ForoHub.Autor.DtoAutor;
import com.example.ForoHub.Curso.DtoCurso;

/**
 *
 * @author Faby
 */
public record DatosRespuestaTopico(Long id, String titulo,
        String mensaje,DtoAutor autor,DtoCurso curso) {
    
}
