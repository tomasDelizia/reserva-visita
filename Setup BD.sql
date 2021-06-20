CREATE DATABASE MUSEO_PICTORICO;

USE MUSEO_PICTORICO;

-- DBCC CHECKIDENT ('EMPLEADOS', RESEED, 0);	-- Resetear contador de id de una tabla

CREATE TABLE CARGOS (
	id_cargo TINYINT IDENTITY,
	nombre VARCHAR(50) NOT NULL,
	descripcion VARCHAR(MAX),
	CONSTRAINT cargos_id_cargo_pk PRIMARY KEY(id_cargo));

CREATE TABLE ESCUELAS (
    id_escuela INT IDENTITY,
    nombre VARCHAR(50) NOT NULL,
    mail VARCHAR(50),
    calle_nombre VARCHAR(50),
    calle_numero INT,
    telefono_celular VARCHAR(30),
    telefono_fijo VARCHAR(30),
    CONSTRAINT escuelas_id_escuela_pk PRIMARY KEY(id_escuela)
);

CREATE TABLE TIPOS_DE_VISITA (
    id_tipo_visita TINYINT IDENTITY,
    nombre VARCHAR(50) NOT NULL,
    CONSTRAINT tipos_de_visita_id_tipo_visita_pk PRIMARY KEY(id_tipo_visita)
);

CREATE TABLE EMPLEADOS (
    id_empleado INT IDENTITY,
    dni INT NOT NULL,
    cuit VARCHAR(13) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    mail VARCHAR(50),
    id_cargo TINYINT,
    id_sede INT,
    fecha_nacimiento DATE NOT NULL,
    fecha_ingreso DATE  NOT NULL,
    calle_nombre VARCHAR(50),
    calle_numero INT,
    telefono VARCHAR(30),
    id_sexo TINYINT,
    codigo_validacion VARCHAR(50),
    CONSTRAINT empleados_id_empleado_pk PRIMARY KEY(id_empleado),
);

CREATE TABLE SEXOS (
    id_sexo TINYINT,
    nombre VARCHAR(30) NOT NULL,
    CONSTRAINT sexos_id_sexo_pk PRIMARY KEY(id_sexo)
);

ALTER TABLE EMPLEADOS
    ADD CONSTRAINT empleados_id_cargo_fk FOREIGN KEY(id_cargo)
        REFERENCES CARGOS(id_cargo)
        ON UPDATE CASCADE
        ON DELETE SET NULL;

ALTER TABLE EMPLEADOS
    ADD CONSTRAINT empleados_id_sexo_fk FOREIGN KEY(id_sexo)
        REFERENCES SEXOS(id_sexo)
        ON UPDATE CASCADE
        ON DELETE SET NULL;

CREATE TABLE SEDES (
    id_sede INT IDENTITY,
    nombre VARCHAR(50) NOT NULL,
    cantidad_maxima_visitantes INT NOT NULL,
    cantidad_maxima_por_guia INT NOT NULL,
    calle_nombre VARCHAR(50),
    calle_numero INT,
    CONSTRAINT id_sede_pk PRIMARY KEY(id_sede)
);

ALTER TABLE EMPLEADOS
    ADD CONSTRAINT empleados_id_sede_fk FOREIGN KEY(id_sede)
        REFERENCES SEDES(id_sede)
        ON UPDATE CASCADE
        ON DELETE SET NULL;

CREATE TABLE EXPOSICIONES (
    id_exposicion INT IDENTITY,
    nombre VARCHAR(50) NOT NULL,
    id_tipo_exposicion TINYINT NOT NULL,
    fecha_inicio DATE,
    fecha_fin DATE,
    fecha_inicio_replanificada DATE,
    fecha_fin_replanificada DATE,
    hora_apertura TIME,
    hora_cierre TIME,
    id_empleado_creador INT,
    CONSTRAINT exposiciones_id_exposicion_pk PRIMARY KEY(id_exposicion)
);

CREATE TABLE TIPOS_DE_EXPOSICION (
    id_tipo_exposicion TINYINT IDENTITY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(MAX),
    CONSTRAINT tipos_de_exposicion_id_tipo_pk PRIMARY KEY(id_tipo_exposicion)
)

ALTER TABLE EXPOSICIONES
    ADD CONSTRAINT exposiciones_id_empleado_fk FOREIGN KEY(id_empleado_creador)
        REFERENCES EMPLEADOS(id_empleado)
        ON UPDATE CASCADE
        ON DELETE NO ACTION;

ALTER TABLE EXPOSICIONES
    ADD CONSTRAINT exposiciones_id_tipo_expo_fk FOREIGN KEY(id_tipo_exposicion)
        REFERENCES TIPOS_DE_EXPOSICION(id_tipo_exposicion)
        ON UPDATE CASCADE
        ON DELETE NO ACTION;

CREATE TABLE OBRAS (
    id_obra INT IDENTITY ,
    nombre_obra VARCHAR(50) NOT NULL,
    id_artista INT,
    alto DECIMAL(4,2),
    ancho DECIMAL(4,2),
    peso DECIMAL(6,2),
    valuacion DECIMAL(11,2),
    duracion_resumida TIME,
    duracion_extendida TIME,
    fecha_creacion DATE,
    fecha_primer_ingreso DATE,
    fecha_registracion DATE,
    id_estilo TINYINT,
    id_tecnica TINYINT,
    id_tematica TINYINT,
    descripcion VARCHAR(MAX),
    codigo_sensor INT,
    id_empleado_creador INT NOT NULL,
    CONSTRAINT obras_id_obra_pk PRIMARY KEY(id_obra)
);

CREATE TABLE DETALLES_DE_EXPOSICION (
    id_exposicion INT,
    id_obra INT,
    lugar_asignado INT,
    CONSTRAINT detalles_expo_id_expo_id_obra_pk PRIMARY KEY(id_exposicion, id_obra)
);

ALTER TABLE DETALLES_DE_EXPOSICION
	ADD CONSTRAINT dt_expo_id_obra_fk FOREIGN KEY(id_obra) REFERENCES OBRAS(id_obra)
		ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE DETALLES_DE_EXPOSICION
	ADD CONSTRAINT dt_expo_id_expo_fk FOREIGN KEY(id_exposicion) REFERENCES EXPOSICIONES(id_exposicion)
		ON UPDATE CASCADE ON DELETE CASCADE;

CREATE TABLE PUBLICOS_DESTINO (
    id_publico INT IDENTITY,
    nombre VARCHAR(50) NOT NULL,
    caracteristicas VARCHAR(MAX),
    CONSTRAINT publicos_destino_id_publico_pk PRIMARY KEY(id_publico)
);

CREATE TABLE PUBLICOS_X_EXPOSICIONES (
    id_exposicion INT,
    id_publico INT,
    CONSTRAINT publi_x_expo_id_expo_id_publi_pk PRIMARY KEY(id_exposicion, id_publico),
    CONSTRAINT publi_x_expo_id_expo_fk FOREIGN KEY(id_exposicion) REFERENCES EXPOSICIONES(id_exposicion)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT publi_x_expo_id_publi_fk FOREIGN KEY(id_publico) REFERENCES PUBLICOS_DESTINO(id_publico)
        ON UPDATE CASCADE ON DELETE CASCADE,
);

