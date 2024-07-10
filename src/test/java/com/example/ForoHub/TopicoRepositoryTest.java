/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ForoHub;

import com.example.ForoHub.Autor.DtoAutor;
import com.example.ForoHub.Curso.DtoCurso;
import com.example.ForoHub.Topico.DatosRegistroTopico;
import com.example.ForoHub.Topico.Estado;
import com.example.ForoHub.Topico.TopicRepository;
import com.example.ForoHub.Topico.Topico;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author Faby
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TopicoRepositoryTest {

    @Autowired
    private TopicRepository topicoRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("deberia retornar nulo cuando el topico no existe en la base de datos")
    void seleccionarTopicoPorIdEscenario1() {
        //given
        var idInexistente = 999L;

        //when
        var topico = topicoRepository.findById(idInexistente);

        //then
        assertThat(topico).isEmpty();
    }

    @Test
    @DisplayName("deberia retornar un topico cuando este existe en la base de datos")
    void seleccionarTopicoPorIdEscenario2() {
        //given
        var topico = registrarTopico("Titulo del topico prueba", Estado.ABIERTO, "Mensaje del topico prueba", new DtoAutor("Nombre", "Apellido"), new DtoCurso("Curso", "Profesor"));

        //when
        var topicoEncontrado = topicoRepository.findById(topico.getId());

        //then
        assertThat(topicoEncontrado).isPresent();
        assertThat(topicoEncontrado.get()).isEqualTo(topico);
    }

    private Topico registrarTopico(String titulo, Estado estado, String mensaje, DtoAutor autor, DtoCurso curso) {
        DatosRegistroTopico datosRegistroTopico = new DatosRegistroTopico(titulo, estado, mensaje, autor, curso);
        Topico topico = new Topico(datosRegistroTopico);
        em.persist(topico);
        return topico;
    }
}
