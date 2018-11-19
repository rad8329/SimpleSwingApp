-- DDL de la base de datos
create database asisControlUAO CHARACTER SET utf8 COLLATE utf8_general_ci;

use asisControlUAO;

create table roles(
	codigo int not null,
	nombre varchar(30) not null,
	descripcion text not null,
	check(codigo > 0),
	primary key(codigo)
);

create table zonas(
	codigo_zona int not null,
	nombre varchar(30) not null,
	descripcion text not null,
	control_acceso bool not null,
	ip_cerradura varchar(20),
	primary key(codigo_zona),
	check(codigo > 0)
);

create table autorizaciones(
	codigo_zona int not null,
	codigo_rol int not null,
	fecha_inicio datetime,
	fecha_fin datetime,
	primary key(codigo_zona, codigo_rol),
	foreign key(codigo_zona) references zonas(codigo_zona),
	foreign key(codigo_rol) references roles(codigo)
);

create table lectoresRFID(
	serial varchar(40) not null,
	codigo_zona int not null,	
	primary key(serial),
	foreign key(codigo_zona) references zonas(codigo_zona)
);

create table programasAcademicos(
	codigo int not null,
	nombre varchar(40) not null,
	primary key(codigo),
	check(codigo > 0)
);

create table dependencias(
	codigo int not null,
	nombre varchar(40) not null,
	primary key(codigo),
	check(codigo > 0)
);

create table eventos(
	consecutivo int not null auto_increment,
	nombre varchar(40) not null,
	descripcion text not null,
	codigo_programa int,
	codigo_dependencia int,
	primary key(consecutivo),
	foreign key(codigo_programa) references programasAcademicos(codigo),
	foreign key(codigo_dependencia) references dependencias(codigo)
);

create table programaciones(
	consecutivo int not null auto_increment,
	codigo_zona int not null,
	fecha_inicio datetime not null,
	fecha_fin datetime not null,
	cod_evento int not null,
	actividades text,
	primary key(consecutivo),
	foreign key(codigo_zona) references zonas(codigo_zona),
	foreign key(cod_evento) references eventos(consecutivo)
);

create table personas(
	codigo int not null,
	nombres varchar(40) not null,
	apellidos varchar(40) not null,
	codigo_rol int not null,
	primary key(codigo),
	foreign key(codigo_rol) references roles(codigo),
	check(codigo > 0)
);


create table enrolamientos(
	codigo_persona int not null,
	codigo_programa int not null,
	primary key(codigo_programa, codigo_persona),
	foreign key(codigo_programa) references programasAcademicos(codigo),
	foreign key(codigo_persona) references personas(codigo)
);

create table ubicaciones(
	consecutivo int not null auto_increment,
	fecha datetime not null,
	serial_lector varchar(40) not null,
	codigo_persona int not null,
	primary key(consecutivo),
	foreign key(serial_lector) references lectoresRFID(serial),
	foreign key(codigo_persona) references personas(codigo)
);

-- DATOS DE PRUEBAS


INSERT INTO programasAcademicos (codigo, nombre) 
	VALUES (1515, 'Ingeniería Infromática');
INSERT INTO programasAcademicos (codigo, nombre) 
	VALUES (1616, 'Ingeniería Electrónica');
INSERT INTO programasAcademicos (codigo, nombre) 
	VALUES (1818, 'Administración de empresas');
INSERT INTO programasAcademicos (codigo, nombre) 
	VALUES (2121, 'Comunicacion');
INSERT INTO programasAcademicos (codigo, nombre) 
	VALUES (1919, 'Diseño');


INSERT INTO dependencias (codigo, nombre) 
	VALUES (4, 'Administración');
INSERT INTO dependencias (codigo, nombre) 
	VALUES (5, 'Vicerrectoría');
INSERT INTO dependencias (codigo, nombre) 
	VALUES (6, 'cafeteria');
INSERT INTO dependencias (codigo, nombre) 
	VALUES (7, 'bienestar');
INSERT INTO dependencias (codigo, nombre) 
	VALUES (8, 'casa');
	
	
INSERT INTO roles (codigo, nombre, descripcion) 
	VALUES (15, 'Profesor', 'Profesores en general');
INSERT INTO roles (codigo, nombre, descripcion) 
	VALUES (16, 'Estudiante', 'Estduiantes en general');
	
INSERT INTO zonas (codigo_zona, nombre, descripcion, control_acceso, ip_cerradura) 
	VALUES (2015, 'La laguna azúl', 'Juanto al árbol grande', false, NULL);
INSERT INTO zonas (codigo_zona, nombre, descripcion, control_acceso, ip_cerradura) 
	VALUES (1015, 'Sala de la muerte', 'InfoLab', false, NULL);
INSERT INTO zonas (codigo_zona, nombre, descripcion, control_acceso, ip_cerradura) 
	VALUES (0101, 'Aulas 1', 'Aulas de clase', false, NULL);
INSERT INTO zonas (codigo_zona, nombre, descripcion, control_acceso, ip_cerradura) 
	VALUES (0202, 'Aulas 2', 'Aulas de clase', false, NULL);
INSERT INTO zonas (codigo_zona, nombre, descripcion, control_acceso, ip_cerradura) 
	VALUES (0303, 'Aulas 3', 'Aulas de clase', false, NULL);
INSERT INTO zonas (codigo_zona, nombre, descripcion, control_acceso, ip_cerradura) 
	VALUES (0404, 'Aulas 4', 'Aulas de clase', false, NULL);


