/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Controller;

import com.example.ForoHub.Autor.DtoAutor;
import com.example.ForoHub.Curso.DtoCurso;
import com.example.ForoHub.ForoHubApplication;
import com.example.ForoHub.Topico.DatosActualizarTopico;
import com.example.ForoHub.Topico.DatosRegistroTopico;
import com.example.ForoHub.Topico.Estado;
import com.example.ForoHub.Topico.TopicRepository;
import com.example.ForoHub.Topico.Topico;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(classes = ForoHubApplication.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
@SuppressWarnings("all")
@TestPropertySource(locations = "classpath:application_test.properties")
public class TopicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosActualizarTopico> actualizarTopicoJacksonTester;

    @Autowired
    private JacksonTester<Topico> topicoJacksonTester;

    @MockBean
    private TopicRepository topicoRepository;

    @Autowired
    private JacksonTester<DatosRegistroTopico> registroTopicoJacksonTester;

    @Test
    @DisplayName("Debería retornar estado HTTP 400 cuando los datos ingresados son inválidos")
    @WithMockUser
    void actualizarEscenario1() throws Exception {
        // Given
        long idExistente = 1L;
        var datosInvalidos = new DatosActualizarTopico("", "", "ABIERTO", new DtoAutor("Nombre", "Apellido"), new DtoCurso("Curso", "Profesor"));

        // Simular la existencia del tópico
        var topicoExistente = new Topico(new DatosRegistroTopico("Título original", Estado.ABIERTO, "Mensaje original", new DtoAutor("Nombre", "Apellido"), new DtoCurso("Curso", "Profesor")));
        ReflectionTestUtils.setField(topicoExistente, "id", idExistente);
        when(topicoRepository.findById(idExistente)).thenReturn(Optional.of(topicoExistente));

        // When
        var response = mvc.perform(put("/topicos/{id}", idExistente)
                .contentType(MediaType.APPLICATION_JSON)
                .content(actualizarTopicoJacksonTester.write(datosInvalidos).getJson()))
                .andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("debería retornar estado http 200 cuando los datos ingresados son válidos")
    @WithMockUser
    void actualizarEscenario2() throws Exception {
        // given
        var id = 7L;
        var datos = new DatosActualizarTopico("Titulo actualizado test", "Mensaje actualizado test", "ABIERTO", new DtoAutor("Nombre", "Apellido"), new DtoCurso("Curso", "Profesor"));
        var topicoExistente = new Topico(new DatosRegistroTopico("Titulo original", Estado.ABIERTO, "Mensaje original", new DtoAutor("Nombre", "Apellido"), new DtoCurso("Curso", "Profesor")));

        // Mockear el comportamiento del repositorio
        when(topicoRepository.findById(id)).thenReturn(Optional.of(topicoExistente));

        // when
        var response = mvc.perform(put("/topicos/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(actualizarTopicoJacksonTester.write(datos).getJson()))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = topicoJacksonTester.write(topicoExistente).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("debería retornar estado HTTP 201 cuando se registra un tópico")
    @WithMockUser
    void registrarTopico() throws Exception {
        // given
        var datosRegistro = new DatosRegistroTopico("Título desde test registro", Estado.ABIERTO, "Mensaje testing", new DtoAutor("Nombre6", "Apellido6"), new DtoCurso("Curso", "Profesor"));

        // Mockear el comportamiento del repositorio para devolver un tópico con ID asignado
        when(topicoRepository.save(any(Topico.class))).thenAnswer(invocation -> {
            Topico t = invocation.getArgument(0);
            // Simular la asignación de ID
            ReflectionTestUtils.setField(t, "id", 1L);
            return t;
        });

        // when
        var response = mvc.perform(post("/topicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(registroTopicoJacksonTester.write(datosRegistro).getJson()))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        // Verificar el cuerpo de la respuesta JSON
        var jsonResponse = response.getContentAsString();
        var topicoRespuesta = new ObjectMapper().readValue(jsonResponse, Topico.class);
        assertThat(topicoRespuesta.getId()).isNotNull();
    }

}
