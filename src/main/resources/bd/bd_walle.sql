DROP DATABASE IF EXISTS bd_walle;
CREATE DATABASE bd_walle;

USE bd_walle;

DROP TABLE IF EXISTS rol;
CREATE TABLE rol(
id_rol			int			not null 	auto_increment,
descripcion	 	varchar(20)	not null,
primary key (id_rol)
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
password 		varchar(20) not null,
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
primary key (id_ticket),
constraint fk_trabajador_ticket foreign key (id_trabajador) references trabajador(id_trabajador),
constraint fk_estado_ticket foreign key (id_estado) references estado(id_estado),
constraint fk_urgencia_ticket foreign key (id_urgencia) references urgencia(id_urgencia),
constraint fk_dificultad_ticket foreign key (id_dificultad) references dificultad(id_dificultad)
);

SELECT * FROM ticket
