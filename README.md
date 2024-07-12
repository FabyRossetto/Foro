# Foro de Estudiantes

## Descripción del Proyecto

Este es un proyecto de foro para estudiantes donde se pueden escribir y gestionar tópicos. 
Los usuarios deben autenticarse para acceder al foro y realizar acciones como crear, buscar, eliminar y editar tópicos de diferentes cursos.

## Características

- **Autenticación y Autorización**: Implementadas con JWT y Spring Security.
- **Gestión de Tópicos**: Crear, buscar, eliminar y editar tópicos ( CRUD ).
- **Gestión de Cursos**: Los tópicos están asociados a diferentes cursos.
- **Gestión de Autores**: Los tópicos están asociados obligatoriamente a un autor.
- **Registro y Login**: Los usuarios deben autenticarse para acceder al foro.
- **Pruebas**: Implementadas con JUnit y Mockito.
- **Migraciones de Base de Datos**: Gestionadas con Flyway.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **MySQL**: Base de datos relacional.
- **Flyway**: Para migraciones de base de datos.
- **JUnit**: Para pruebas unitarias.
- **Mockito**: Para pruebas de integración.
