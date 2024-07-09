/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ForoHub.Autor;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author Faby
 */
public record DtoAutor(
        @NotBlank
        String nombre,
        
        @NotBlank
        String apellido) {
    
}
