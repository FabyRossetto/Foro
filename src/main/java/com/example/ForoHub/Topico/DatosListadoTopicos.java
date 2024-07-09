/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ForoHub.Topico;


import java.time.LocalDateTime;

/**
 *
 * @author Faby
 */
public record DatosListadoTopicos ( String titulo,String mensaje,LocalDateTime fechaDeCreacion,String estado,String autor,String curso){
   

    public DatosListadoTopicos(Topico topic) {
        this( topic.getTitulo(),topic.getMensaje(),topic.getFechaDeCreacion(), topic.getEstado().toString(), "" + topic.getAutor().getNombre() + " " + topic.getAutor().getApellido(),topic.getCurso().getNombreCurso());
    }
}
    

