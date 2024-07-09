/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ForoHub.Autor;

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
public class Autor {
    
    private String nombre;
    private String apellido;
   
    
    public Autor(DtoAutor autor) {
        this.nombre = autor.nombre();
        this.apellido = autor.apellido();
         
    }
    
    //actualizar datos
}
