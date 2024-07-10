/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.infra.TratadorDeErrores;

/**
 *
 * @author Faby
 */
public class ValidacionDeIntegridad extends RuntimeException {

    public ValidacionDeIntegridad(String mensaje) {
        super(mensaje);
    }
}