CREATE TABLE USUARIOS (
    id_usuario INT IDENTITY,
    nombre_usuario VARCHAR(50) NOT NULL,
    contrasena VARCHAR(50) NOT NULL,
    caducidad DATE,
    id_empleado INT NOT NULL,
    CONSTRAINT usuarios_id_usuario_pk PRIMARY KEY(id_usuario),
    CONSTRAINT usuarios_id_empleado_fk FOREIGN KEY(id_empleado) REFERENCES EMPLEADOS(id_empleado)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE SESIONES (
    id_usuario INT,
    fecha_hora_inicio DATETIME,
    fecha_hora_fin DATETIME,
    CONSTRAINT sesiones_id_usuario_fecha_hora_inicio_pk PRIMARY KEY(id_usuario, fecha_hora_inicio),
    CONSTRAINT sesiones_id_usuario_fk FOREIGN KEY(id_usuario) REFERENCES USUARIOS(id_usuario)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE DIAS_DE_SEMANA (
    id_dia TINYINT,
    nombre VARCHAR(10) NOT NULL,
    CONSTRAINT dias_de_semana_id_dia_pk PRIMARY KEY(id_dia)
);

CREATE TABLE HORARIOS (
id_horario INT IDENTITY,
hora_inicio TIME,
hora_fin TIME,
id_dia TINYINT
CONSTRAINT horarios_pk PRIMARY KEY(id_horario),
CONSTRAINT horarios_id_dia FOREIGN KEY(id_dia)
REFERENCES DIAS_DE_SEMANA(id_dia)
);


CREATE TABLE HORARIOS_DE_EMPLEADOS (
	id_empleado INT,
	id_horario INT,
    CONSTRAINT horarios_de_empleados_id_emp_id_horario_pk PRIMARY KEY(id_empleado, id_horario),
    CONSTRAINT horarios_de_empleados_id_emp_fk FOREIGN KEY(id_empleado)
	REFERENCES EMPLEADOS(id_empleado)
        ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT horarios_de_empleados_id_horario_fk FOREIGN KEY(id_horario)
	REFERENCES HORARIOS(id_horario)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE HORARIOS_DE_SEDES (
	id_sede INT,
	id_horario INT,
    CONSTRAINT horarios_de_sedes_id_sede_id_horario_pk PRIMARY KEY(id_sede, id_horario),
    CONSTRAINT horarios_de_sedes_id_emp_fk FOREIGN KEY(id_sede)
	REFERENCES SEDES(id_sede)
        ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT horarios_de_sedes_id_horario_fk FOREIGN KEY(id_horario)
	REFERENCES HORARIOS(id_horario)
        ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE RESERVAS_DE_VISITA (
    id_reserva INT,
    cantidad_alumnos INT NOT NULL,
    cantidad_alumnos_confirmada INT,
    fecha_hora_creacion DATETIME NOT NULL,
    fecha_hora_reserva DATETIME NOT NULL,
    duracion_estimada TIME NOT NULL,
    hora_inicio_real TIME,
    hora_fin_real TIME,
    id_escuela INT NOT NULL,
    id_sede INT NOT NULL,
    id_empleado_creador INT NOT NULL,
    CONSTRAINT reservas_de_visita_id_reserva_pk PRIMARY KEY(id_reserva),
    CONSTRAINT reservas_de_visita_id_escuela_fk FOREIGN KEY(id_escuela) REFERENCES ESCUELAS(id_escuela)
        ON UPDATE CASCADE ON DELETE NO ACTION,
    CONSTRAINT reservas_de_visita_id_sede_fk FOREIGN KEY(id_sede) REFERENCES SEDES(id_sede)
        ON UPDATE CASCADE ON DELETE NO ACTION,
    CONSTRAINT reservas_de_visita_id_empleado_creador_fk FOREIGN KEY(id_empleado_creador)
        REFERENCES EMPLEADOS(id_empleado) ON UPDATE NO ACTION ON DELETE NO ACTION,
);

--CREATE TABLE ASIGNACIONES_DE_GUIA (
--	id_guia INT,
--    fecha_hora_inicio DATETIME NOT NULL,
--    fecha_hora_fin DATETIME NOT NULL,
--	id_reserva INT,
--    CONSTRAINT asig_guia_id_guia_fechahora_inicio_pk PRIMARY KEY(id_guia, fecha_hora_inicio),
--    CONSTRAINT asig_guia_id_reserva_fk FOREIGN KEY(id_reserva) REFERENCES RESERVAS_DE_VISITA(id_reserva)
--        ON UPDATE NO ACTION ON DELETE CASCADE,
--	CONSTRAINT asig_guia_id_guia_fk FOREIGN KEY(id_guia) REFERENCES EMPLEADOS(id_empleado)
--        ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE ESTADOS_DE_RESERVA (
    id_estado_reserva TINYINT IDENTITY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(MAX),
    CONSTRAINT estados_de_reserva_id_estado_reserva_pk PRIMARY KEY(id_estado_reserva)
);

--CREATE TABLE CAMBIOS_DE_ESTADO_DE_RESERVA (
--    id_reserva INT,
--    id_estado_reserva TINYINT,
--    fecha_hora_inicio DATETIME NOT NULL,
--    fecha_hora_fin DATETIME,
--    CONSTRAINT cambios_de_estado_res_id_res_id_est_fecha_ini_pk PRIMARY KEY(id_reserva, id_estado_reserva),
--    CONSTRAINT cambios_de_estado_res_id_res_fk FOREIGN KEY(id_reserva) REFERENCES RESERVAS_DE_VISITA(id_reserva)
--        ON UPDATE CASCADE ON DELETE CASCADE,
--    CONSTRAINT cambios_de_estado_res_id_est_fk FOREIGN KEY(id_estado_reserva) REFERENCES ESTADOS_DE_RESERVA(id_estado_reserva)
--        ON UPDATE CASCADE ON DELETE CASCADE
--);

CREATE TABLE EXPOSICIONES_X_SEDES (
    id_sede INT,
    id_exposicion INT,
    CONSTRAINT expo_x_sede_id_sede_id_expo_pk PRIMARY KEY(id_sede, id_exposicion),
    CONSTRAINT expo_x_sede_id_sede_fk FOREIGN KEY(id_sede) REFERENCES SEDES(id_sede)
        ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT expo_x_sede_id_expo_fk FOREIGN KEY(id_exposicion) REFERENCES EXPOSICIONES(id_exposicion)
        ON UPDATE NO ACTION ON DELETE CASCADE,
);

CREATE TABLE EXPOSICIONES_X_RESERVAS (
    id_reserva INT,
    id_exposicion INT,
    CONSTRAINT expo_x_reserva_id_reserva_id_expo_pk PRIMARY KEY(id_reserva, id_exposicion),
    CONSTRAINT expo_x_reserva_id_reserva_fk FOREIGN KEY(id_reserva) REFERENCES RESERVAS_DE_VISITA(id_reserva)
        ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT expo_x_reserva_id_expo_fk FOREIGN KEY(id_exposicion) REFERENCES EXPOSICIONES(id_exposicion)
        ON UPDATE NO ACTION ON DELETE CASCADE,
);


-- POBLAMIENTO TABLAS

INSERT INTO CARGOS (nombre, descripcion) VALUES('Responsable de Obras', N'Persona responsable de registrar la adquisición de las Obras, y la parametrización de la información necesaria para su administración.');
INSERT INTO CARGOS (nombre, descripcion) VALUES('Responsable de Infraestructura', N'Persona responsable de la registración de las sedes y su estructura física.');
INSERT INTO CARGOS (nombre, descripcion) VALUES(N'Responsable de Préstamos', N'Responsable de gestionar los préstamos de Obras tanto de Artistas como a otros Museos.');
INSERT INTO CARGOS (nombre, descripcion) VALUES('Administrador de Exposiciones', 'Responsable de gestionar las exposiciones de las Obras en cada sede.');
INSERT INTO CARGOS (nombre, descripcion) VALUES('Responsable de Visitas', N'Persona responsable de la gestión de las vistas programadas y de reservas de estas.');
INSERT INTO CARGOS (nombre, descripcion) VALUES('Responsable de Ventas', N'Encargado de las ventas de entradas y de la parametrización de la información necesaria para dicha gestión.');
INSERT INTO CARGOS (nombre, descripcion) VALUES('Administrador de RR HH', N'Encargado de la administración de información de los empleados.');
INSERT INTO CARGOS (nombre, descripcion) VALUES(N'Guía', N'Encargado de explicar una por una las Obras durante un recorrido que haya contratado su servicio.');

INSERT INTO TIPOS_DE_VISITA (nombre) VALUES ('Completa');
INSERT INTO TIPOS_DE_VISITA (nombre) VALUES (N'Por Exposición');

INSERT INTO SEXOS (id_sexo, nombre) VALUES (0, 'MASCULINO');
INSERT INTO SEXOS (id_sexo, nombre) VALUES (1, 'FEMENINO');

INSERT INTO ESTADOS_DE_RESERVA (nombre, descripcion) VALUES (N'Pendiente de Confirmación', 'Estado que se asigna incialmente cuando la escuela efectua una reserva.');
INSERT INTO ESTADOS_DE_RESERVA (nombre, descripcion) VALUES ('Escuela Notificada', N'Estado que se asgina de no confirmar la reserva una semana antes de la fecha programada, al enviar automáticamente un recordatorio a la escuela mediante e-mail y WhatsApp.');
INSERT INTO ESTADOS_DE_RESERVA (nombre, descripcion) VALUES ('Cancelada', 'Estado que se asigna cuando la escuela decide cancelar una reserva, mientras no haya sido anulada o se haya realizado la visita asociada.');
INSERT INTO ESTADOS_DE_RESERVA (nombre, descripcion) VALUES ('Anulada', 'Estado que se asigna cuando la escuela no confirma la reserva, a pesar de haber sido notificada previamente.');
INSERT INTO ESTADOS_DE_RESERVA (nombre, descripcion) VALUES ('Confirmada', 'Estado que se asigna cuando la escuela confirma la reserva mediante e-mail o Whatsapp');
INSERT INTO ESTADOS_DE_RESERVA (nombre, descripcion) VALUES ('Visita Realizada', 'Estado que se asigna cuando finalmente se lleva a cabo la reserva de visita solicitada por la escuela');

INSERT INTO DIAS_DE_SEMANA (id_dia, nombre) VALUES
(0, 'LUNES'),
(1, 'MARTES'),
(2, 'MIERCOLES'),
(3, 'JUEVES'),
(4, 'VIERNES'),
(5, 'SABADO'),
(6, 'DOMINGO');

INSERT INTO HORARIOS(hora_inicio, hora_fin, id_dia) VALUES
('08:00', '16:00', 0),
('09:00', '17:00', 0),
('10:00', '18:00', 0),
('11:00', '19:00', 0),
('12:00', '20:00', 0),
('13:00', '21:00', 0),
('14:00', '22:00', 0),
('15:00', '23:00', 0),
('16:00', '00:00', 0),
('08:00', '12:00', 0),
('12:00', '16:00', 0),
('16:00', '20:00', 0),
('20:00', '00:00', 0),

('08:00', '16:00', 1),
('09:00', '17:00', 1),
('10:00', '18:00', 1),
('11:00', '19:00', 1),
('12:00', '20:00', 1),
('13:00', '21:00', 1),
('14:00', '22:00', 1),
('15:00', '23:00', 1),
('16:00', '00:00', 1),
('08:00', '12:00', 1),
('12:00', '16:00', 1),
('16:00', '20:00', 1),
('20:00', '00:00', 1),

('08:00', '16:00', 2),
('09:00', '17:00', 2),
('10:00', '18:00', 2),
('11:00', '19:00', 2),
('12:00', '20:00', 2),
('13:00', '21:00', 2),
('14:00', '22:00', 2),
('15:00', '23:00', 2),
('16:00', '00:00', 2),
('08:00', '12:00', 2),
('12:00', '16:00', 2),
('16:00', '20:00', 2),
('20:00', '00:00', 2),

('08:00', '16:00', 3),
('09:00', '17:00', 3),
('10:00', '18:00', 3),
('11:00', '19:00', 3),
('12:00', '20:00', 3),
('13:00', '21:00', 3),
('14:00', '22:00', 3),
('15:00', '23:00', 3),
('16:00', '00:00', 3),
('08:00', '12:00', 3),
('12:00', '16:00', 3),
('16:00', '20:00', 3),
('20:00', '00:00', 3),

('08:00', '16:00', 4),
('09:00', '17:00', 4),
('10:00', '18:00', 4),
('11:00', '19:00', 4),
('12:00', '20:00', 4),
('13:00', '21:00', 4),
('14:00', '22:00', 4),
('15:00', '23:00', 4),
('16:00', '00:00', 4),
('08:00', '12:00', 4),
('12:00', '16:00', 4),
('16:00', '20:00', 4),
('20:00', '00:00', 4),

('08:00', '16:00', 5),
('09:00', '17:00', 5),
('10:00', '18:00', 5),
('11:00', '19:00', 5),
('12:00', '20:00', 5),
('13:00', '21:00', 5),
('14:00', '22:00', 5),
('15:00', '23:00', 5),
('16:00', '00:00', 5),
('08:00', '12:00', 5),
('12:00', '16:00', 5),
('16:00', '20:00', 5),
('20:00', '00:00', 5),

('08:00', '16:00', 6),
('09:00', '17:00', 6),
('10:00', '18:00', 6),
('11:00', '19:00', 6),
('12:00', '20:00', 6),
('13:00', '21:00', 6),
('14:00', '22:00', 6),
('15:00', '23:00', 6),
('16:00', '00:00', 6),
('08:00', '12:00', 6),
('12:00', '16:00', 6),
('16:00', '20:00', 6),
('20:00', '00:00', 6),

('08:00', '22:00', 0),
('08:00', '22:00', 1),
('08:00', '22:00', 2),
('08:00', '22:00', 3),
('08:00', '22:00', 4),
('08:00', '22:00', 5),
('08:00', '22:00', 6);



INSERT INTO HORARIOS_DE_EMPLEADOS VALUES
(1, 1),
(2, 2),
(3, 15),
(4, 41),
(5, 61),
(6, 62),
(7, 79),
(8, 32),
(9, 16),
(10, 1),
(10, 75),
(10, 77),
(11, 14),
(11,27),
(11,40),
(12,5),
(12,18),
(12,57),
(13,78),
(13,65),
(13,52),
(14,8),
(14,21),
(14,20),
(15,66),
(15,53),
(15,40),
(16,96),
(16,3),
(16,19),
(17, 92),
(17,14),
(17,27),
(18,52),
(18,65),
(18,97),
(19,1),
(19,14),
(19,27),
(20,2),
(20,15),
(21,98),
(22,4),
(22,17),
(22,43),
(23,56),
(23,97),
(24,10),
(24,23),
(24,36),
(25,2),
(25,15),
(26,77),
(26,64),
(27,94),
(11,92),
(11,94),
(11,96),
(11,98),
(12,93),
(12,95),
(12,97),
(13,92),
(13,94),
(13,96),
(13,98),
(14,93),
(14,95),
(14,97),
(15,92),
(15,94),
(15,96),
(15,98),
(16,93),
(16,95),
(16,97),
(17,94),
(17,96),
(17,98),
(18,93),
(18,95),
(19,92),
(19,94),
(19,96),
(19,98),
(20,93),
(20,95),
(20,97),
(21,92),
(21,94),
(21,96),
(22,93),
(22,95),
(22,97),
(23,92),
(23,94),
(23,96),
(23,98),
(24,93),
(24,95),
(24,97);


INSERT INTO HORARIOS_DE_SEDES VALUES
(1, 92), (1, 93), (1, 94), (1, 95), (1, 96), (1, 97), (1, 81),
(2, 92), (2, 93), (2, 94), (2, 95), (2, 96), (2, 66),
(3, 92), (3, 93), (3, 94), (3, 95), (3, 96), (3, 66),
(4, 92), (4, 93), (4, 94), (4, 95), (4, 96), (4, 97),
(5, 92), (5, 93), (5, 94), (5, 95), (5, 96), (5, 66), (5, 81),
(6, 92), (6, 93), (6, 94), (6, 95), (6, 96), (6, 66),
(7, 92), (7, 93), (7, 94), (7, 95), (7, 96), (7, 97), (7, 81),
(8, 92), (8, 93), (8, 94), (8, 95), (8, 96), (8, 66),
(9, 92), (9, 93), (9, 94), (9, 95), (9, 96), (9, 66),
(10, 92), (10, 93), (10, 94), (10, 95), (10, 96), (10, 66), (10, 81);


INSERT INTO TIPOS_DE_EXPOSICION (nombre, descripcion) VALUES ('Permanente',
                                                              N'Tienen vigencia durante varios meses y pueden ser visitadas dentro de los horarios de atención de cada sede.');
INSERT INTO TIPOS_DE_EXPOSICION (nombre, descripcion) VALUES ('Temporal',
                                                              N'Suelen organizarse en fechas cercanas a ciertos acontecimientos de importancia general (por ejemplo, fechas patrias, día de la mujer, día del niño, etc.), tienen vigencia durante pocos días y pueden ser visitadas sólo en determinados horarios (definidos específicamente para cada exposición).');

INSERT INTO ESCUELAS (nombre, mail, calle_nombre, telefono_celular, telefono_fijo)
VALUES (N'Colegio San Pedro Apóstol', 'sanpedro@sanpedroapostol.edu.ar', 'Av. del Piamonte', '3514846584', '4846584'),
       (N'Academia Argüello', 'institucional@aa.edu.ar', N'Pozo de la Loma Esq. Av Rafael Núñez
Argüello', '3513450574', '3450574');
INSERT INTO ESCUELAS (nombre, mail, calle_nombre, calle_numero, telefono_celular, telefono_fijo)
VALUES (N'Colegio Alemán', 'info@colegioalemancba.edu.ar', 'Recta Martinoli Esq. Neper', 6230, '543420834', '3420834');
INSERT INTO ESCUELAS (nombre, mail, calle_nombre, calle_numero, telefono_celular, telefono_fijo)
VALUES (N'Colegio Escuti', 'Escuti@escuti.edu.ar','Nazaret', 3399, '0351 481-5596', '4815596');
INSERT INTO ESCUELAS (nombre, mail, calle_nombre, calle_numero, telefono_celular, telefono_fijo)
VALUES (N'Colegio Madres Escolapias', 'Escolapias@escolapias.edu.ar','Av.Hugo Wast', 4455, '0351 481-2418', '4812418');
INSERT INTO ESCUELAS (nombre, mail, calle_nombre, calle_numero, telefono_celular, telefono_fijo)
VALUES (N'Colegio Taborin', 'Taborin@taborin.edu.ar','Av. Amadeo Sabattini', 359, '351458009', '4583522');
INSERT INTO ESCUELAS (nombre, mail, calle_nombre, calle_numero, telefono_celular, telefono_fijo)
VALUES (N'Colegio Rosarito', 'Rosarito@rosarito.edu.ar','Viracocha', 6933, '03543 44-1373', '441373');
INSERT INTO ESCUELAS (nombre, mail, calle_nombre, calle_numero, telefono_celular, telefono_fijo)
VALUES (N'Colegio Peña', 'Peña@peña.edu.ar','Cd. de Tampa', 2954, '0351 480-6054', '4806054');
INSERT INTO ESCUELAS (nombre, mail, calle_nombre, calle_numero, telefono_celular, telefono_fijo)
VALUES (N'Colegio La Salle', 'Lasalle@lasalle.edu.ar','Av. Recta Martinolli', 6602, '03543 42-0166', '420166');
INSERT INTO ESCUELAS (nombre, mail, calle_nombre,telefono_celular, telefono_fijo)
VALUES (N'Colegio Castel Franco', 'CastelFranco@castelFranco.edu.ar','Av.Lasalle', '03543 42-1802', '421802');
INSERT INTO ESCUELAS (nombre, mail, calle_nombre,calle_numero, telefono_celular, telefono_fijo)
VALUES (N'Colegio Monserrat', 'Monserrat@monserrat.edu.ar','Obispo Trejo',294, '0351 433-2079', '4332079');

INSERT INTO SEDES (nombre, cantidad_maxima_visitantes, cantidad_maxima_por_guia, calle_nombre, calle_numero) VALUES ('Sede Cordoba', 600, 65, 'Obispo Trejos', 825);
INSERT INTO SEDES (nombre, cantidad_maxima_visitantes, cantidad_maxima_por_guia, calle_nombre, calle_numero) VALUES ('Sede Buenos Aires', 500, 25, 'Rondeau', 300);
INSERT INTO SEDES (nombre, cantidad_maxima_visitantes, cantidad_maxima_por_guia, calle_nombre, calle_numero) VALUES ('Bellas Artes', 400, 30, 'Brasil', 128);
INSERT INTO SEDES (nombre, cantidad_maxima_visitantes, cantidad_maxima_por_guia, calle_nombre, calle_numero) VALUES ('Louvre', 300, 50, N'Agustín Roque Arias', 1600);
INSERT INTO SEDES (nombre, cantidad_maxima_visitantes, cantidad_maxima_por_guia, calle_nombre, calle_numero) VALUES (N'Museo Británico', 200, 40, 'Av. De Mayo', 1000);
INSERT INTO SEDES (nombre, cantidad_maxima_visitantes, cantidad_maxima_por_guia, calle_nombre, calle_numero) VALUES ('Museo Nacional del Prado', 450, 80, 'Blamey Lafore', 594);
INSERT INTO SEDES (nombre, cantidad_maxima_visitantes, cantidad_maxima_por_guia, calle_nombre, calle_numero) VALUES ('Museo Metropolitano de Arte', 550, 60, 'Uruguay', 495);
INSERT INTO SEDES (nombre, cantidad_maxima_visitantes, cantidad_maxima_por_guia, calle_nombre, calle_numero) VALUES ('Historia Natural', 400, 50, N'José Manuel Estrada', 15);
INSERT INTO SEDES (nombre, cantidad_maxima_visitantes, cantidad_maxima_por_guia, calle_nombre, calle_numero) VALUES ('Sede centro', 500, 90, 'Entre Rios', 105);
INSERT INTO SEDES (nombre, cantidad_maxima_visitantes, cantidad_maxima_por_guia, calle_nombre, calle_numero) VALUES ('Sede Zona Norte', 320, 45, 'Obispo Lascano', 2448);

INSERT INTO EMPLEADOS (dni , cuit , nombre, apellido , mail, id_cargo, id_sede , fecha_nacimiento , fecha_ingreso , calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (39895674 , '23-39895674-3' , N'María' , 'Robles' , 'mrobles@hotmail.com', 1 , 1 , '1995-04-09', '2010-08-05', 'Francisco Aston', 3190 ,'3515459874' , 1, 1234);
INSERT INTO EMPLEADOS (dni , cuit , nombre, apellido , mail, id_cargo, id_sede , fecha_nacimiento , fecha_ingreso , calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (40156147 , '23-40156147-4', 'Lautaro' , 'Rodriguez' , 'lautiro@hotmail.com' , 2, 2 , '1996-12-12', '2018-01-02', 'Felix Olmedo', 4578, '3513140012', 0, 5678);
INSERT INTO EMPLEADOS (dni , cuit , nombre, apellido , mail, id_cargo, id_sede , fecha_nacimiento, fecha_ingreso , calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (41256365, '23-41256365-3', 'Milagros' , 'Ramos' , 'miliramos@hotmail.com' , 3, 3, '1997-06-04', '2016-04-01', 'Melincue', 7894, '3513140165', 1, 1423);
INSERT INTO EMPLEADOS (dni , cuit , nombre, apellido , mail, id_cargo, id_sede , fecha_nacimiento , fecha_ingreso , calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (42217998, '23-42217991-3', N'Raúl' , 'Gonzalez', 'raulgonzalez@hotmail.com', 4 , 4, '1998-11-11', '2021-01-01', 'Guemes' , 7415 , '351605738', 0, 3512);
INSERT INTO EMPLEADOS (dni , cuit , nombre, apellido , mail, id_cargo, id_sede , fecha_nacimiento ,fecha_ingreso , calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (43512364, '23-43512364-2', 'Eduardo' , 'Suarez', 'edusuarez@hotmail.com' , 5 , 5,  '1999-08-06', '2020-01-05', 'Lima', 1452, '3514023152', 0, 4521);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (21392420, '27-21392420-4', 'Agustina', 'Gomez', 'agusgomez@gmail.com', 6, 6, '1968-09-05', '2020-11-11', 'General Paz', 1730, '3517157950', 1, 1520);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (30520687, '20-30520687-2', 'Juan', 'Chavez', 'chavezj@hotmail.com', 7, 7, '1970-10-10', '2016-05-08', 'Buenos Aires', 625, '3516652250', 0, 1568);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (19562852, '20-19562852-3', N'María Belen', 'López', 'mblopez@hotmail.com', 8, 8, '1965-04-02', '2016-03-05', 'Marcelo Lopez', 10, '3517689456', 1, 6958);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (41562398, '27-41562398-4', 'Carla', 'Juarez', 'chavezj@hotmail.com', 8, 9, '1998-01-02', '2019-03-01', 'Velez Sarsfield', 658, '3517852456', 1, 2589);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (32650897, '20-32650897-8', 'Mauricio', 'Cuevas', 'mcuevas@gmail.com', 8, 10, '1987-05-20', '2020-02-10', 'Independencia', 210, '3512987562', 0, 1518);

INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (43438359, '20-43438359-6', 'Tomás', 'Delizia', 'tomidelizia@gmail.com', 5, 1, '2001-05-29', '2020-01-01', 'Crisol', 62, '3874535259', 0, 5538);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (43267361, '21-43267361-8', 'Mauro', 'Gabrielli', 'maurogab@gmail.com', 8, 10, '1987-05-20', '2020-02-10', 'Chacabuco', 210, '3512987562', 0, 1518);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (38648797, '26-38648797-8', 'Sastre', 'Martin', 'sastresatrin@gmail.com', 8, 10, '1995-02-20', '2020-03-20', 'Roberto Sisi', 280, '3512446562', 0, 1388);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (26659127, '26659127-8', 'Peter', 'Sanchez', 'peterelsanchez@gmail.com', 8, 6, '1997-01-29', '2021-02-17', 'Juan Rodriguez', 212, '351777989', 0, 1438);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (29650897, '19-29650897-8', 'Juan', 'Obolo', 'obolojuan45@gmail.com', 8, 9, '1990-09-13', '2019-03-14', 'Ayovi', 330, '351458125', 0, 1917);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (43276589, '25-43276589-8', 'Agustin', 'Limon', 'aguslimontayahoo@gmail.com', 8, 4, '2000-05-20', '2020-08-20', 'Independencia', 560, '351564645', 1, 4848);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (41256398, '25-41256398-8', 'Joaquin', 'Lopez', 'elJoacolopez@gmail.com', 8, 8, '2001-05-20', '2021-01-15', 'Paraguay', 550, '3513467895', 0, 4458);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (32461321, '25-32461321-8', 'Yenifer', 'Garcia', 'yenigarcia_12@gmail.com', 8, 8, '1999-09-30', '2020-03-05', 'Pergamino', 600, '351787878', 1, 5562);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (36798420, '25-36798420-8', 'Maria', 'Casas', 'casas_454@gmail.com', 8, 7, '1989-09-30', '2020-01-23', 'Balcarce', 993, '351787878', 1, 5562);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (45124578, '25-45124578-8', 'Ana', 'De la Cruz', 'anitass4@gmail.com', 8, 3, '1949-01-10', '2020-01-23', 'Gorriti', 393, '351236878', 1, 6662);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (37542651, '20-37542651-8', 'Cristina', 'Diaz', 'crisdiaz@gmail.com', 8, 4, '1974-07-06', '2020-03-05', 'Bodereau', 241, '3515478562', 1, 7495);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (37547698, '20-37547698-8', 'Veronica', 'Rodriguez', 'veror@gmail.com', 8, 1, '1980-07-16', '2021-05-10', 'Rondeau', 748, '3514786325', 1, 4587);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (35698210, '20-35698210-8', 'Josefina', 'Gonzalez', 'josegonzalez@gmail.com', 8, 5, '1988-09-20', '2021-07-11', 'Menendez Pidal', 520, '3518749658', 1, 1111);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (35475963, '20-35475963-8', 'Eugenia', 'Martinez', 'eugem@gmail.com', 8, 8, '1995-09-20', '2020-08-21', 'Roque Funes', 452, '3514758214', 1, 1540);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (32650897, '20-32650897-4', 'Guillermo', 'Gomez', 'guilleg@gmail.com', 8, 3, '1987-05-20', '2020-02-08', 'Mariano Larra', 350, '3512149870', 0, 1333);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (37564851, '20-37564851-8', 'Franco', 'Rodriguez', 'rfranco@gmail.com', 8, 7, '1991-07-10', '2018-02-05', 'Pablo Buitrago', 748, '3512568974', 0, 4852);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (30265984, '20-30265984-8', 'Agostina','Gonzalez', 'agosgonzalez@gmail.com', 8, 4, '1995-07-20', '2019-04-17', 'Sagrada Familia', 736, '3512563348', 1, 2491);
INSERT INTO EMPLEADOS (dni, cuit, nombre, apellido, mail, id_cargo, id_sede, fecha_nacimiento, fecha_ingreso, calle_nombre, calle_numero, telefono, id_sexo, codigo_validacion)
VALUES (40512984, '20-40512984-8', 'Victoria', 'Fernandez', 'vickifernandez@gmail.com', 8, 5, '1989-07-06', '2017-03-10', 'Gauss', 748, '3512568974', 0, 4852);


INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('milagros_ramos', 'ACD8141', '2022-05-02',3);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('eduardo_suarez', 'AXQ8541', '2021-07-03',5);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('juan_chavez', 'AHG7252', '2022-02-01',7);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('raul_gonzalez', 'AFB7841', '2022-04-09',4);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('mbelen_lopez', 'AMW7486', '2021-08-10',8);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES (N'lautaro_rodriguez', 'NDF5418', '2021-10-21',2);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('agustina_gomez', 'HGD2541', '2021-09-17',6);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('carla_juarez', 'HGT2514', '2022-03-17',9);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('maria_robles', 'KJL3671', '2022-03-17',1);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('mauricio_cuevas', 'JGT2410', '2021-07-20',10);

INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('mauro_gabrielli', 'OWK4271', '2021-10-20',11);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('martin_sastre', 'HIM8965', '2021-08-10',12);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('peter_sanchez', 'PPT2398', '2021-06-28',13);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('juan_obolo', 'GFD7643', '2021-11-15',14);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('agus_limon', 'limonagrio', '2021-09-13',15);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('joaco_lopez', 'tututu123', '2022-01-23',16);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('yenifer45', 'yeni5412', '2022-05-03',17);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('mari78', 'nononon', '2023-10-30',18);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('anaDelvalle', 'flipflopJk', '2021-11-11',19)
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('cristina_diaz', 'HQW2145', '2022-05-21',20);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('veronica_rodriguez', 'PYT4500', '2021-09-08',21);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('josefina_gonzalez', 'KLY8333', '2022-08-21',22);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('eugenia_martinez', 'LVF5123', '2022-01-20',23);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('guille_gomez', 'LSS5163', '2021-10-22',24);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('franco_rodriguez', 'LLE4523', '2022-11-15',25);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('agos_gonzalez', 'LKF2501', '2022-09-05',26);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('vicki_fernandez', 'TRE2105', '2022-11-25',27);
INSERT INTO USUARIOS (nombre_usuario,contrasena,caducidad,id_empleado)
VALUES ('tomas_delizia', '290501', '2022-11-25',11);

INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (1, '2020-05-20 09:00:00','2020-05-20 09:30:00');
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (1, '2020-05-23 08:00:00','2020-05-23 10:45:00');
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (1, '2020-05-23 12:00:00' ,'2020-05-23 12:22:00');
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (1, '2020-05-24 12:00:00' ,'2020-05-25 12:55:00');
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (1, '2020-05-26 11:00:00','2020-05-26 12:30:00');
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (2,'2020-05-20 9:30:00','2020-05-20 11:30:009' );
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (2, '2020-05-22 8:40:00','2020-05-22 10:55:00' );
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (2,'2020-05-23 11:00:00','2020-05-23 12:22:00' );
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (2,'2020-05-25 12:30:00','2020-05-25 12:55:00' );
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (2, '2020-05-26 11:00:00','2020-05-26 12:30:00');
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (3,'2020-05-20 8:30:00','2020-05-20 11:30:00');
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (3, '2020-05-22 7:40:00','2020-05-22 10:55:00' );
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (3,'2020-05-23 10:00:00','2020-05-23 12:22:00' );
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (3,'2020-05-25 11:30:00','2020-05-25 12:55:00' );
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (3, '2020-05-26 08:00:00','2020-05-26 12:30:00');
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (4,'2020-05-20 08:30:00','2020-05-20 11:30:00' );
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (4, '2020-05-21 07:40:00','2020-05-21 10:55:00' );
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (4,'2020-05-22 10:00:00','2020-05-22 12:22:00' );
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (4,'2020-05-25 09:30:00','2020-05-25 12:55:00' );
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (4, '2020-05-26 08:00:00','2020-05-26 12:30:00');
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (5,'2020-05-20 11:15:00','2020-05-20 11:30:00' );
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (5, '2020-05-21 08:40:00','2020-05-21 09:55:00' );
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (6,'2020-05-22 10:00:00','2020-05-22 12:22:00' );
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (6,'2020-05-25 10:30:00','2020-05-25 11:55:00' );
INSERT INTO SESIONES (id_usuario, fecha_hora_inicio, fecha_hora_fin)
VALUES (7, '2020-05-26 09:00:00','2020-05-26 11:30:00');

INSERT INTO PUBLICOS_DESTINO (nombre, caracteristicas) VALUES
(N'Público General', N'Exposición destinada a cualquier tipo de visitante'),
('Menores', N'Exposición destinada a chicos de entre 4 y 17 años'),
('Jubilados', N'Exposición destinada a jubilados'),
('Estudiantes de Arte', N'Exposición destinada a estudiantes de carreras de arte');

INSERT INTO EXPOSICIONES (nombre,id_tipo_exposicion,fecha_inicio,fecha_fin,fecha_inicio_replanificada,fecha_fin_replanificada,hora_apertura,hora_cierre,id_empleado_creador) VALUES
(N'Contemporanea',1,'2021-06-01','2021-07-26', null, null,'08:00:00','17:00:00',3),
('Abstracta',2,'2021-05-01','2021-09-06', null, null,'9:00:00','18:00:00',1),
('Rupestre',2,'2021-07-01','2021-08-06',null , null,'8:00:00','16:30:00',2),
('Occidental',2,'2021-05-15','2021-06-15','2021-06-15','2020-08-28','10:00:00','15:00:00',5),
('Callejero',1,'2021-05-01','2021-07-06', null, null,'11:00:00','16:50:00',3),
('Moderna',1,'2021-07-02','2021-07-22', null , null,'12:00:00','18:40:00',1),
('Oriental',1,'2021-06-12','2021-09-12', null, null,'10:00:00','13:30:00',2),
('El atajo',2,'2021-05-02','2021-06-08','2021-06-08','2021-06-30','8:20:00','12:00:00',6),
('En el malba',1,'2021-06-12','2021-07-08', null, null,'14:00:00','19:00:00',7),
('Insurrectas',2,'2021-05-02','2021-06-28', null, null,'8:00:00','13:00:00',3),
('A traves del cristal',1,'2021-05-02','2021-06-02','2021-06-02','2020-08-30','10:00:00','16:20:00',2),
('Guerra fria',2,'2021-04-02','2021-06-10','2021-06-10','2021-07-03','12:00:00','17:30:00',1),
('UFO', 2,'2021-08-02','2021-08-25', null, null,'08:30:00','15:30:00', 4),
('La hora azul',1,'2021-01-01', '2021-04-06','2021-04-06', '2021-06-30','08:00:00', '18:30:00',4),
('Armonias de Color',2,'2021-05-20','2021-10-25',null , null,'09:00:00','18:00:00',4),
('Viento y Marea', 1,'2021-02-01','2021-10-01', null, null,'10:35:00', '20:00:00',4),
('Implosion',2,'2021-04-05','2021-06-05','2021-06-05','2021-07-01','10:30:00','13:30:00',4);



INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 2, 1);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 5, 4);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 3, 4);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 2, 2);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 5, 3);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 3, 2);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 4, 1);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 1, 1);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 1, 2);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 6, 3);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 6, 2);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 6, 1);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 7, 4);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 7, 3);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 8, 1);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 8, 2);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 9, 3);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 9, 2);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 9, 1);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 10, 1);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 11, 3);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 11, 4);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 12, 4);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 13, 2);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 13, 1);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 14, 3);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 14, 4);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 15, 4);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 16, 1);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 17, 2);
INSERT INTO PUBLICOS_X_EXPOSICIONES (id_exposicion, id_publico) VALUES ( 17, 1);

