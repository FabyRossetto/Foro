
create table topico(

    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(300) not null unique,
    fecha datetime not null,
    estado varchar(100) not null,
    nombre varchar(50) not null,
    apellido varchar (50)not null,
    nombre_curso varchar(100) not null,
    nombre_profesor varchar (50)not null,
  

    primary key(id)





);
