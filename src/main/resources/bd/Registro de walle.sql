

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
(null, 1, 1, 1, 1, 'No tengo Mouse', 'Mi mouse esta roto', 'Mouse', null, null),
(null, 2, 1, 1, 1, 'No tengo Teclado', 'Mi teclado no funciona', 'Teclado', null, null),
(null, 3, 1, 1, 1, 'Computadora lenta', 'Mi computadora esta lenta', 'Monitor', null, null),
(null, 4, 1, 1, 1, 'Impresora no funciona', 'La impresora no prende', 'Impresora', null, null),
(null, 5, 1, 1, 1, 'Auricular roto', 'El cable esta roto del auricular', 'Auricular', null, null)