INSERT INTO RESERVAS_DE_VISITA (id_reserva, cantidad_alumnos, cantidad_alumnos_confirmada, fecha_hora_creacion, fecha_hora_reserva, duracion_estimada, hora_inicio_real, hora_fin_real, id_escuela, id_sede, id_empleado_creador)
VALUES (1, 200, 200, '2020-06-06 12:23:00', '2020-06-20 10:30:00', '00:50:00', '10:30:00', '11:30:00', 1, 2, 1);
INSERT INTO RESERVAS_DE_VISITA (id_reserva, cantidad_alumnos, cantidad_alumnos_confirmada, fecha_hora_creacion, fecha_hora_reserva, duracion_estimada, hora_inicio_real, hora_fin_real, id_escuela, id_sede, id_empleado_creador)
VALUES (2, 200, 180, '2020-08-09 09:12:30', '2020-08-13 08:40:00', '00:35:00', '08:45:00', '09:30:00', 2, 4, 2);
INSERT INTO RESERVAS_DE_VISITA (id_reserva, cantidad_alumnos, cantidad_alumnos_confirmada, fecha_hora_creacion, fecha_hora_reserva, duracion_estimada, hora_inicio_real, hora_fin_real, id_escuela, id_sede, id_empleado_creador)
VALUES (3, 150,136,  '2021-02-10 16:53:43', '2021-03-01 14:00:00', '00:30:00', '14:10:00', '14:43:00', 5, 3, 5);
INSERT INTO RESERVAS_DE_VISITA (id_reserva, cantidad_alumnos, cantidad_alumnos_confirmada, fecha_hora_creacion, fecha_hora_reserva, duracion_estimada, hora_inicio_real, hora_fin_real, id_escuela, id_sede, id_empleado_creador)
VALUES (4, 50, 50,  '2021-01-06 09:49:20', '2021-01-08 09:00:00', '00:43:00', '09:00:00', '09:43:00', 6, 5, 6);
INSERT INTO RESERVAS_DE_VISITA (id_reserva, cantidad_alumnos, cantidad_alumnos_confirmada, fecha_hora_creacion, fecha_hora_reserva, duracion_estimada, hora_inicio_real, hora_fin_real, id_escuela, id_sede, id_empleado_creador)
VALUES (5, 100, 90, '2021-03-06 10:58:49', '2021-03-13 17:00:00', '01:15:00', '17:01:00', '18:05:00', 2, 2, 3);
INSERT INTO RESERVAS_DE_VISITA (id_reserva, cantidad_alumnos, cantidad_alumnos_confirmada, fecha_hora_creacion, fecha_hora_reserva, duracion_estimada, hora_inicio_real, hora_fin_real, id_escuela, id_sede, id_empleado_creador)
VALUES (6, 112, 100, '2021-07-07 16:53:40', '2021-07-09 10:00:00', '01:20:00', NULL, NULL, 3, 1, 2);
INSERT INTO RESERVAS_DE_VISITA (id_reserva, cantidad_alumnos, cantidad_alumnos_confirmada, fecha_hora_creacion, fecha_hora_reserva, duracion_estimada, hora_inicio_real, hora_fin_real, id_escuela, id_sede, id_empleado_creador)
VALUES (7, 60, 58, '2021-06-03 18:46:49', '2021-07-01 15:32:00', '00:25:00', '15:35:00', '11:00:00', 1, 1, 1);
INSERT INTO RESERVAS_DE_VISITA (id_reserva, cantidad_alumnos, cantidad_alumnos_confirmada, fecha_hora_creacion, fecha_hora_reserva, duracion_estimada, hora_inicio_real, hora_fin_real, id_escuela, id_sede, id_empleado_creador)
VALUES (8, 73, 73, '2021-08-06 14:43:30', '2021-08-16 10:50:00', '01:10:00', NULL, NULL, 7, 3, 4);


