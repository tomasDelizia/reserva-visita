CREATE DATABASE museo_pictorico;

USE museo_pictorico;

DBCC CHECKIDENT ('ESCUELAS', RESEED, 0);	-- Resetear contador de id de una tabla

CREATE TABLE CARGOS (
	id_cargo TINYINT IDENTITY,
	nombre VARCHAR(50) NOT NULL,
	descripcion VARCHAR(MAX),
	CONSTRAINT cargos_id_cargo_pk PRIMARY KEY(id_cargo));

INSERT INTO CARGOS (nombre, descripcion) VALUES('Responsable de Obras', N'Persona responsable de registrar la adquisición de las Obras, y la parametrización de la información necesaria para su administración.');
INSERT INTO CARGOS (nombre, descripcion) VALUES('Responsable de Infraestructura', N'Persona responsable de la registración de las sedes y su estructura física.');
INSERT INTO CARGOS (nombre, descripcion) VALUES(N'Responsable de Préstamos', N'Responsable de gestionar los préstamos de Obras tanto de Artistas como a otros Museos.');
INSERT INTO CARGOS (nombre, descripcion) VALUES('Administrador de Exposiciones', 'Responsable de gestionar las exposiciones de las Obras en cada sede.');
INSERT INTO CARGOS (nombre, descripcion) VALUES('Responsable de Visitas', N'Persona responsable de la gestión de las vistas programadas y de reservas de estas.');
INSERT INTO CARGOS (nombre, descripcion) VALUES('Responsable de Ventas', N'Encargado de las ventas de entradas y de la parametrización de la información necesaria para dicha gestión.');
INSERT INTO CARGOS (nombre, descripcion) VALUES('Administrador de RR HH', N'Encargado de la administración de información de los empleados.');
INSERT INTO CARGOS (nombre, descripcion) VALUES(N'Guía', N'Encargado de explicar una por una las Obras durante un recorrido que haya contratado su servicio.');


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

INSERT INTO TIPOS_DE_VISITA (nombre) VALUES ('Completa');
INSERT INTO TIPOS_DE_VISITA (nombre) VALUES (N'Por Exposición');


CREATE TABLE EMPLEADOS (
    id_empleado INT IDENTITY,
    dni INT NOT NULL,
    cuit INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    mail VARCHAR(50),
    id_cargo TINYINT,
    id_sede INT,
    fecha_nacimiento DATE,
    fecha_ingreso DATE,
    calle_nombre VARCHAR(50),
    calle_numero INT,
    telefono VARCHAR(30),
    id_sexo TINYINT,
    codigo_validacion VARCHAR(50),
    CONSTRAINT empleados_id_empleado_pk PRIMARY KEY(id_empleado),
    CONSTRAINT empleados_dni_uq UNIQUE(dni),
    CONSTRAINT empleados_cuit_uq UNIQUE(cuit)
);

CREATE TABLE SEXOS (
    id_sexo TINYINT IDENTITY,
    nombre VARCHAR(50) NOT NULL,
    CONSTRAINT sexos_id_sexo_pk PRIMARY KEY(id_sexo)
);

INSERT INTO SEXOS (nombre) VALUES ('Masculino');
INSERT INTO SEXOS (nombre) VALUES ('Femenino');

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
    cantidad_maxima_visitantes INT,
    cantidad_maxima_por_guia INT,
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
    id_tipo_exposicion TINYINT,
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

INSERT INTO TIPOS_DE_EXPOSICION (nombre, descripcion) VALUES ('Permanente',
                                                              N'Tienen vigencia durante varios meses y pueden ser visitadas dentro de los horarios de atención de cada sede.');
INSERT INTO TIPOS_DE_EXPOSICION (nombre, descripcion) VALUES ('Temporal',
                                                              N'Suelen organizarse en fechas cercanas a ciertos acontecimientos de importancia general (por ejemplo, fechas patrias, día de la mujer, día del niño, etc.), tienen vigencia durante pocos días y pueden ser visitadas sólo en determinados horarios (definidos específicamente para cada exposición).');

ALTER TABLE EXPOSICIONES
    ADD CONSTRAINT exposiciones_id_empleado_fk FOREIGN KEY(id_empleado_creador)
        REFERENCES EMPLEADOS(id_empleado)
        ON UPDATE CASCADE
        ON DELETE SET NULL;

