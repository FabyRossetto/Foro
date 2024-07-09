/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ForoHub.Controllers;

import com.example.ForoHub.Autor.DtoAutor;
import com.example.ForoHub.Curso.DtoCurso;
import com.example.ForoHub.Topico.DatosRegistroTopico;
import com.example.ForoHub.Topico.DatosRespuestaTopico;
import com.example.ForoHub.Topico.TopicRepository;
import com.example.ForoHub.Topico.Topico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Faby
 */
@RestController
@RequestMapping("/topicos")
public class TopicController {
    
    @Autowired
    private TopicRepository tr;
    
    @PostMapping
    @Transactional
    // "Registra un nuevo topico en la base de datos"
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistro,
                                                                UriComponentsBuilder uriComponentsBuilder) {
        //ACA SE GUARDA UN TOPICO CON TODOS LOS DATOS DEL REGISTRO
        Topico topico = tr.save(new Topico(datosRegistro));
        
        //ACA SE CREA LA RESPUESTA
        DatosRespuestaTopico datosRespuesta = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
               
                new DtoAutor(topico.getAutor().getNombre(), topico.getAutor().getApellido()),
                new DtoCurso(topico.getCurso().getNombreCurso(), topico.getCurso().getNombreProfesor())
                       );

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);

    }
    
}
