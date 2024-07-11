/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ForoHub.Controllers;

import com.example.ForoHub.Autor.DtoAutor;
import com.example.ForoHub.Curso.DtoCurso;
import com.example.ForoHub.Topico.DatosActualizarTopico;
import com.example.ForoHub.Topico.DatosListadoTopicos;
import com.example.ForoHub.Topico.DatosRegistroTopico;
import com.example.ForoHub.Topico.DatosRespuestaTopico;
import com.example.ForoHub.Topico.TopicRepository;
import com.example.ForoHub.Topico.Topico;
import com.example.infra.TratadorDeErrores.ValidacionDeIntegridad;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

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

    @GetMapping
    //"Obtiene el listado de los topicos")
    public ResponseEntity<List<DatosListadoTopicos>> listadoTopicos() {
        List<DatosListadoTopicos> topicos = tr.findAll()
                .stream()
                .sorted((t1, t2) -> t1.getFechaDeCreacion().compareTo(t2.getFechaDeCreacion()))
                .limit(10)
                .map(DatosListadoTopicos::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    //"Obtiene los registros de los topicos con el ID")
    public ResponseEntity<DatosListadoTopicos> retornaDatosTopico(@PathVariable Long id) throws Exception {
        Topico topico = tr.findById(id).orElseThrow(() -> new ValidacionDeIntegridad("Tópico no encontrado"));
       
        //titulo,mensaje,fechaDeCreacion, estado,autor,curso. Porque necesito estos datos es que uso el record DatosListadoTopicos.
        DatosListadoTopicos datosTopico = new DatosListadoTopicos(topico);
        return ResponseEntity.ok(datosTopico);
    }

    //actualiza los datos del topico
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) throws Exception {
        Optional<Topico> optionalTopico = tr.findById(id);
        if (optionalTopico.isEmpty()) {
            throw new ValidacionDeIntegridad("Tópico no encontrado");
        }

        Topico topico = optionalTopico.get();
        topico.actualizarDatos(datosActualizarTopico);

        tr.save(topico);

        return ResponseEntity.ok(topico);
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    //"Elimina un topico a partir del ID")
    public ResponseEntity eliminarTopico(@PathVariable Long id) throws Exception {
        Optional<Topico> optionalTopico = tr.findById(id);
        if(optionalTopico.isPresent()){
        tr.deleteById(id);
        }else{
            throw new ValidacionDeIntegridad("Tópico no encontrado");
        }
        return ResponseEntity.noContent().build();
    }
   
}