INSERT INTO OBRAS (nombre_obra,id_artista,alto,ancho,peso,valuacion,duracion_resumida,duracion_extendida,fecha_creacion,
fecha_primer_ingreso,fecha_registracion,descripcion,codigo_sensor,id_empleado_creador)
VALUES ('Romeo y Julieta',1,1.82,1.20,2.50,750.50,'00:10:00','00:15:00','2021-10-03','2021-10-06','2021-10-09','Drama',5,3);

INSERT INTO OBRAS (nombre_obra,id_artista,alto,ancho,peso,valuacion,duracion_resumida,duracion_extendida,fecha_creacion,
fecha_primer_ingreso,fecha_registracion,descripcion,codigo_sensor,id_empleado_creador)
VALUES ('La Celestina',2,1.98,1.35,2.74,870.50,'00:15:00','00:20:00','2021-05-07','2021-05-15','2021-05-24','Romance',2,4);

INSERT INTO OBRAS (nombre_obra,id_artista,alto,ancho,peso,valuacion,duracion_resumida,duracion_extendida,fecha_creacion,
fecha_primer_ingreso,fecha_registracion,descripcion,codigo_sensor,id_empleado_creador)
VALUES ('La Divina Comedia',3,1.45,1.74,2.21,935.50,'00:20:00','0:25:00','2021-06-07','2021-06-15','2021-06-10','Comedia',1,2);

