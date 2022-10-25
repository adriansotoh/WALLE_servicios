DROP DATABASE IF EXISTS bd_walle;
CREATE DATABASE bd_walle;

USE bd_walle;

DROP TABLE IF EXISTS rol;
CREATE TABLE rol(
id_rol			int			not null 	auto_increment,
descripcion	 	varchar(20)	not null,
primary key (id_rol)
);

DROP TABLE IF EXISTS opcion;
CREATE TABLE opcion(
id_opcion		int			not null 	auto_increment,
descripcion	 	varchar(20)	not null,
ruta			varchar(30) not null,
tipo			int 		not null,
estado			int			not null,
primary key (id_opcion)
);

DROP TABLE IF EXISTS urgencia;
CREATE TABLE urgencia(
id_urgencia		int			not null	auto_increment,
descripcion 	varchar(20)	not null,
primary key (id_urgencia)
);

DROP TABLE IF EXISTS dificultad;
CREATE TABLE dificultad(
id_dificultad  	int			not null 	auto_increment,
descripcion 	varchar(20)	not null,
primary key (id_dificultad)
);

DROP TABLE IF EXISTS rol_has_opcion;
CREATE TABLE rol_has_opcion(
id_rol  	int		not null,
id_opcion 	int		not null,
-- primary key (id_dificultad)
constraint fk_rol_opcion foreign key (id_rol) references rol(id_rol),
constraint fk_opcion_rol foreign key (id_opcion) references opcion(id_opcion)
);

DROP TABLE IF EXISTS estado;
CREATE TABLE estado(
id_estado  		int			not null 		auto_increment,
descripcion 	varchar(20)	not null,
primary key (id_estado)
);

DROP TABLE IF EXISTS trabajador;
CREATE TABLE trabajador(
id_trabajador  	int			not null 		auto_increment,
id_rol 			int 		not null,
documento		char(12)	not null,
nombres			varchar(50)	not null,
apellidos		varchar(50)	not null,
correo 			varchar(50)	not null,
descripcion 	varchar(20)	null,
usuario 		varchar(10)	not null,
password 		varchar(100) not null,
primary key (id_trabajador),
constraint fk_rol_trabajador foreign key (id_rol) references rol(id_rol)
);

DROP TABLE IF EXISTS ticket;
CREATE TABLE ticket(
id_ticket 		int			not null 		auto_increment,
id_trabajador	int			not null,
id_estado		int			not null,
id_urgencia		int			not null,
id_dificultad	int			not null,
titulo			varchar(40)	not null,
descripcion 	varchar(100)not null,
equipo			varchar(30)	not null,
estrellas 		int 		null,
opinion 		varchar(80)	null,
fecha_creacion	datetime 	null default now(),
primary key (id_ticket),
constraint fk_trabajador_ticket foreign key (id_trabajador) references trabajador(id_trabajador),
constraint fk_estado_ticket foreign key (id_estado) references estado(id_estado),
constraint fk_urgencia_ticket foreign key (id_urgencia) references urgencia(id_urgencia),
constraint fk_dificultad_ticket foreign key (id_dificultad) references dificultad(id_dificultad)
);



-- llenar datos
use bd_walle;


-- Rol
insert into rol values 
(null,"usuario"),
(null,"administrador");

-- Urgencia

insert into urgencia values (null, "Baja"),
(null, "Media"),
 (null, "Alta")
;
-- Dificultad

insert into dificultad values (null, "Baja"),
(null, "Media"),
(null, "Alta")
;
-- Estado
insert into estado values (null, "No iniciado"),
(null, "En proceso"),
(null, "Finalizado"),
(null, "Cancelado")
;
-- Trabajador

insert into trabajador values (null, 1, '14852369', 'Alvaro', 'Guillen Carbajal', 'alvaro.guillen@gmail.com', 'Tecnico  TI', 'AGC', '1234'),
(null, 2, '14852364', 'Andres', 'Gallegos Luque', 'andres.gallegos@gmail.com', 'Admin', 'AGL', '1234'),
(null, 1, '14852361', 'Carlo', 'Soto Hidalgo', 'carlo.soto@gmail.com', 'Tecnico  TI', 'CAS', '1234'),
(null, 1, '14852368', 'Camila', 'Flores Solano', 'camila.flores@gmail.com', 'Tecnico  TI', 'CFS', '1234'),
(null, 1, '14852362', 'Renato', 'Espejo Gozzi', 'renato.espejo@gmail.com', 'Tecnico  TI', 'REG', '1234')
;
-- Ticket

insert into ticket values 
(null, 1, 1, 1, 1, 'No tengo Mouse', 'Mi mouse esta roto', 'Mouse', null, null, null),
(null, 2, 1, 1, 1, 'No tengo Teclado', 'Mi teclado no funciona', 'Teclado', null, null, null),
(null, 3, 1, 1, 1, 'Computadora lenta', 'Mi computadora esta lenta', 'Monitor', null, null, null),
(null, 4, 1, 1, 1, 'Impresora no funciona', 'La impresora no prende', 'Impresora', null, null, null),
(null, 5, 1, 1, 1, 'Auricular roto', 'El cable esta roto del auricular', 'Auricular', null, null, null)