ALTER TABLE EXPOSICIONES
    ADD CONSTRAINT exposiciones_id_tipo_expo_fk FOREIGN KEY(id_tipo_exposicion)
        REFERENCES TIPOS_DE_EXPOSICION(id_tipo_exposicion)
        ON UPDATE CASCADE
        ON DELETE SET NULL;

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
    id_empleado_creador INT,
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
		ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE DETALLES_DE_EXPOSICION
	ADD CONSTRAINT dt_expo_id_expo_fk FOREIGN KEY(id_exposicion) REFERENCES EXPOSICIONES(id_exposicion)
		ON UPDATE NO ACTION ON DELETE CASCADE;


CREATE TABLE ARTISTAS (
    id_artista INT IDENTITY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    mail VARCHAR(50),
    pseudonimo VARCHAR(50),
    telefono VARCHAR(30),
    id_sexo TINYINT,
    CONSTRAINT artistas_id_artista_pk PRIMARY KEY(id_artista)
);

ALTER TABLE ARTISTAS
    ADD CONSTRAINT artistas_id_sexo_fk FOREIGN KEY(id_sexo)
        REFERENCES SEXOS(id_sexo)
        ON UPDATE CASCADE
        ON DELETE SET NULL;

CREATE TABLE ESTILOS (
    id_estilo TINYINT IDENTITY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(MAX),
    CONSTRAINT estilos_id_estilo_pk PRIMARY KEY(id_estilo)
);

CREATE TABLE TECNICAS (
    id_tecnica TINYINT IDENTITY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(MAX),
    CONSTRAINT tecnicas_id_tecnica_pk PRIMARY KEY(id_tecnica)
);

CREATE TABLE TEMATICAS (
    id_tematica TINYINT IDENTITY,
    nombre VARCHAR(50) NOT NULL,
    CONSTRAINT tematicas_id_tematica_pk PRIMARY KEY(id_tematica)
);

ALTER TABLE OBRAS
    ADD CONSTRAINT obras_id_artista_fk FOREIGN KEY(id_artista)
        REFERENCES ARTISTAS(id_artista)
        ON UPDATE CASCADE
        ON DELETE SET NULL;

ALTER TABLE OBRAS
    ADD CONSTRAINT obras_id_empleado_creador_fk FOREIGN KEY(id_empleado_creador)
        REFERENCES EMPLEADOS(id_empleado)
        ON UPDATE NO ACTION
        ON DELETE SET NULL;

ALTER TABLE OBRAS
    ADD CONSTRAINT obras_id_estilo_fk FOREIGN KEY(id_estilo)
        REFERENCES ESTILOS(id_estilo)
        ON UPDATE CASCADE
        ON DELETE SET NULL;

ALTER TABLE OBRAS
    ADD CONSTRAINT obras_id_tecnica_fk FOREIGN KEY(id_tecnica)
        REFERENCES TECNICAS(id_tecnica)
        ON UPDATE CASCADE
        ON DELETE SET NULL;

ALTER TABLE OBRAS
    ADD CONSTRAINT obras_id_tematica_fk FOREIGN KEY(id_tematica)
        REFERENCES TEMATICAS(id_tematica)
        ON UPDATE CASCADE
        ON DELETE SET NULL;

CREATE TABLE ARCHIVOS (
    id_archivo INT,
    descripcion VARCHAR(50),
    ubicacion VARCHAR(200) NOT NULL,
    CONSTRAINT archivos_id_archivo_pk PRIMARY KEY(id_archivo)
);

CREATE TABLE ARCHIVOS_X_OBRAS (
    id_obra INT,
    id_archivo INT,
    CONSTRAINT arch_x_obras_id_obra_id_arch_pk PRIMARY KEY(id_obra, id_archivo),
    CONSTRAINT arch_x_obras_id_obra_fk FOREIGN KEY(id_obra) REFERENCES OBRAS(id_obra)
        ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT arch_x_obras_id_archivo_fk FOREIGN KEY(id_archivo) REFERENCES ARCHIVOS(id_archivo)
        ON UPDATE NO ACTION ON DELETE CASCADE,
);

CREATE TABLE PUBLICOS_DESTINO (
    id_publico INT,
    nombre VARCHAR(50) NOT NULL,
    caracteristicas VARCHAR(MAX),
    CONSTRAINT publicos_destino_id_publico_pk PRIMARY KEY(id_publico)
);