INSERT INTO OBRAS (nombre_obra,id_artista,alto,ancho,peso,valuacion,duracion_resumida,duracion_extendida,fecha_creacion,
fecha_primer_ingreso,fecha_registracion,descripcion,codigo_sensor,id_empleado_creador)
VALUES ('La vida es sueño',4,1.67,1.54,2.88,465.50,'00:17:00','00:23:00','2021-02-04','2021-02-06','2021-02-10','Terror',3,1);

INSERT INTO OBRAS (nombre_obra,id_artista,alto,ancho,peso,valuacion,duracion_resumida,duracion_extendida,fecha_creacion,
fecha_primer_ingreso,fecha_registracion,descripcion,codigo_sensor,id_empleado_creador)
VALUES ('Hamlet',5,1.88,1.65,2.97,990.10,'00:15:00','00:20:00','2021-09-09','2021-09-10','2021-09-15','Tragedia',4,5);

INSERT INTO OBRAS (nombre_obra,id_artista,alto,ancho,peso,valuacion,duracion_resumida,duracion_extendida,fecha_creacion,
fecha_primer_ingreso,fecha_registracion,descripcion,codigo_sensor,id_empleado_creador)
VALUES ('Don Juan Tenorio',6,1.45,1.20,3.54,500.45,'00:10:00','00:15:00','2021-05-10','2021-05-12','2021-05-15','Comedia',5,6);

