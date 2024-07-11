DELETE FROM `ForoHub_test`.`topico`;

INSERT INTO  `ForoHub_test`.`topico`
(`id`, `titulo`, `mensaje`, `fecha`, `estado`, `nombre`, `apellido`, `nombre_curso`, `nombre_profesor`)  
VALUES ('1', 'Primer Tópico desde V3', 'Este es el mensaje del primer tópico de V3', '2023-07-11 10:00:00', 'abierto', 'Juan', 'Pérez', 'Matemáticas', 'Dr. González');
