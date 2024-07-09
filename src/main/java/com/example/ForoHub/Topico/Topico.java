/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ForoHub.Topico;

import com.example.ForoHub.Curso.Curso;
import com.example.ForoHub.Autor.Autor;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author Faby
 */
@Table(name = "topico")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titulo;
    
    private String mensaje;
    
    @Column(name = "fecha")
    private LocalDateTime fechaDeCreacion;
    
    @Enumerated(EnumType.STRING)
    private Estado estado;
    
    @Embedded
    private Autor autor;
    
    @Embedded
    private Curso curso;
    
    
    public Topico(DatosRegistroTopico topic) {
        
        this.titulo = topic.titulo();
        this.mensaje = topic.mensaje();
         this.fechaDeCreacion= LocalDateTime.now();
        this.estado= topic.estado();
        this.autor = new Autor(topic.autor());
        this.curso = new Curso(topic.curso());
       
    }
    
    //actualizar datos topico
    
    
}