INSERT INTO OBRAS (nombre_obra,id_artista,alto,ancho,peso,valuacion,duracion_resumida,duracion_extendida,fecha_creacion,
fecha_primer_ingreso,fecha_registracion,descripcion,codigo_sensor,id_empleado_creador)
VALUES ('Mona Lisa' ,2,1.65,1.10,1.85,900.00, '00:10:00' , '00:15:00' , '2021-09-04' , '2021-09-07' , '2021-09-12' , 'Terror', 8 , 2);

INSERT INTO OBRAS (nombre_obra,id_artista,alto,ancho,peso,valuacion,duracion_resumida,duracion_extendida,fecha_creacion,
fecha_primer_ingreso,fecha_registracion,descripcion,codigo_sensor,id_empleado_creador)
VALUES ('El Grito',3,2.05,2.10, 2.50, 850.00, '00:05:00' , '00:10:00' , '2021-12-06' , '2021-12-08' , '2021-12-15' , 'Suspenso' , 7, 4);

INSERT INTO OBRAS (nombre_obra,id_artista,alto,ancho,peso,valuacion,duracion_resumida,duracion_extendida,fecha_creacion,
fecha_primer_ingreso,fecha_registracion,descripcion,codigo_sensor,id_empleado_creador)
VALUES ('Guernica' ,4,1.58,1.32,2.15,980.00, '00:06:00' , '0:13:00' , '2021-04-11' , '2021-04-15' , '2021-04-18' , 'Romance' , 2 , 3);