CREATE TABLE PUBLICOS_X_EXPOSICIONES (
    id_exposicion INT,
    id_publico INT,
    CONSTRAINT publi_x_expo_id_expo_id_publi_pk PRIMARY KEY(id_exposicion, id_publico),
    CONSTRAINT publi_x_expo_id_expo_fk FOREIGN KEY(id_exposicion) REFERENCES EXPOSICIONES(id_exposicion)
        ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT publi_x_expo_id_publi_fk FOREIGN KEY(id_publico) REFERENCES PUBLICOS_DESTINO(id_publico)
        ON UPDATE NO ACTION ON DELETE CASCADE,
);

CREATE TABLE USUARIOS (
    id_usuario INT IDENTITY,
    nombre_usuario VARCHAR(50) NOT NULL,
    contrasena VARCHAR(50) NOT NULL,
    caducidad DATE,
    id_empleado INT,
    CONSTRAINT usuarios_id_usuario_pk PRIMARY KEY(id_usuario),
    CONSTRAINT usuarios_id_empleado_fk FOREIGN KEY(id_empleado) REFERENCES EMPLEADOS(id_empleado)
        ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE SESIONES (
    id_usuario INT,
    fecha_hora_inicio DATETIME,
    fecha_hora_fin DATETIME,
    CONSTRAINT sesiones_id_usuario_fecha_hora_inicio_pk PRIMARY KEY(id_usuario, fecha_hora_inicio),
    CONSTRAINT sesiones_id_usuario_fk FOREIGN KEY(id_usuario) REFERENCES USUARIOS(id_usuario)
        ON UPDATE NO ACTION ON DELETE CASCADE,
);

CREATE TABLE DIAS_DE_SEMANA (
    id_dia TINYINT IDENTITY,
    nombre VARCHAR(10) NOT NULL,
    CONSTRAINT dias_de_semana_id_dia_pk PRIMARY KEY(id_dia)
);

INSERT INTO DIAS_DE_SEMANA (nombre) VALUES ('Lunes');
INSERT INTO DIAS_DE_SEMANA (nombre) VALUES ('Martes');
INSERT INTO DIAS_DE_SEMANA (nombre) VALUES (N'Miércoles');
INSERT INTO DIAS_DE_SEMANA (nombre) VALUES ('Jueves');
INSERT INTO DIAS_DE_SEMANA (nombre) VALUES ('Viernes');
INSERT INTO DIAS_DE_SEMANA (nombre) VALUES (N'Sábado');
INSERT INTO DIAS_DE_SEMANA (nombre) VALUES ('Domingo');

CREATE TABLE HORARIOS_EMPLEADOS (
    id_empleado INT,
    id_dia TINYINT,
    horario_ingreso TIME,
    horario_salida TIME,
    CONSTRAINT horarios_empleados_id_emp_id_dia_pk PRIMARY KEY(id_empleado, id_dia),
    CONSTRAINT horarios_empleados_id_emp_fk FOREIGN KEY(id_empleado) REFERENCES EMPLEADOS(id_empleado)
        ON UPDATE NO ACTION ON DELETE CASCADE,
	CONSTRAINT horarios_empleados_id_dia_fk FOREIGN KEY(id_dia) REFERENCES DIAS_DE_SEMANA(id_dia)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE HORARIOS_SEDE (
    id_sede INT,
    id_dia TINYINT,
    horario_apertura TIME,
    horario_cierre TIME,
    CONSTRAINT horarios_sedes_id_sede_id_dia_pk PRIMARY KEY(id_sede, id_dia),
    CONSTRAINT horarios_sedes_id_sede_fk FOREIGN KEY(id_sede) REFERENCES SEDES(id_sede)
        ON UPDATE NO ACTION ON DELETE CASCADE,
	CONSTRAINT horarios_sedes_id_dia_fk FOREIGN KEY(id_dia) REFERENCES DIAS_DE_SEMANA(id_dia)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE RESERVAS_DE_VISITA (
    id_reserva INT IDENTITY,
    cantidad_alumnos INT,
    cantidad_alumnos_confirmada INT,
    fecha_hora_creacion DATETIME,
    fecha_hora_reserva DATETIME,
    duracion_estimada TIME,
    hora_inicio_real TIME,
    hora_fin_real TIME,
    id_escuela INT,
    id_sede INT,
    id_empleado_creador INT,
    CONSTRAINT reservas_de_visita_id_reserva_pk PRIMARY KEY(id_reserva),
    CONSTRAINT reservas_de_visita_id_escuela_fk FOREIGN KEY(id_escuela) REFERENCES ESCUELAS(id_escuela)
        ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT reservas_de_visita_id_sede_fk FOREIGN KEY(id_sede) REFERENCES SEDES(id_sede)
        ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT reservas_de_visita_id_empleado_creador_fk FOREIGN KEY(id_empleado_creador)
        REFERENCES EMPLEADOS(id_empleado) ON UPDATE NO ACTION ON DELETE SET NULL,
);


CREATE TABLE ASIGNACIONES_DE_GUIA (
    id_reserva INT,
	id_guia INT,
    fecha_hora_inicio DATETIME,
    fecha_hora_fin DATETIME,
    CONSTRAINT asig_guia_id_reserva_id_guia_pk PRIMARY KEY(id_reserva, id_guia),
    CONSTRAINT asig_guia_id_reserva_fk FOREIGN KEY(id_reserva) REFERENCES RESERVAS_DE_VISITA(id_reserva)
        ON UPDATE NO ACTION ON DELETE CASCADE,
	CONSTRAINT asig_guia_id_guia_fk FOREIGN KEY(id_guia) REFERENCES EMPLEADOS(id_empleado)
        ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE ESTADOS_DE_RESERVA (
    id_estado_reserva TINYINT IDENTITY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(MAX),
    CONSTRAINT estados_de_reserva_id_estado_reserva_pk PRIMARY KEY(id_estado_reserva)
);

INSERT INTO ESTADOS_DE_RESERVA (nombre, descripcion) VALUES (N'Pendiente de Confirmación', 'Estado que se asigna incialmente cuando la escuela efectua una reserva.');
INSERT INTO ESTADOS_DE_RESERVA (nombre, descripcion) VALUES ('Escuela Notificada', N'Estado que se asgina de no confirmar la reserva una semana antes de la fecha programada, al enviar automáticamente un recordatorio a la escuela mediante e-mail y WhatsApp.');
INSERT INTO ESTADOS_DE_RESERVA (nombre, descripcion) VALUES ('Cancelada', 'Estado que se asigna cuando la escuela decide cancelar una reserva, mientras no haya sido anulada o se haya realizado la visita asociada.');
INSERT INTO ESTADOS_DE_RESERVA (nombre, descripcion) VALUES ('Anulada', 'Estado que se asigna cuando la escuela no confirma la reserva, a pesar de haber sido notificada previamente.');
INSERT INTO ESTADOS_DE_RESERVA (nombre, descripcion) VALUES ('Confirmada', 'Estado que se asigna cuando la escuela confirma la reserva mediante e-mail o Whatsapp');
INSERT INTO ESTADOS_DE_RESERVA (nombre, descripcion) VALUES ('Visita Realizada', 'Estado que se asigna cuando finalmente se lleva a cabo la reserva de visita solicitada por la escuela');

CREATE TABLE CAMBIOS_DE_ESTADO_DE_RESERVA (
    id_reserva INT,
    id_estado_reserva TINYINT,
    fecha_hora_inicio DATETIME,
    fecha_hora_fin DATETIME,
    CONSTRAINT cambios_de_estado_res_id_res_id_est_fecha_ini_pk PRIMARY KEY(id_reserva, id_estado_reserva, fecha_hora_inicio),
    CONSTRAINT cambios_de_estado_res_id_res_fk FOREIGN KEY(id_reserva) REFERENCES RESERVAS_DE_VISITA(id_reserva)
        ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT cambios_de_estado_res_id_est_fk FOREIGN KEY(id_estado_reserva) REFERENCES ESTADOS_DE_RESERVA(id_estado_reserva)
        ON UPDATE NO ACTION ON DELETE CASCADE
);


-- POBLAMIENTO TABLAS
INSERT INTO ESCUELAS (nombre, mail, calle_nombre, telefono_celular, telefono_fijo)
VALUES (N'Colegio San Pedro Apóstol', 'sanpedro@sanpedroapostol.edu.ar', 'Av. del Piamonte', '3514846584', '4846584'),
       (N'Academia Argüello', 'institucional@aa.edu.ar', N'Pozo de la Loma Esq. Av Rafael Núñez
Argüello', '3513450574', '3450574');

INSERT INTO ESCUELAS (nombre, mail, calle_nombre, calle_numero, telefono_celular, telefono_fijo)
VALUES (N'Colegio Alemán', 'info@colegioalemancba.edu.ar', 'Recta Martinoli Esq. Neper', 6230, '543420834', '3420834');