INSERT INTO lectoresRFID (serial, codigo_zona) 
	VALUES ('121212', 2015);
INSERT INTO lectoresRFID (serial, codigo_zona) 
	VALUES ('121213', 2015);
INSERT INTO lectoresRFID (serial, codigo_zona) 
	VALUES ('101010', 1015);
INSERT INTO lectoresRFID (serial, codigo_zona) 
	VALUES ('110011', 0101);
INSERT INTO lectoresRFID (serial, codigo_zona) 
	VALUES ('110022', 0101);
INSERT INTO lectoresRFID (serial, codigo_zona) 
	VALUES ('220011', 0202);
INSERT INTO lectoresRFID (serial, codigo_zona) 
	VALUES ('330011', 0303);
INSERT INTO lectoresRFID (serial, codigo_zona) 
	VALUES ('440011', 0404);
	
INSERT INTO personas (codigo, nombres, apellidos, codigo_rol) 
	VALUES (2170553, 'Rober', 'Diaz', 16);
INSERT INTO personas (codigo, nombres, apellidos, codigo_rol) 
	VALUES (2161634, 'Brayan', 'Quiñonez', 16);
INSERT INTO personas (codigo, nombres, apellidos, codigo_rol) 
	VALUES (2000505, 'Juan', 'Topo', 15);
INSERT INTO personas (codigo, nombres, apellidos, codigo_rol) 
	VALUES (2165430, 'Julian', 'carvajal', 15);
INSERT INTO personas (codigo, nombres, apellidos, codigo_rol) 
	VALUES (2178090, 'Paola', 'Rojas', 16);
INSERT INTO personas (codigo, nombres, apellidos, codigo_rol) 
	VALUES (2123456, 'Lyda', 'peña', 15);

INSERT INTO enrolamientos (codigo_persona, codigo_programa) 
	VALUES (2170553, 1515);
INSERT INTO enrolamientos (codigo_persona, codigo_programa) 
	VALUES (2161634, 1616);
INSERT INTO enrolamientos (codigo_persona, codigo_programa) 
	VALUES (2000505, 1515);
INSERT INTO enrolamientos (codigo_persona, codigo_programa) 
	VALUES (2165430, 1616);
INSERT INTO enrolamientos (codigo_persona, codigo_programa) 
	VALUES (2178090, 1616);
INSERT INTO enrolamientos (codigo_persona, codigo_programa) 
	VALUES (2123456, 1818);

INSERT INTO eventos (nombre, descripcion, codigo_programa) 
	VALUES ("Seguridad en su auto deportivo","En este evento se daran charlas sobre como debe prevenir el robo de una foto por parte de los paparazzi",1515);
INSERT INTO eventos (nombre, descripcion, codigo_programa) 
	VALUES ("IOT","Internet of things for dummies",1515);
INSERT INTO eventos (nombre, descripcion, codigo_programa) 
	VALUES ("Bases de datos","Masacre final",1515);
INSERT INTO eventos (nombre, descripcion, codigo_programa) 
	VALUES ("Congreso de industriales sin casco","Es un evento para administradores",1818);

INSERT INTO programaciones (codigo_zona, fecha_inicio, fecha_fin, cod_evento, actividades) 
	VALUES (2015, '2018-02-20 10:00', '2018-02-20 23:59', 1, "se habla sobre el evento");
INSERT INTO programaciones (codigo_zona, fecha_inicio, fecha_fin, cod_evento, actividades) 
	VALUES (2015, '2018-02-21 10:00', '2018-02-21 23:59', 1, "se almuerza todo el dia");
INSERT INTO programaciones (codigo_zona, fecha_inicio, fecha_fin, cod_evento, actividades) 
	VALUES (2015, '2018-02-20 10:00', '2018-02-20 23:59', 2, "No se hace nada");
INSERT INTO programaciones (codigo_zona, fecha_inicio, fecha_fin, cod_evento, actividades) 
	VALUES (2015, '2018-04-20 13:00', '2018-04-20 13:59', 4, "Se administra el evento");
INSERT INTO programaciones (codigo_zona, fecha_inicio, fecha_fin, cod_evento, actividades) 
	VALUES (1015, '2018-11-15 17:00', '2018-11-15 18:30', 3, "Se llora para pasar la materia T_T");

INSERT INTO ubicaciones (fecha, serial_lector, codigo_persona) 
	VALUES ('2018-02-20 10:15', '121212', 2170553);
INSERT INTO ubicaciones (fecha, serial_lector, codigo_persona) 
	VALUES ('2018-02-20 10:16', '121212', 2000505);
INSERT INTO ubicaciones (fecha, serial_lector, codigo_persona) 
	VALUES ('2018-02-20 10:20', '121212', 2161634);
INSERT INTO ubicaciones (fecha, serial_lector, codigo_persona) 
	VALUES ('2018-11-15 17:15', '101010', 2170553);
INSERT INTO ubicaciones (fecha, serial_lector, codigo_persona) 
	VALUES ('2018-11-15 17:15', '101010', 2161634);
INSERT INTO ubicaciones (fecha, serial_lector, codigo_persona) 
	VALUES ('2018-11-15 17:00', '101010', 2000505);
INSERT INTO ubicaciones (fecha, serial_lector, codigo_persona) 
	VALUES ('2018-11-15 18:30', '101010', 2000505);



