INSERT INTO OBRAS (nombre_obra,id_artista,alto,ancho,peso,valuacion,duracion_resumida,duracion_extendida,fecha_creacion,
fecha_primer_ingreso,fecha_registracion,descripcion,codigo_sensor,id_empleado_creador)
VALUES ('El nacimiento de Venus' , 5,2.50,2.15,4.20,500.00, '00:10:00' , '0:15:00' , '2021-11-09' , '2021-11-15' , '2021-11-22' , 'Comedia' , 7, 6);

INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (2, 1, 1);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (5, 3, 2);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (2, 2, 3);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (3, 5, 2);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (2, 3, 1);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (5, 4, 5);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (3, 7, 4);

INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (1, 1, 1);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (4, 2, 2);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (6, 3, 3);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (7, 4, 4);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (8, 5, 5);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (9, 6, 6);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (10, 7, 7);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (11, 8, 8);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (12, 9, 9);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (13, 10, 10);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (14, 1, 10);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (15, 2, 10);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (16, 3, 10);
INSERT INTO DETALLES_DE_EXPOSICION(id_exposicion, id_obra, lugar_asignado) VALUES (17, 4, 10);


INSERT INTO EXPOSICIONES_X_SEDES VALUES (1, 17);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (1, 2);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (1, 3);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (1, 8);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (1, 6);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (1, 14);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (1, 13);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (1, 12);

INSERT INTO EXPOSICIONES_X_SEDES VALUES (2, 1);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (2, 4);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (2, 5);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (2, 6);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (2, 9);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (2, 12);

INSERT INTO EXPOSICIONES_X_SEDES VALUES (3, 2);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (3, 3);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (3, 6);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (3, 8);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (3, 10);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (3, 16);

INSERT INTO EXPOSICIONES_X_SEDES VALUES (4, 16);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (4, 17);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (4, 11);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (4, 1);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (4, 2);

INSERT INTO EXPOSICIONES_X_SEDES VALUES (5, 3);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (5, 7);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (5, 10);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (5, 11);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (5, 12);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (5, 5);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (5, 9);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (5, 16);

INSERT INTO EXPOSICIONES_X_SEDES VALUES (6, 15);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (6, 13);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (6, 6);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (6, 3);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (6, 2);

INSERT INTO EXPOSICIONES_X_SEDES VALUES (7, 1);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (7, 7);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (7, 3);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (7, 9);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (7, 10);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (7, 12);

INSERT INTO EXPOSICIONES_X_SEDES VALUES (8, 11);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (8, 14);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (8, 16);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (8, 2);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (8, 6);

INSERT INTO EXPOSICIONES_X_SEDES VALUES (9, 17);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (9, 15);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (9, 11);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (9, 4);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (9, 5);

INSERT INTO EXPOSICIONES_X_SEDES VALUES (10, 6);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (10, 7);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (10, 8);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (10, 13);
INSERT INTO EXPOSICIONES_X_SEDES VALUES (10, 11);

INSERT INTO EXPOSICIONES_X_RESERVAS VALUES (1, 4);
INSERT INTO EXPOSICIONES_X_RESERVAS VALUES (1, 5);
INSERT INTO EXPOSICIONES_X_RESERVAS VALUES (2, 3);
INSERT INTO EXPOSICIONES_X_RESERVAS VALUES (2, 4);
INSERT INTO EXPOSICIONES_X_RESERVAS VALUES (3, 6);
INSERT INTO EXPOSICIONES_X_RESERVAS VALUES (3, 2);
INSERT INTO EXPOSICIONES_X_RESERVAS VALUES (4, 5);
INSERT INTO EXPOSICIONES_X_RESERVAS VALUES (4, 6);
INSERT INTO EXPOSICIONES_X_RESERVAS VALUES (5, 4);
INSERT INTO EXPOSICIONES_X_RESERVAS VALUES (5, 5);
INSERT INTO EXPOSICIONES_X_RESERVAS VALUES (6, 2);
INSERT INTO EXPOSICIONES_X_RESERVAS VALUES (6, 3);
INSERT INTO EXPOSICIONES_X_RESERVAS VALUES (7, 1);
INSERT INTO EXPOSICIONES_X_RESERVAS VALUES (7, 2);
INSERT INTO EXPOSICIONES_X_RESERVAS VALUES (8, 6);
INSERT INTO EXPOSICIONES_X_RESERVAS VALUES (8, 1);

--INSERT INTO ASIGNACIONES_DE_GUIA (id_reserva, id_guia, fecha_hora_inicio, fecha_hora_fin)
--VALUES (1,8,'2020-05-01 13:25:15','2020-05-01 15:25:15');
--INSERT INTO ASIGNACIONES_DE_GUIA (id_reserva, id_guia, fecha_hora_inicio, fecha_hora_fin)
--VALUES (2,9,'2020-06-07 14:00:15','2020-06-07 15:50:15');
--INSERT INTO ASIGNACIONES_DE_GUIA (id_reserva, id_guia, fecha_hora_inicio, fecha_hora_fin)
--VALUES (3,10,'2020-02-04 17:30:20','2020-02-04 19:30:15');
--INSERT INTO ASIGNACIONES_DE_GUIA (id_reserva, id_guia, fecha_hora_inicio, fecha_hora_fin)
--VALUES (4,8,'2020-07-09 15:30:15','2020-07-09 18:30:15');
--INSERT INTO ASIGNACIONES_DE_GUIA (id_reserva, id_guia, fecha_hora_inicio, fecha_hora_fin)
--VALUES (5,9,'2020-03-01 17:10:10','2020-03-01 19:30:10');
--INSERT INTO ASIGNACIONES_DE_GUIA (id_reserva, id_guia, fecha_hora_inicio, fecha_hora_fin)
--VALUES (6,10,'2020-04-03 12:15:00','2020-04-03 13:25:30');
--INSERT INTO ASIGNACIONES_DE_GUIA (id_reserva, id_guia, fecha_hora_inicio, fecha_hora_fin)
--VALUES (7,8,'2020-07-09 11:45:05','2020-07-09 16:30:30');
--INSERT INTO ASIGNACIONES_DE_GUIA (id_reserva, id_guia, fecha_hora_inicio, fecha_hora_fin)
--VALUES (8,9,'2020-06-07 14:50:25','2020-06-07 17:50:25');
--INSERT INTO ASIGNACIONES_DE_GUIA (id_reserva, id_guia, fecha_hora_inicio, fecha_hora_fin)
--VALUES (8,10,'2020-08-05 15:50:00','2020-08-05 18:50:15');
--INSERT INTO ASIGNACIONES_DE_GUIA (id_reserva, id_guia, fecha_hora_inicio, fecha_hora_fin)
--VALUES (5,8,'2020-07-01 12:35:00','2020-07-01 13:25:35');

--INSERT INTO CAMBIOS_DE_ESTADO_DE_RESERVA
--VALUES (1, 3, '2021-06-06 10:00:00', '2021-06-06 10:30:00'),
--(2, 6, '2021-10-01 19:00:00', '2021-10-12 21:00:00'),
--(3, 2, '2020-08-12 08:00:00', '2020-08-24 13:45:00'),
--(4, 5, '2021-12-15 13:30:00', '2021-12-25 18:30:00'),
--(5, 1, '2020-03-03 07:45:00', '2020-03-21 15:30:00'),
--(6, 4, '2021-06-21 10:00:00', '2021-07-21 07:00:00'),
--(7, 5, '2021-01-10 15:30:00', '2021-01-10 16:55:00'),
--(8, 2, '2021-10-06 17:00:00', '2021-10-10 10:30:00'),
--(3, 5, '2020-08-25 07:30:00', '2020-08-29 12:00:00'),
--(7, 6, '2021-01-11 10:00:00','2021-01-11 20:30:00');

------

CREATE TABLE ASIGNACIONES_DE_GUIA (
	id_asignacion INT IDENTITY,
	id_guia INT,
    fecha_hora_inicio DATETIME NOT NULL,
    fecha_hora_fin DATETIME NOT NULL,
    CONSTRAINT asignaciones_guia_id_asignacion_pk PRIMARY KEY(id_asignacion),
	CONSTRAINT asig_guia_id_guia_fk FOREIGN KEY(id_guia) REFERENCES EMPLEADOS(id_empleado)
        ON UPDATE CASCADE ON DELETE CASCADE,
);

CREATE TABLE ASIGNACIONES_DE_GUIA_X_RESERVA (
	id_reserva INT,
	id_asignacion INT,
	CONSTRAINT asig_guia_x_reserva_id_res_id_asig_pk PRIMARY KEY(id_reserva, id_asignacion),
	CONSTRAINT asig_guia_x_reserva_id_reserva_fk FOREIGN KEY(id_reserva) REFERENCES RESERVAS_DE_VISITA(id_reserva)
        ON UPDATE NO ACTION ON DELETE CASCADE,
	CONSTRAINT asig_guia_x_reserva_id_asig_fk FOREIGN KEY(id_asignacion) REFERENCES ASIGNACIONES_DE_GUIA(id_asignacion)
		ON UPDATE NO ACTION ON DELETE CASCADE,
);

CREATE TABLE CAMBIOS_DE_ESTADO (
	id_cambio_de_estado INT IDENTITY,
    id_estado_reserva TINYINT NOT NULL,
    fecha_hora_inicio DATETIME NOT NULL,
    fecha_hora_fin DATETIME,
    CONSTRAINT cambios_de_estado_id_cambio_estado_pk PRIMARY KEY(id_cambio_de_estado),
    CONSTRAINT cambios_de_estado_res_id_est_fk FOREIGN KEY(id_estado_reserva) REFERENCES ESTADOS_DE_RESERVA(id_estado_reserva)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE CAMBIOS_DE_ESTADO_DE_RESERVAS (
	id_reserva INT,
	id_cambio_de_estado INT,
	CONSTRAINT cambios_estado_x_reserva_id_res_id_cambio_pk PRIMARY KEY(id_reserva, id_cambio_de_estado),
	CONSTRAINT cambios_estado_x_reserva_id_reserva_fk FOREIGN KEY(id_reserva) REFERENCES RESERVAS_DE_VISITA(id_reserva)
        ON UPDATE NO ACTION ON DELETE CASCADE,
	CONSTRAINT cambios_de_estado_x_reserva_id_cambio_fk FOREIGN KEY(id_cambio_de_estado) REFERENCES CAMBIOS_DE_ESTADO(id_cambio_de_estado)
		ON UPDATE NO ACTION ON DELETE CASCADE,
);

INSERT INTO CAMBIOS_DE_ESTADO (id_estado_reserva, fecha_hora_inicio, fecha_hora_fin) VALUES
(3, '2021-06-06 10:00:00', '2021-06-06 10:30:00'),
(6, '2021-10-01 19:00:00', '2021-10-12 21:00:00'),
(2, '2020-08-12 08:00:00', '2020-08-24 13:45:00'),
(5, '2021-12-15 13:30:00', '2021-12-25 18:30:00'),
(1, '2020-03-03 07:45:00', '2020-03-21 15:30:00'),
(4, '2021-06-21 10:00:00', '2021-07-21 07:00:00'),
(5, '2021-01-10 15:30:00', '2021-01-10 16:55:00'),
(2, '2021-10-06 17:00:00', '2021-10-10 10:30:00'),
(5, '2020-08-25 07:30:00', '2020-08-29 12:00:00'),
(6, '2021-01-11 10:00:00','2021-01-11 20:30:00');

INSERT INTO CAMBIOS_DE_ESTADO_DE_RESERVAS VALUES
(1, 1), (2,2), (3,3), (4,4), (5,5), (6,6), (7,7), (8,8), (1,9), (9,10);

INSERT INTO ASIGNACIONES_DE_GUIA (id_guia, fecha_hora_inicio, fecha_hora_fin)
VALUES (8,'2020-05-01 13:25:15','2020-05-01 15:25:15');
INSERT INTO ASIGNACIONES_DE_GUIA (id_guia, fecha_hora_inicio, fecha_hora_fin)
VALUES (9,'2020-06-07 14:00:15','2020-06-07 15:50:15');
INSERT INTO ASIGNACIONES_DE_GUIA (id_guia, fecha_hora_inicio, fecha_hora_fin)
VALUES (10,'2020-02-04 17:30:20','2020-02-04 19:30:15');
INSERT INTO ASIGNACIONES_DE_GUIA (id_guia, fecha_hora_inicio, fecha_hora_fin)
VALUES (8,'2020-07-09 15:30:15','2020-07-09 18:30:15');
INSERT INTO ASIGNACIONES_DE_GUIA (id_guia, fecha_hora_inicio, fecha_hora_fin)
VALUES (9,'2020-03-01 17:10:10','2020-03-01 19:30:10');
INSERT INTO ASIGNACIONES_DE_GUIA (id_guia, fecha_hora_inicio, fecha_hora_fin)
VALUES (10,'2020-04-03 12:15:00','2020-04-03 13:25:30');
INSERT INTO ASIGNACIONES_DE_GUIA (id_guia, fecha_hora_inicio, fecha_hora_fin)
VALUES (8,'2020-07-09 11:45:05','2020-07-09 16:30:30');
INSERT INTO ASIGNACIONES_DE_GUIA (id_guia, fecha_hora_inicio, fecha_hora_fin)
VALUES (9,'2020-06-07 14:50:25','2020-06-07 17:50:25');
INSERT INTO ASIGNACIONES_DE_GUIA (id_guia, fecha_hora_inicio, fecha_hora_fin)
VALUES (10,'2020-08-05 15:50:00','2020-08-05 18:50:15');
INSERT INTO ASIGNACIONES_DE_GUIA (id_guia, fecha_hora_inicio, fecha_hora_fin)
VALUES (8,'2020-07-01 12:35:00','2020-07-01 13:25:35');

INSERT INTO ASIGNACIONES_DE_GUIA_X_RESERVA VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8), (9, 9), (8, 10);


--CREATE TABLE CAMBIOS_DE_ESTADO_DE_RESERVA (
--	id_cambio_de_estado INT IDENTITY,
--    id_reserva INT,
--    id_estado_reserva TINYINT NOT NULL,
--    fecha_hora_inicio DATETIME NOT NULL,
--    fecha_hora_fin DATETIME,
--    CONSTRAINT cambios_de_estado_res_id_cambio_de_estado_pk PRIMARY KEY(id_cambio_de_estado),
--    CONSTRAINT cambios_de_estado_res_id_reserva_fk FOREIGN KEY(id_reserva) REFERENCES RESERVAS_DE_VISITA(id_reserva)
--        ON UPDATE CASCADE ON DELETE CASCADE,
--    CONSTRAINT cambios_de_estado_res_id_estado_fk FOREIGN KEY(id_estado_reserva) REFERENCES ESTADOS_DE_RESERVA(id_estado_reserva)
--        ON UPDATE CASCADE ON DELETE CASCADE
--);


--INSERT INTO CAMBIOS_DE_ESTADO_DE_RESERVA (id_reserva, id_estado_reserva, fecha_hora_inicio, fecha_hora_fin)
--VALUES (1, 3, '2021-06-06 10:00:00', '2021-06-06 10:30:00'),
--(2, 6, '2021-10-01 19:00:00', '2021-10-12 21:00:00'),
--(3, 2, '2020-08-12 08:00:00', '2020-08-24 13:45:00'),
--(4, 5, '2021-12-15 13:30:00', '2021-12-25 18:30:00'),
--(5, 1, '2020-03-03 07:45:00', '2020-03-21 15:30:00'),
--(6, 4, '2021-06-21 10:00:00', '2021-07-21 07:00:00'),
--(7, 5, '2021-01-10 15:30:00', '2021-01-10 16:55:00'),
--(8, 2, '2021-10-06 17:00:00', '2021-10-10 10:30:00'),
--(3, 5, '2020-08-25 07:30:00', '2020-08-29 12:00:00'),
--(7, 6, '2021-01-11 10:00:00','2021-01-11 20:30:00');

