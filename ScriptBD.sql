USE [MUSEO_PICTORICO]
GO
/****** Object:  Table [dbo].[EMPLEADOS]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EMPLEADOS](
	[id_empleado] [int] IDENTITY(1,1) NOT NULL,
	[dni] [int] NOT NULL,
	[cuit] [varchar](13) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[apellido] [varchar](50) NOT NULL,
	[mail] [varchar](50) NULL,
	[id_cargo] [tinyint] NULL,
	[id_sede] [int] NULL,
	[fecha_nacimiento] [date] NOT NULL,
	[fecha_ingreso] [date] NOT NULL,
	[calle_nombre] [varchar](50) NULL,
	[calle_numero] [int] NULL,
	[telefono] [varchar](30) NULL,
	[id_sexo] [tinyint] NULL,
	[codigo_validacion] [varchar](50) NULL,
 CONSTRAINT [empleados_id_empleado_pk] PRIMARY KEY CLUSTERED 
(
	[id_empleado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[GUIAS]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[GUIAS] AS
SELECT * FROM EMPLEADOS WHERE id_cargo = 8;
GO
/****** Object:  Table [dbo].[ASIGNACIONES_DE_GUIA]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ASIGNACIONES_DE_GUIA](
	[id_asignacion] [int] IDENTITY(1,1) NOT NULL,
	[id_guia] [int] NULL,
	[fecha_hora_inicio] [datetime] NOT NULL,
	[fecha_hora_fin] [datetime] NOT NULL,
 CONSTRAINT [asignaciones_guia_id_asignacion_pk] PRIMARY KEY CLUSTERED 
(
	[id_asignacion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA](
	[id_reserva] [int] NOT NULL,
	[id_asignacion] [int] NOT NULL,
 CONSTRAINT [asig_guia_x_reserva_id_res_id_asig_pk] PRIMARY KEY CLUSTERED 
(
	[id_reserva] ASC,
	[id_asignacion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CAMBIOS_DE_ESTADO]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CAMBIOS_DE_ESTADO](
	[id_cambio_de_estado] [int] IDENTITY(1,1) NOT NULL,
	[id_estado_reserva] [tinyint] NOT NULL,
	[fecha_hora_inicio] [datetime] NOT NULL,
	[fecha_hora_fin] [datetime] NULL,
 CONSTRAINT [cambios_de_estado_id_cambio_estado_pk] PRIMARY KEY CLUSTERED 
(
	[id_cambio_de_estado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS](
	[id_reserva] [int] NOT NULL,
	[id_cambio_de_estado] [int] NOT NULL,
 CONSTRAINT [cambios_estado_x_reserva_id_res_id_cambio_pk] PRIMARY KEY CLUSTERED 
(
	[id_reserva] ASC,
	[id_cambio_de_estado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CARGOS]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CARGOS](
	[id_cargo] [tinyint] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[descripcion] [varchar](max) NULL,
 CONSTRAINT [cargos_id_cargo_pk] PRIMARY KEY CLUSTERED 
(
	[id_cargo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DETALLES_DE_EXPOSICION]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DETALLES_DE_EXPOSICION](
	[id_exposicion] [int] NOT NULL,
	[id_obra] [int] NOT NULL,
	[lugar_asignado] [int] NULL,
 CONSTRAINT [detalles_expo_id_expo_id_obra_pk] PRIMARY KEY CLUSTERED 
(
	[id_exposicion] ASC,
	[id_obra] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DIAS_DE_SEMANA]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DIAS_DE_SEMANA](
	[id_dia] [tinyint] NOT NULL,
	[nombre] [varchar](10) NOT NULL,
 CONSTRAINT [dias_de_semana_id_dia_pk] PRIMARY KEY CLUSTERED 
(
	[id_dia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ESCUELAS]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ESCUELAS](
	[id_escuela] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[mail] [varchar](50) NULL,
	[calle_nombre] [varchar](50) NULL,
	[calle_numero] [int] NULL,
	[telefono_celular] [varchar](30) NULL,
	[telefono_fijo] [varchar](30) NULL,
 CONSTRAINT [escuelas_id_escuela_pk] PRIMARY KEY CLUSTERED 
(
	[id_escuela] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ESTADOS_DE_RESERVA]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ESTADOS_DE_RESERVA](
	[id_estado_reserva] [tinyint] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[descripcion] [varchar](max) NULL,
 CONSTRAINT [estados_de_reserva_id_estado_reserva_pk] PRIMARY KEY CLUSTERED 
(
	[id_estado_reserva] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EXPOSICIONES]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EXPOSICIONES](
	[id_exposicion] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[id_tipo_exposicion] [tinyint] NOT NULL,
	[fecha_inicio] [date] NULL,
	[fecha_fin] [date] NULL,
	[fecha_inicio_replanificada] [date] NULL,
	[fecha_fin_replanificada] [date] NULL,
	[hora_apertura] [time](7) NULL,
	[hora_cierre] [time](7) NULL,
	[id_empleado_creador] [int] NULL,
 CONSTRAINT [exposiciones_id_exposicion_pk] PRIMARY KEY CLUSTERED 
(
	[id_exposicion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EXPOSICIONES_X_RESERVAS]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EXPOSICIONES_X_RESERVAS](
	[id_reserva] [int] NOT NULL,
	[id_exposicion] [int] NOT NULL,
 CONSTRAINT [expo_x_reserva_id_reserva_id_expo_pk] PRIMARY KEY CLUSTERED 
(
	[id_reserva] ASC,
	[id_exposicion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EXPOSICIONES_X_SEDES]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EXPOSICIONES_X_SEDES](
	[id_sede] [int] NOT NULL,
	[id_exposicion] [int] NOT NULL,
 CONSTRAINT [expo_x_sede_id_sede_id_expo_pk] PRIMARY KEY CLUSTERED 
(
	[id_sede] ASC,
	[id_exposicion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HORARIOS]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HORARIOS](
	[id_horario] [int] IDENTITY(1,1) NOT NULL,
	[hora_inicio] [time](7) NULL,
	[hora_fin] [time](7) NULL,
	[id_dia] [tinyint] NULL,
 CONSTRAINT [horarios_pk] PRIMARY KEY CLUSTERED 
(
	[id_horario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HORARIOS_DE_EMPLEADOS]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HORARIOS_DE_EMPLEADOS](
	[id_empleado] [int] NOT NULL,
	[id_horario] [int] NOT NULL,
 CONSTRAINT [horarios_de_empleados_id_emp_id_horario_pk] PRIMARY KEY CLUSTERED 
(
	[id_empleado] ASC,
	[id_horario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HORARIOS_DE_SEDES]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HORARIOS_DE_SEDES](
	[id_sede] [int] NOT NULL,
	[id_horario] [int] NOT NULL,
 CONSTRAINT [horarios_de_sedes_id_sede_id_horario_pk] PRIMARY KEY CLUSTERED 
(
	[id_sede] ASC,
	[id_horario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OBRAS]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OBRAS](
	[id_obra] [int] IDENTITY(1,1) NOT NULL,
	[nombre_obra] [varchar](50) NOT NULL,
	[id_artista] [int] NULL,
	[alto] [decimal](4, 2) NULL,
	[ancho] [decimal](4, 2) NULL,
	[peso] [decimal](6, 2) NULL,
	[valuacion] [decimal](11, 2) NULL,
	[duracion_resumida] [time](7) NULL,
	[duracion_extendida] [time](7) NULL,
	[fecha_creacion] [date] NULL,
	[fecha_primer_ingreso] [date] NULL,
	[fecha_registracion] [date] NULL,
	[id_estilo] [tinyint] NULL,
	[id_tecnica] [tinyint] NULL,
	[id_tematica] [tinyint] NULL,
	[descripcion] [varchar](max) NULL,
	[codigo_sensor] [int] NULL,
	[id_empleado_creador] [int] NOT NULL,
 CONSTRAINT [obras_id_obra_pk] PRIMARY KEY CLUSTERED 
(
	[id_obra] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PUBLICOS_DESTINO]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PUBLICOS_DESTINO](
	[id_publico] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[caracteristicas] [varchar](max) NULL,
 CONSTRAINT [publicos_destino_id_publico_pk] PRIMARY KEY CLUSTERED 
(
	[id_publico] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PUBLICOS_X_EXPOSICIONES]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PUBLICOS_X_EXPOSICIONES](
	[id_exposicion] [int] NOT NULL,
	[id_publico] [int] NOT NULL,
 CONSTRAINT [publi_x_expo_id_expo_id_publi_pk] PRIMARY KEY CLUSTERED 
(
	[id_exposicion] ASC,
	[id_publico] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RESERVAS_DE_VISITA]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RESERVAS_DE_VISITA](
	[id_reserva] [int] NOT NULL,
	[cantidad_alumnos] [int] NOT NULL,
	[cantidad_alumnos_confirmada] [int] NULL,
	[fecha_hora_creacion] [datetime] NOT NULL,
	[fecha_hora_reserva] [datetime] NOT NULL,
	[duracion_estimada] [time](7) NOT NULL,
	[hora_inicio_real] [time](7) NULL,
	[hora_fin_real] [time](7) NULL,
	[id_escuela] [int] NOT NULL,
	[id_sede] [int] NOT NULL,
	[id_empleado_creador] [int] NOT NULL,
 CONSTRAINT [reservas_de_visita_id_reserva_pk] PRIMARY KEY CLUSTERED 
(
	[id_reserva] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SEDES]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SEDES](
	[id_sede] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[cantidad_maxima_visitantes] [int] NOT NULL,
	[cantidad_maxima_por_guia] [int] NOT NULL,
	[calle_nombre] [varchar](50) NULL,
	[calle_numero] [int] NULL,
 CONSTRAINT [id_sede_pk] PRIMARY KEY CLUSTERED 
(
	[id_sede] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SESIONES]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SESIONES](
	[id_usuario] [int] NOT NULL,
	[fecha_hora_inicio] [datetime] NOT NULL,
	[fecha_hora_fin] [datetime] NULL,
 CONSTRAINT [sesiones_id_usuario_fecha_hora_inicio_pk] PRIMARY KEY CLUSTERED 
(
	[id_usuario] ASC,
	[fecha_hora_inicio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SEXOS]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SEXOS](
	[id_sexo] [tinyint] NOT NULL,
	[nombre] [varchar](30) NOT NULL,
 CONSTRAINT [sexos_id_sexo_pk] PRIMARY KEY CLUSTERED 
(
	[id_sexo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TIPOS_DE_EXPOSICION]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TIPOS_DE_EXPOSICION](
	[id_tipo_exposicion] [tinyint] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[descripcion] [varchar](max) NULL,
 CONSTRAINT [tipos_de_exposicion_id_tipo_pk] PRIMARY KEY CLUSTERED 
(
	[id_tipo_exposicion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TIPOS_DE_VISITA]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TIPOS_DE_VISITA](
	[id_tipo_visita] [tinyint] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
 CONSTRAINT [tipos_de_visita_id_tipo_visita_pk] PRIMARY KEY CLUSTERED 
(
	[id_tipo_visita] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[USUARIOS]    Script Date: 27/08/2021 8:52:41 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[USUARIOS](
	[id_usuario] [int] IDENTITY(1,1) NOT NULL,
	[nombre_usuario] [varchar](50) NOT NULL,
	[contrasena] [varchar](50) NOT NULL,
	[caducidad] [date] NULL,
	[id_empleado] [int] NOT NULL,
 CONSTRAINT [usuarios_id_usuario_pk] PRIMARY KEY CLUSTERED 
(
	[id_usuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[ASIGNACIONES_DE_GUIA] ON 

INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (1, 8, CAST(N'2020-05-01T13:25:15.000' AS DateTime), CAST(N'2020-05-01T15:25:15.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (2, 9, CAST(N'2020-06-07T14:00:15.000' AS DateTime), CAST(N'2020-06-07T15:50:15.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (3, 10, CAST(N'2020-02-04T17:30:20.000' AS DateTime), CAST(N'2020-02-04T19:30:15.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (4, 8, CAST(N'2020-07-09T15:30:15.000' AS DateTime), CAST(N'2020-07-09T18:30:15.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (5, 9, CAST(N'2020-03-01T17:10:10.000' AS DateTime), CAST(N'2020-03-01T19:30:10.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (6, 10, CAST(N'2020-04-03T12:15:00.000' AS DateTime), CAST(N'2020-04-03T13:25:30.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (7, 8, CAST(N'2020-07-09T11:45:05.000' AS DateTime), CAST(N'2020-07-09T16:30:30.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (8, 9, CAST(N'2020-06-07T14:50:25.000' AS DateTime), CAST(N'2020-06-07T17:50:25.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (9, 10, CAST(N'2020-08-05T15:50:00.000' AS DateTime), CAST(N'2020-08-05T18:50:15.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (10, 8, CAST(N'2020-07-01T12:35:00.000' AS DateTime), CAST(N'2020-07-01T13:25:35.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (11, 19, CAST(N'2021-06-21T16:00:00.000' AS DateTime), CAST(N'2021-06-21T16:55:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (14, 16, CAST(N'2021-06-21T15:30:00.000' AS DateTime), CAST(N'2021-06-21T16:38:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (15, 10, CAST(N'2021-06-21T15:30:00.000' AS DateTime), CAST(N'2021-06-21T16:05:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (16, 12, CAST(N'2021-06-21T15:30:00.000' AS DateTime), CAST(N'2021-06-21T16:05:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (17, 16, CAST(N'2021-06-26T15:00:00.000' AS DateTime), CAST(N'2021-06-26T15:13:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (18, 14, CAST(N'2021-06-24T16:00:00.000' AS DateTime), CAST(N'2021-06-24T16:40:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (22, 16, CAST(N'2021-06-28T16:00:00.000' AS DateTime), CAST(N'2021-06-28T17:08:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (23, 21, CAST(N'2021-06-28T16:00:00.000' AS DateTime), CAST(N'2021-06-28T17:08:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (24, 16, CAST(N'2021-08-30T15:30:00.000' AS DateTime), CAST(N'2021-08-30T16:15:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (25, 16, CAST(N'2021-08-23T15:30:00.000' AS DateTime), CAST(N'2021-08-23T16:15:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (26, 21, CAST(N'2021-08-23T15:30:00.000' AS DateTime), CAST(N'2021-08-23T16:15:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (27, 20, CAST(N'2021-08-26T16:00:00.000' AS DateTime), CAST(N'2021-08-26T16:45:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, 16, CAST(N'2021-08-27T15:30:00.000' AS DateTime), CAST(N'2021-08-27T16:15:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (29, 16, CAST(N'2021-08-26T15:30:00.000' AS DateTime), CAST(N'2021-08-26T16:15:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (30, 14, CAST(N'2021-08-23T15:30:00.000' AS DateTime), CAST(N'2021-08-23T16:50:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (31, 14, CAST(N'2021-08-23T15:00:00.000' AS DateTime), CAST(N'2021-08-23T15:45:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (32, 16, CAST(N'2021-08-24T15:30:00.000' AS DateTime), CAST(N'2021-08-24T16:15:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (33, 16, CAST(N'2021-08-26T15:00:00.000' AS DateTime), CAST(N'2021-08-26T15:45:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (34, 21, CAST(N'2021-09-01T15:00:00.000' AS DateTime), CAST(N'2021-09-01T15:45:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (35, 20, CAST(N'2021-08-24T15:30:00.000' AS DateTime), CAST(N'2021-08-24T16:15:00.000' AS DateTime))
INSERT [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion], [id_guia], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (36, 14, CAST(N'2021-09-02T15:00:00.000' AS DateTime), CAST(N'2021-09-02T15:45:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[ASIGNACIONES_DE_GUIA] OFF
GO
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (1, 1)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (2, 2)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (3, 3)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (4, 4)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (5, 5)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (6, 6)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (7, 7)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (8, 8)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (8, 10)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (9, 9)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (10, 11)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (11, 14)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (12, 15)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (12, 16)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (13, 17)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (14, 18)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (16, 22)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (16, 23)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (17, 24)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (18, 25)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (18, 26)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (19, 27)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (20, 28)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (21, 29)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (22, 30)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (23, 31)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (24, 32)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (25, 33)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (26, 34)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (27, 35)
INSERT [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] ([id_reserva], [id_asignacion]) VALUES (28, 36)
GO
SET IDENTITY_INSERT [dbo].[CAMBIOS_DE_ESTADO] ON 

INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (1, 3, CAST(N'2021-06-06T10:00:00.000' AS DateTime), CAST(N'2021-06-06T10:30:00.000' AS DateTime))
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (2, 6, CAST(N'2021-10-01T19:00:00.000' AS DateTime), CAST(N'2021-10-12T21:00:00.000' AS DateTime))
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (3, 2, CAST(N'2020-08-12T08:00:00.000' AS DateTime), CAST(N'2020-08-24T13:45:00.000' AS DateTime))
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (4, 5, CAST(N'2021-12-15T13:30:00.000' AS DateTime), CAST(N'2021-12-25T18:30:00.000' AS DateTime))
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (5, 1, CAST(N'2020-03-03T07:45:00.000' AS DateTime), CAST(N'2020-03-21T15:30:00.000' AS DateTime))
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (6, 4, CAST(N'2021-06-21T10:00:00.000' AS DateTime), CAST(N'2021-07-21T07:00:00.000' AS DateTime))
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (7, 5, CAST(N'2021-01-10T15:30:00.000' AS DateTime), CAST(N'2021-01-10T16:55:00.000' AS DateTime))
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (8, 2, CAST(N'2021-10-06T17:00:00.000' AS DateTime), CAST(N'2021-10-10T10:30:00.000' AS DateTime))
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (9, 5, CAST(N'2020-08-25T07:30:00.000' AS DateTime), CAST(N'2020-08-29T12:00:00.000' AS DateTime))
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (10, 6, CAST(N'2021-01-11T10:00:00.000' AS DateTime), CAST(N'2021-01-11T20:30:00.000' AS DateTime))
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (11, 1, CAST(N'2021-06-20T09:22:52.557' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (12, 1, CAST(N'2021-06-20T09:26:13.260' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (13, 1, CAST(N'2021-06-20T09:29:06.913' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (14, 1, CAST(N'2021-06-21T17:19:06.183' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (16, 1, CAST(N'2021-06-22T13:11:23.817' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (17, 1, CAST(N'2021-06-22T13:29:36.280' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (18, 1, CAST(N'2021-08-03T17:38:02.177' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (19, 1, CAST(N'2021-08-03T17:44:53.347' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (20, 1, CAST(N'2021-08-12T18:03:31.203' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (21, 1, CAST(N'2021-08-13T11:17:01.290' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (22, 1, CAST(N'2021-08-19T17:19:35.950' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (23, 1, CAST(N'2021-08-21T18:42:20.703' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (24, 1, CAST(N'2021-08-22T06:38:45.150' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (25, 1, CAST(N'2021-08-22T11:09:57.597' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (26, 1, CAST(N'2021-08-22T12:07:34.740' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (27, 1, CAST(N'2021-08-22T12:09:39.190' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, 1, CAST(N'2021-08-24T14:00:04.727' AS DateTime), NULL)
INSERT [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado], [id_estado_reserva], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (29, 1, CAST(N'2021-08-26T11:38:17.090' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[CAMBIOS_DE_ESTADO] OFF
GO
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (1, 1)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (1, 9)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (2, 2)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (3, 3)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (4, 4)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (5, 5)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (6, 6)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (7, 7)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (8, 8)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (9, 10)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (11, 11)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (12, 12)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (13, 13)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (14, 14)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (15, 16)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (16, 17)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (17, 18)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (18, 19)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (19, 20)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (20, 21)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (21, 22)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (22, 23)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (23, 24)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (24, 25)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (25, 26)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (26, 27)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (27, 28)
INSERT [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] ([id_reserva], [id_cambio_de_estado]) VALUES (28, 29)
GO
SET IDENTITY_INSERT [dbo].[CARGOS] ON 

INSERT [dbo].[CARGOS] ([id_cargo], [nombre], [descripcion]) VALUES (1, N'Responsable de Obras', N'Persona responsable de registrar la adquisición de las Obras, y la parametrización de la información necesaria para su administración.')
INSERT [dbo].[CARGOS] ([id_cargo], [nombre], [descripcion]) VALUES (2, N'Responsable de Infraestructura', N'Persona responsable de la registración de las sedes y su estructura física.')
INSERT [dbo].[CARGOS] ([id_cargo], [nombre], [descripcion]) VALUES (3, N'Responsable de Préstamos', N'Responsable de gestionar los préstamos de Obras tanto de Artistas como a otros Museos.')
INSERT [dbo].[CARGOS] ([id_cargo], [nombre], [descripcion]) VALUES (4, N'Administrador de Exposiciones', N'Responsable de gestionar las exposiciones de las Obras en cada sede.')
INSERT [dbo].[CARGOS] ([id_cargo], [nombre], [descripcion]) VALUES (5, N'Responsable de Visitas', N'Persona responsable de la gestión de las vistas programadas y de reservas de estas.')
INSERT [dbo].[CARGOS] ([id_cargo], [nombre], [descripcion]) VALUES (6, N'Responsable de Ventas', N'Encargado de las ventas de entradas y de la parametrización de la información necesaria para dicha gestión.')
INSERT [dbo].[CARGOS] ([id_cargo], [nombre], [descripcion]) VALUES (7, N'Administrador de RR HH', N'Encargado de la administración de información de los empleados.')
INSERT [dbo].[CARGOS] ([id_cargo], [nombre], [descripcion]) VALUES (8, N'Guía', N'Encargado de explicar una por una las Obras durante un recorrido que haya contratado su servicio.')
SET IDENTITY_INSERT [dbo].[CARGOS] OFF
GO
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (1, 1, 1)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (2, 1, 1)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (2, 2, 3)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (2, 3, 1)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (3, 5, 2)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (3, 7, 4)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (4, 2, 2)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (5, 3, 2)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (5, 4, 5)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (6, 3, 3)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (7, 4, 4)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (8, 5, 5)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (9, 6, 6)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (10, 7, 7)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (11, 8, 8)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (12, 9, 9)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (13, 10, 10)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (14, 1, 10)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (15, 2, 10)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (16, 3, 10)
INSERT [dbo].[DETALLES_DE_EXPOSICION] ([id_exposicion], [id_obra], [lugar_asignado]) VALUES (17, 4, 10)
GO
INSERT [dbo].[DIAS_DE_SEMANA] ([id_dia], [nombre]) VALUES (0, N'LUNES')
INSERT [dbo].[DIAS_DE_SEMANA] ([id_dia], [nombre]) VALUES (1, N'MARTES')
INSERT [dbo].[DIAS_DE_SEMANA] ([id_dia], [nombre]) VALUES (2, N'MIERCOLES')
INSERT [dbo].[DIAS_DE_SEMANA] ([id_dia], [nombre]) VALUES (3, N'JUEVES')
INSERT [dbo].[DIAS_DE_SEMANA] ([id_dia], [nombre]) VALUES (4, N'VIERNES')
INSERT [dbo].[DIAS_DE_SEMANA] ([id_dia], [nombre]) VALUES (5, N'SABADO')
INSERT [dbo].[DIAS_DE_SEMANA] ([id_dia], [nombre]) VALUES (6, N'DOMINGO')
GO
SET IDENTITY_INSERT [dbo].[EMPLEADOS] ON 

INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (1, 39895674, N'23-39895674-3', N'María', N'Robles', N'mrobles@hotmail.com', 1, 1, CAST(N'1995-04-09' AS Date), CAST(N'2010-08-05' AS Date), N'Francisco Aston', 3190, N'3515459874', 1, N'1234')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (2, 40156147, N'23-40156147-4', N'Lautaro', N'Rodriguez', N'lautiro@hotmail.com', 2, 2, CAST(N'1996-12-12' AS Date), CAST(N'2018-01-02' AS Date), N'Felix Olmedo', 4578, N'3513140012', 0, N'5678')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (3, 41256365, N'23-41256365-3', N'Milagros', N'Ramos', N'miliramos@hotmail.com', 3, 3, CAST(N'1997-06-04' AS Date), CAST(N'2016-04-01' AS Date), N'Melincue', 7894, N'3513140165', 1, N'1423')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (4, 42217998, N'23-42217991-3', N'Raúl', N'Gonzalez', N'raulgonzalez@hotmail.com', 4, 4, CAST(N'1998-11-11' AS Date), CAST(N'2021-01-01' AS Date), N'Guemes', 7415, N'351605738', 0, N'3512')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (5, 43512364, N'23-43512364-2', N'Eduardo', N'Suarez', N'edusuarez@hotmail.com', 5, 5, CAST(N'1999-08-06' AS Date), CAST(N'2020-01-05' AS Date), N'Lima', 1452, N'3514023152', 0, N'4521')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (6, 21392420, N'27-21392420-4', N'Agustina', N'Gomez', N'agusgomez@gmail.com', 6, 6, CAST(N'1968-09-05' AS Date), CAST(N'2020-11-11' AS Date), N'General Paz', 1730, N'3517157950', 1, N'1520')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (7, 30520687, N'20-30520687-2', N'Juan', N'Chavez', N'chavezj@hotmail.com', 7, 7, CAST(N'1970-10-10' AS Date), CAST(N'2016-05-08' AS Date), N'Buenos Aires', 625, N'3516652250', 0, N'1568')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (8, 19562852, N'20-19562852-3', N'María Belen', N'López', N'mblopez@hotmail.com', 8, 8, CAST(N'1965-04-02' AS Date), CAST(N'2016-03-05' AS Date), N'Marcelo Lopez', 10, N'3517689456', 1, N'6958')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (9, 41562398, N'27-41562398-4', N'Carla', N'Juarez', N'chavezj@hotmail.com', 8, 9, CAST(N'1998-01-02' AS Date), CAST(N'2019-03-01' AS Date), N'Velez Sarsfield', 658, N'3517852456', 1, N'2589')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (10, 32650897, N'20-32650897-8', N'Mauricio', N'Cuevas', N'mcuevas@gmail.com', 8, 10, CAST(N'1987-05-20' AS Date), CAST(N'2020-02-10' AS Date), N'Independencia', 210, N'3512987562', 0, N'1518')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (11, 43438359, N'20-43438359-6', N'Tomás', N'Delizia', N'tomidelizia@gmail.com', 5, 1, CAST(N'2001-05-29' AS Date), CAST(N'2020-01-01' AS Date), N'Crisol', 62, N'3874535259', 0, N'5538')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (12, 43267361, N'21-43267361-8', N'Mauro', N'Gabrielli', N'maurogab@gmail.com', 8, 10, CAST(N'1987-05-20' AS Date), CAST(N'2020-02-10' AS Date), N'Chacabuco', 210, N'3512987562', 0, N'1518')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (13, 38648797, N'26-38648797-8', N'Sastre', N'Martin', N'sastresatrin@gmail.com', 8, 10, CAST(N'1995-02-20' AS Date), CAST(N'2020-03-20' AS Date), N'Roberto Sisi', 280, N'3512446562', 0, N'1388')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (14, 26659127, N'26659127-8', N'Peter', N'Sanchez', N'peterelsanchez@gmail.com', 8, 6, CAST(N'1997-01-29' AS Date), CAST(N'2021-02-17' AS Date), N'Juan Rodriguez', 212, N'351777989', 0, N'1438')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (15, 29650897, N'19-29650897-8', N'Juan', N'Obolo', N'obolojuan45@gmail.com', 8, 9, CAST(N'1990-09-13' AS Date), CAST(N'2019-03-14' AS Date), N'Ayovi', 330, N'351458125', 0, N'1917')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (16, 43276589, N'25-43276589-8', N'Agustin', N'Limon', N'aguslimontayahoo@gmail.com', 8, 4, CAST(N'2000-05-20' AS Date), CAST(N'2020-08-20' AS Date), N'Independencia', 560, N'351564645', 1, N'4848')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (17, 41256398, N'25-41256398-8', N'Joaquin', N'Lopez', N'elJoacolopez@gmail.com', 8, 8, CAST(N'2001-05-20' AS Date), CAST(N'2021-01-15' AS Date), N'Paraguay', 550, N'3513467895', 0, N'4458')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (18, 32461321, N'25-32461321-8', N'Yenifer', N'Garcia', N'yenigarcia_12@gmail.com', 8, 8, CAST(N'1999-09-30' AS Date), CAST(N'2020-03-05' AS Date), N'Pergamino', 600, N'351787878', 1, N'5562')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (19, 36798420, N'25-36798420-8', N'Maria', N'Casas', N'casas_454@gmail.com', 8, 7, CAST(N'1989-09-30' AS Date), CAST(N'2020-01-23' AS Date), N'Balcarce', 993, N'351787878', 1, N'5562')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (20, 45124578, N'25-45124578-8', N'Ana', N'De la Cruz', N'anitass4@gmail.com', 8, 3, CAST(N'1949-01-10' AS Date), CAST(N'2020-01-23' AS Date), N'Gorriti', 393, N'351236878', 1, N'6662')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (21, 37542651, N'20-37542651-8', N'Cristina', N'Diaz', N'crisdiaz@gmail.com', 8, 4, CAST(N'1974-07-06' AS Date), CAST(N'2020-03-05' AS Date), N'Bodereau', 241, N'3515478562', 1, N'7495')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (22, 37547698, N'20-37547698-8', N'Veronica', N'Rodriguez', N'veror@gmail.com', 8, 1, CAST(N'1980-07-16' AS Date), CAST(N'2021-05-10' AS Date), N'Rondeau', 748, N'3514786325', 1, N'4587')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (23, 35698210, N'20-35698210-8', N'Josefina', N'Gonzalez', N'josegonzalez@gmail.com', 8, 5, CAST(N'1988-09-20' AS Date), CAST(N'2021-07-11' AS Date), N'Menendez Pidal', 520, N'3518749658', 1, N'1111')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (24, 35475963, N'20-35475963-8', N'Eugenia', N'Martinez', N'eugem@gmail.com', 8, 8, CAST(N'1995-09-20' AS Date), CAST(N'2020-08-21' AS Date), N'Roque Funes', 452, N'3514758214', 1, N'1540')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (25, 32650897, N'20-32650897-4', N'Guillermo', N'Gomez', N'guilleg@gmail.com', 8, 3, CAST(N'1987-05-20' AS Date), CAST(N'2020-02-08' AS Date), N'Mariano Larra', 350, N'3512149870', 0, N'1333')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (26, 37564851, N'20-37564851-8', N'Franco', N'Rodriguez', N'rfranco@gmail.com', 8, 7, CAST(N'1991-07-10' AS Date), CAST(N'2018-02-05' AS Date), N'Pablo Buitrago', 748, N'3512568974', 0, N'4852')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (27, 30265984, N'20-30265984-8', N'Agostina', N'Gonzalez', N'agosgonzalez@gmail.com', 8, 4, CAST(N'1995-07-20' AS Date), CAST(N'2019-04-17' AS Date), N'Sagrada Familia', 736, N'3512563348', 1, N'2491')
INSERT [dbo].[EMPLEADOS] ([id_empleado], [dni], [cuit], [nombre], [apellido], [mail], [id_cargo], [id_sede], [fecha_nacimiento], [fecha_ingreso], [calle_nombre], [calle_numero], [telefono], [id_sexo], [codigo_validacion]) VALUES (28, 40512984, N'20-40512984-8', N'Victoria', N'Fernandez', N'vickifernandez@gmail.com', 8, 5, CAST(N'1989-07-06' AS Date), CAST(N'2017-03-10' AS Date), N'Gauss', 748, N'3512568974', 0, N'4852')
SET IDENTITY_INSERT [dbo].[EMPLEADOS] OFF
GO
SET IDENTITY_INSERT [dbo].[ESCUELAS] ON 

INSERT [dbo].[ESCUELAS] ([id_escuela], [nombre], [mail], [calle_nombre], [calle_numero], [telefono_celular], [telefono_fijo]) VALUES (1, N'Colegio San Pedro Apóstol', N'sanpedro@sanpedroapostol.edu.ar', N'Av. del Piamonte', NULL, N'3514846584', N'4846584')
INSERT [dbo].[ESCUELAS] ([id_escuela], [nombre], [mail], [calle_nombre], [calle_numero], [telefono_celular], [telefono_fijo]) VALUES (2, N'Academia Argüello', N'institucional@aa.edu.ar', N'Pozo de la Loma Esq. Av Rafael Núñez
Argüello', NULL, N'3513450574', N'3450574')
INSERT [dbo].[ESCUELAS] ([id_escuela], [nombre], [mail], [calle_nombre], [calle_numero], [telefono_celular], [telefono_fijo]) VALUES (3, N'Colegio Alemán', N'info@colegioalemancba.edu.ar', N'Recta Martinoli Esq. Neper', 6230, N'543420834', N'3420834')
INSERT [dbo].[ESCUELAS] ([id_escuela], [nombre], [mail], [calle_nombre], [calle_numero], [telefono_celular], [telefono_fijo]) VALUES (4, N'Colegio Escuti', N'Escuti@escuti.edu.ar', N'Nazaret', 3399, N'0351 481-5596', N'4815596')
INSERT [dbo].[ESCUELAS] ([id_escuela], [nombre], [mail], [calle_nombre], [calle_numero], [telefono_celular], [telefono_fijo]) VALUES (5, N'Colegio Madres Escolapias', N'Escolapias@escolapias.edu.ar', N'Av.Hugo Wast', 4455, N'0351 481-2418', N'4812418')
INSERT [dbo].[ESCUELAS] ([id_escuela], [nombre], [mail], [calle_nombre], [calle_numero], [telefono_celular], [telefono_fijo]) VALUES (6, N'Colegio Taborin', N'Taborin@taborin.edu.ar', N'Av. Amadeo Sabattini', 359, N'351458009', N'4583522')
INSERT [dbo].[ESCUELAS] ([id_escuela], [nombre], [mail], [calle_nombre], [calle_numero], [telefono_celular], [telefono_fijo]) VALUES (7, N'Colegio Rosarito', N'Rosarito@rosarito.edu.ar', N'Viracocha', 6933, N'03543 44-1373', N'441373')
INSERT [dbo].[ESCUELAS] ([id_escuela], [nombre], [mail], [calle_nombre], [calle_numero], [telefono_celular], [telefono_fijo]) VALUES (8, N'Colegio Peña', N'Peña@peña.edu.ar', N'Cd. de Tampa', 2954, N'0351 480-6054', N'4806054')
INSERT [dbo].[ESCUELAS] ([id_escuela], [nombre], [mail], [calle_nombre], [calle_numero], [telefono_celular], [telefono_fijo]) VALUES (9, N'Colegio La Salle', N'Lasalle@lasalle.edu.ar', N'Av. Recta Martinolli', 6602, N'03543 42-0166', N'420166')
INSERT [dbo].[ESCUELAS] ([id_escuela], [nombre], [mail], [calle_nombre], [calle_numero], [telefono_celular], [telefono_fijo]) VALUES (10, N'Colegio Castel Franco', N'CastelFranco@castelFranco.edu.ar', N'Av.Lasalle', NULL, N'03543 42-1802', N'421802')
INSERT [dbo].[ESCUELAS] ([id_escuela], [nombre], [mail], [calle_nombre], [calle_numero], [telefono_celular], [telefono_fijo]) VALUES (11, N'Colegio Monserrat', N'Monserrat@monserrat.edu.ar', N'Obispo Trejo', 294, N'0351 433-2079', N'4332079')
SET IDENTITY_INSERT [dbo].[ESCUELAS] OFF
GO
SET IDENTITY_INSERT [dbo].[ESTADOS_DE_RESERVA] ON 

INSERT [dbo].[ESTADOS_DE_RESERVA] ([id_estado_reserva], [nombre], [descripcion]) VALUES (1, N'Pendiente de Confirmación', N'Estado que se asigna incialmente cuando la escuela efectua una reserva.')
INSERT [dbo].[ESTADOS_DE_RESERVA] ([id_estado_reserva], [nombre], [descripcion]) VALUES (2, N'Escuela Notificada', N'Estado que se asgina de no confirmar la reserva una semana antes de la fecha programada, al enviar automáticamente un recordatorio a la escuela mediante e-mail y WhatsApp.')
INSERT [dbo].[ESTADOS_DE_RESERVA] ([id_estado_reserva], [nombre], [descripcion]) VALUES (3, N'Cancelada', N'Estado que se asigna cuando la escuela decide cancelar una reserva, mientras no haya sido anulada o se haya realizado la visita asociada.')
INSERT [dbo].[ESTADOS_DE_RESERVA] ([id_estado_reserva], [nombre], [descripcion]) VALUES (4, N'Anulada', N'Estado que se asigna cuando la escuela no confirma la reserva, a pesar de haber sido notificada previamente.')
INSERT [dbo].[ESTADOS_DE_RESERVA] ([id_estado_reserva], [nombre], [descripcion]) VALUES (5, N'Confirmada', N'Estado que se asigna cuando la escuela confirma la reserva mediante e-mail o Whatsapp')
INSERT [dbo].[ESTADOS_DE_RESERVA] ([id_estado_reserva], [nombre], [descripcion]) VALUES (6, N'Cumplimentada', N'Estado que se asigna cuando finalmente se lleva a cabo la reserva de visita solicitada por la escuela')
SET IDENTITY_INSERT [dbo].[ESTADOS_DE_RESERVA] OFF
GO
SET IDENTITY_INSERT [dbo].[EXPOSICIONES] ON 

INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (1, N'Contemporanea', 1, CAST(N'2021-06-01' AS Date), CAST(N'2021-07-26' AS Date), NULL, NULL, CAST(N'08:00:00' AS Time), CAST(N'17:00:00' AS Time), 3)
INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (2, N'Abstracta', 2, CAST(N'2021-05-01' AS Date), CAST(N'2021-09-06' AS Date), NULL, NULL, CAST(N'09:00:00' AS Time), CAST(N'18:00:00' AS Time), 1)
INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (3, N'Rupestre', 2, CAST(N'2021-07-01' AS Date), CAST(N'2021-08-06' AS Date), NULL, NULL, CAST(N'08:00:00' AS Time), CAST(N'16:30:00' AS Time), 2)
INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (4, N'Occidental', 2, CAST(N'2021-05-15' AS Date), CAST(N'2021-06-15' AS Date), CAST(N'2021-06-15' AS Date), CAST(N'2020-08-28' AS Date), CAST(N'10:00:00' AS Time), CAST(N'15:00:00' AS Time), 5)
INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (5, N'Callejero', 1, CAST(N'2021-05-01' AS Date), CAST(N'2021-07-06' AS Date), NULL, NULL, CAST(N'11:00:00' AS Time), CAST(N'16:50:00' AS Time), 3)
INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (6, N'Moderna', 1, CAST(N'2021-07-02' AS Date), CAST(N'2021-07-22' AS Date), NULL, NULL, CAST(N'12:00:00' AS Time), CAST(N'18:40:00' AS Time), 1)
INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (7, N'Oriental', 1, CAST(N'2021-06-12' AS Date), CAST(N'2021-09-12' AS Date), NULL, NULL, CAST(N'10:00:00' AS Time), CAST(N'13:30:00' AS Time), 2)
INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (8, N'El atajo', 2, CAST(N'2021-05-02' AS Date), CAST(N'2021-06-08' AS Date), CAST(N'2021-06-08' AS Date), CAST(N'2021-06-30' AS Date), CAST(N'08:20:00' AS Time), CAST(N'12:00:00' AS Time), 6)
INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (9, N'En el malba', 1, CAST(N'2021-06-12' AS Date), CAST(N'2021-07-08' AS Date), NULL, NULL, CAST(N'14:00:00' AS Time), CAST(N'19:00:00' AS Time), 7)
INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (10, N'Insurrectas', 2, CAST(N'2021-05-02' AS Date), CAST(N'2021-06-28' AS Date), NULL, NULL, CAST(N'08:00:00' AS Time), CAST(N'13:00:00' AS Time), 3)
INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (11, N'A traves del cristal', 1, CAST(N'2021-05-02' AS Date), CAST(N'2021-06-02' AS Date), CAST(N'2021-06-02' AS Date), CAST(N'2020-08-30' AS Date), CAST(N'10:00:00' AS Time), CAST(N'16:20:00' AS Time), 2)
INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (12, N'Guerra fria', 2, CAST(N'2021-04-02' AS Date), CAST(N'2021-06-10' AS Date), CAST(N'2021-06-10' AS Date), CAST(N'2021-07-03' AS Date), CAST(N'12:00:00' AS Time), CAST(N'17:30:00' AS Time), 1)
INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (13, N'UFO', 2, CAST(N'2021-08-02' AS Date), CAST(N'2021-08-25' AS Date), NULL, NULL, CAST(N'08:30:00' AS Time), CAST(N'15:30:00' AS Time), 4)
INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (14, N'La hora azul', 1, CAST(N'2021-01-01' AS Date), CAST(N'2021-04-06' AS Date), CAST(N'2021-04-06' AS Date), CAST(N'2021-06-30' AS Date), CAST(N'08:00:00' AS Time), CAST(N'18:30:00' AS Time), 4)
INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (15, N'Armonias de Color', 2, CAST(N'2021-05-20' AS Date), CAST(N'2021-10-25' AS Date), NULL, NULL, CAST(N'09:00:00' AS Time), CAST(N'18:00:00' AS Time), 4)
INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (16, N'Viento y Marea', 1, CAST(N'2021-02-01' AS Date), CAST(N'2021-10-01' AS Date), NULL, NULL, CAST(N'10:35:00' AS Time), CAST(N'20:00:00' AS Time), 4)
INSERT [dbo].[EXPOSICIONES] ([id_exposicion], [nombre], [id_tipo_exposicion], [fecha_inicio], [fecha_fin], [fecha_inicio_replanificada], [fecha_fin_replanificada], [hora_apertura], [hora_cierre], [id_empleado_creador]) VALUES (17, N'Implosion', 2, CAST(N'2021-04-05' AS Date), CAST(N'2021-06-05' AS Date), CAST(N'2021-06-05' AS Date), CAST(N'2021-07-01' AS Date), CAST(N'10:30:00' AS Time), CAST(N'13:30:00' AS Time), 4)
SET IDENTITY_INSERT [dbo].[EXPOSICIONES] OFF
GO
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (1, 4)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (1, 5)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (2, 3)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (2, 4)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (3, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (3, 6)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (4, 5)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (4, 6)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (5, 4)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (5, 5)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (6, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (6, 3)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (7, 1)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (7, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (8, 1)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (8, 6)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (9, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (9, 17)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (10, 3)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (10, 10)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (11, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (11, 17)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (12, 8)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (12, 13)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (13, 12)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (14, 3)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (15, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (15, 17)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (16, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (16, 17)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (17, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (18, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (19, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (20, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (21, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (22, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (22, 13)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (22, 15)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (23, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (24, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (25, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (26, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (27, 2)
INSERT [dbo].[EXPOSICIONES_X_RESERVAS] ([id_reserva], [id_exposicion]) VALUES (28, 2)
GO
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (1, 2)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (1, 3)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (1, 6)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (1, 8)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (1, 12)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (1, 13)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (1, 14)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (1, 17)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (2, 1)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (2, 4)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (2, 5)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (2, 6)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (2, 9)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (2, 12)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (3, 2)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (3, 3)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (3, 6)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (3, 8)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (3, 10)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (3, 16)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (4, 1)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (4, 2)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (4, 11)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (4, 16)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (4, 17)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (5, 3)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (5, 5)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (5, 7)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (5, 9)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (5, 10)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (5, 11)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (5, 12)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (5, 16)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (6, 2)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (6, 3)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (6, 6)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (6, 13)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (6, 15)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (7, 1)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (7, 3)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (7, 7)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (7, 9)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (7, 10)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (7, 12)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (8, 2)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (8, 6)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (8, 11)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (8, 14)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (8, 16)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (9, 4)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (9, 5)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (9, 11)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (9, 15)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (9, 17)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (10, 6)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (10, 7)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (10, 8)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (10, 11)
INSERT [dbo].[EXPOSICIONES_X_SEDES] ([id_sede], [id_exposicion]) VALUES (10, 13)
GO
SET IDENTITY_INSERT [dbo].[HORARIOS] ON 

INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (1, CAST(N'08:00:00' AS Time), CAST(N'16:00:00' AS Time), 0)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (2, CAST(N'09:00:00' AS Time), CAST(N'17:00:00' AS Time), 0)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (3, CAST(N'10:00:00' AS Time), CAST(N'18:00:00' AS Time), 0)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (4, CAST(N'11:00:00' AS Time), CAST(N'19:00:00' AS Time), 0)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (5, CAST(N'12:00:00' AS Time), CAST(N'20:00:00' AS Time), 0)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (6, CAST(N'13:00:00' AS Time), CAST(N'21:00:00' AS Time), 0)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (7, CAST(N'14:00:00' AS Time), CAST(N'22:00:00' AS Time), 0)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (8, CAST(N'15:00:00' AS Time), CAST(N'23:00:00' AS Time), 0)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (9, CAST(N'16:00:00' AS Time), CAST(N'00:00:00' AS Time), 0)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (10, CAST(N'08:00:00' AS Time), CAST(N'12:00:00' AS Time), 0)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (11, CAST(N'12:00:00' AS Time), CAST(N'16:00:00' AS Time), 0)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (12, CAST(N'16:00:00' AS Time), CAST(N'20:00:00' AS Time), 0)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (13, CAST(N'20:00:00' AS Time), CAST(N'00:00:00' AS Time), 0)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (14, CAST(N'08:00:00' AS Time), CAST(N'16:00:00' AS Time), 1)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (15, CAST(N'09:00:00' AS Time), CAST(N'17:00:00' AS Time), 1)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (16, CAST(N'10:00:00' AS Time), CAST(N'18:00:00' AS Time), 1)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (17, CAST(N'11:00:00' AS Time), CAST(N'19:00:00' AS Time), 1)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (18, CAST(N'12:00:00' AS Time), CAST(N'20:00:00' AS Time), 1)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (19, CAST(N'13:00:00' AS Time), CAST(N'21:00:00' AS Time), 1)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (20, CAST(N'14:00:00' AS Time), CAST(N'22:00:00' AS Time), 1)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (21, CAST(N'15:00:00' AS Time), CAST(N'23:00:00' AS Time), 1)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (22, CAST(N'16:00:00' AS Time), CAST(N'00:00:00' AS Time), 1)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (23, CAST(N'08:00:00' AS Time), CAST(N'12:00:00' AS Time), 1)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (24, CAST(N'12:00:00' AS Time), CAST(N'16:00:00' AS Time), 1)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (25, CAST(N'16:00:00' AS Time), CAST(N'20:00:00' AS Time), 1)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (26, CAST(N'20:00:00' AS Time), CAST(N'00:00:00' AS Time), 1)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (27, CAST(N'08:00:00' AS Time), CAST(N'16:00:00' AS Time), 2)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (28, CAST(N'09:00:00' AS Time), CAST(N'17:00:00' AS Time), 2)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (29, CAST(N'10:00:00' AS Time), CAST(N'18:00:00' AS Time), 2)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (30, CAST(N'11:00:00' AS Time), CAST(N'19:00:00' AS Time), 2)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (31, CAST(N'12:00:00' AS Time), CAST(N'20:00:00' AS Time), 2)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (32, CAST(N'13:00:00' AS Time), CAST(N'21:00:00' AS Time), 2)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (33, CAST(N'14:00:00' AS Time), CAST(N'22:00:00' AS Time), 2)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (34, CAST(N'15:00:00' AS Time), CAST(N'23:00:00' AS Time), 2)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (35, CAST(N'16:00:00' AS Time), CAST(N'00:00:00' AS Time), 2)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (36, CAST(N'08:00:00' AS Time), CAST(N'12:00:00' AS Time), 2)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (37, CAST(N'12:00:00' AS Time), CAST(N'16:00:00' AS Time), 2)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (38, CAST(N'16:00:00' AS Time), CAST(N'20:00:00' AS Time), 2)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (39, CAST(N'20:00:00' AS Time), CAST(N'00:00:00' AS Time), 2)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (40, CAST(N'08:00:00' AS Time), CAST(N'16:00:00' AS Time), 3)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (41, CAST(N'09:00:00' AS Time), CAST(N'17:00:00' AS Time), 3)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (42, CAST(N'10:00:00' AS Time), CAST(N'18:00:00' AS Time), 3)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (43, CAST(N'11:00:00' AS Time), CAST(N'19:00:00' AS Time), 3)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (44, CAST(N'12:00:00' AS Time), CAST(N'20:00:00' AS Time), 3)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (45, CAST(N'13:00:00' AS Time), CAST(N'21:00:00' AS Time), 3)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (46, CAST(N'14:00:00' AS Time), CAST(N'22:00:00' AS Time), 3)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (47, CAST(N'15:00:00' AS Time), CAST(N'23:00:00' AS Time), 3)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (48, CAST(N'16:00:00' AS Time), CAST(N'00:00:00' AS Time), 3)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (49, CAST(N'08:00:00' AS Time), CAST(N'12:00:00' AS Time), 3)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (50, CAST(N'12:00:00' AS Time), CAST(N'16:00:00' AS Time), 3)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (51, CAST(N'16:00:00' AS Time), CAST(N'20:00:00' AS Time), 3)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (52, CAST(N'20:00:00' AS Time), CAST(N'00:00:00' AS Time), 3)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (53, CAST(N'08:00:00' AS Time), CAST(N'16:00:00' AS Time), 4)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (54, CAST(N'09:00:00' AS Time), CAST(N'17:00:00' AS Time), 4)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (55, CAST(N'10:00:00' AS Time), CAST(N'18:00:00' AS Time), 4)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (56, CAST(N'11:00:00' AS Time), CAST(N'19:00:00' AS Time), 4)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (57, CAST(N'12:00:00' AS Time), CAST(N'20:00:00' AS Time), 4)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (58, CAST(N'13:00:00' AS Time), CAST(N'21:00:00' AS Time), 4)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (59, CAST(N'14:00:00' AS Time), CAST(N'22:00:00' AS Time), 4)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (60, CAST(N'15:00:00' AS Time), CAST(N'23:00:00' AS Time), 4)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (61, CAST(N'16:00:00' AS Time), CAST(N'00:00:00' AS Time), 4)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (62, CAST(N'08:00:00' AS Time), CAST(N'12:00:00' AS Time), 4)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (63, CAST(N'12:00:00' AS Time), CAST(N'16:00:00' AS Time), 4)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (64, CAST(N'16:00:00' AS Time), CAST(N'20:00:00' AS Time), 4)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (65, CAST(N'20:00:00' AS Time), CAST(N'00:00:00' AS Time), 4)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (66, CAST(N'08:00:00' AS Time), CAST(N'16:00:00' AS Time), 5)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (67, CAST(N'09:00:00' AS Time), CAST(N'17:00:00' AS Time), 5)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (68, CAST(N'10:00:00' AS Time), CAST(N'18:00:00' AS Time), 5)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (69, CAST(N'11:00:00' AS Time), CAST(N'19:00:00' AS Time), 5)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (70, CAST(N'12:00:00' AS Time), CAST(N'20:00:00' AS Time), 5)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (71, CAST(N'13:00:00' AS Time), CAST(N'21:00:00' AS Time), 5)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (72, CAST(N'14:00:00' AS Time), CAST(N'22:00:00' AS Time), 5)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (73, CAST(N'15:00:00' AS Time), CAST(N'23:00:00' AS Time), 5)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (74, CAST(N'16:00:00' AS Time), CAST(N'00:00:00' AS Time), 5)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (75, CAST(N'08:00:00' AS Time), CAST(N'12:00:00' AS Time), 5)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (76, CAST(N'12:00:00' AS Time), CAST(N'16:00:00' AS Time), 5)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (77, CAST(N'16:00:00' AS Time), CAST(N'20:00:00' AS Time), 5)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (78, CAST(N'20:00:00' AS Time), CAST(N'00:00:00' AS Time), 5)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (79, CAST(N'08:00:00' AS Time), CAST(N'16:00:00' AS Time), 6)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (80, CAST(N'09:00:00' AS Time), CAST(N'17:00:00' AS Time), 6)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (81, CAST(N'10:00:00' AS Time), CAST(N'18:00:00' AS Time), 6)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (82, CAST(N'11:00:00' AS Time), CAST(N'19:00:00' AS Time), 6)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (83, CAST(N'12:00:00' AS Time), CAST(N'20:00:00' AS Time), 6)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (84, CAST(N'13:00:00' AS Time), CAST(N'21:00:00' AS Time), 6)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (85, CAST(N'14:00:00' AS Time), CAST(N'22:00:00' AS Time), 6)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (86, CAST(N'15:00:00' AS Time), CAST(N'23:00:00' AS Time), 6)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (87, CAST(N'16:00:00' AS Time), CAST(N'00:00:00' AS Time), 6)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (88, CAST(N'08:00:00' AS Time), CAST(N'12:00:00' AS Time), 6)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (89, CAST(N'12:00:00' AS Time), CAST(N'16:00:00' AS Time), 6)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (90, CAST(N'16:00:00' AS Time), CAST(N'20:00:00' AS Time), 6)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (91, CAST(N'20:00:00' AS Time), CAST(N'00:00:00' AS Time), 6)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (92, CAST(N'08:00:00' AS Time), CAST(N'22:00:00' AS Time), 0)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (93, CAST(N'08:00:00' AS Time), CAST(N'22:00:00' AS Time), 1)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (94, CAST(N'08:00:00' AS Time), CAST(N'22:00:00' AS Time), 2)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (95, CAST(N'08:00:00' AS Time), CAST(N'22:00:00' AS Time), 3)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (96, CAST(N'08:00:00' AS Time), CAST(N'22:00:00' AS Time), 4)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (97, CAST(N'08:00:00' AS Time), CAST(N'22:00:00' AS Time), 5)
INSERT [dbo].[HORARIOS] ([id_horario], [hora_inicio], [hora_fin], [id_dia]) VALUES (98, CAST(N'08:00:00' AS Time), CAST(N'22:00:00' AS Time), 6)
SET IDENTITY_INSERT [dbo].[HORARIOS] OFF
GO
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (1, 1)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (2, 2)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (3, 15)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (4, 41)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (5, 61)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (6, 62)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (7, 79)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (8, 32)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (9, 16)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (10, 1)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (10, 75)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (10, 77)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (11, 14)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (11, 27)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (11, 40)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (11, 92)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (11, 94)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (11, 96)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (11, 98)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (12, 5)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (12, 18)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (12, 57)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (12, 93)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (12, 95)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (12, 97)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (13, 52)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (13, 65)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (13, 78)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (13, 92)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (13, 94)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (13, 96)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (13, 98)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (14, 8)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (14, 20)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (14, 21)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (14, 93)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (14, 95)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (14, 97)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (15, 40)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (15, 53)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (15, 66)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (15, 92)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (15, 94)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (15, 96)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (15, 98)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (16, 3)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (16, 19)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (16, 93)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (16, 95)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (16, 96)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (16, 97)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (17, 14)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (17, 27)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (17, 92)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (17, 94)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (17, 96)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (17, 98)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (18, 52)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (18, 65)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (18, 93)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (18, 95)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (18, 97)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (19, 1)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (19, 14)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (19, 27)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (19, 92)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (19, 94)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (19, 96)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (19, 98)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (20, 2)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (20, 15)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (20, 93)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (20, 95)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (20, 97)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (21, 92)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (21, 94)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (21, 96)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (21, 98)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (22, 4)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (22, 17)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (22, 43)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (22, 93)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (22, 95)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (22, 97)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (23, 56)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (23, 92)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (23, 94)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (23, 96)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (23, 97)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (23, 98)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (24, 10)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (24, 23)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (24, 36)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (24, 93)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (24, 95)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (24, 97)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (25, 2)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (25, 15)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (26, 64)
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (26, 77)
GO
INSERT [dbo].[HORARIOS_DE_EMPLEADOS] ([id_empleado], [id_horario]) VALUES (27, 94)
GO
SET IDENTITY_INSERT [dbo].[OBRAS] ON 

INSERT [dbo].[OBRAS] ([id_obra], [nombre_obra], [id_artista], [alto], [ancho], [peso], [valuacion], [duracion_resumida], [duracion_extendida], [fecha_creacion], [fecha_primer_ingreso], [fecha_registracion], [id_estilo], [id_tecnica], [id_tematica], [descripcion], [codigo_sensor], [id_empleado_creador]) VALUES (1, N'Romeo y Julieta', 1, CAST(1.82 AS Decimal(4, 2)), CAST(1.20 AS Decimal(4, 2)), CAST(2.50 AS Decimal(6, 2)), CAST(750.50 AS Decimal(11, 2)), CAST(N'00:10:00' AS Time), CAST(N'00:15:00' AS Time), CAST(N'2021-10-03' AS Date), CAST(N'2021-10-06' AS Date), CAST(N'2021-10-09' AS Date), NULL, NULL, NULL, N'Drama', 5, 3)
INSERT [dbo].[OBRAS] ([id_obra], [nombre_obra], [id_artista], [alto], [ancho], [peso], [valuacion], [duracion_resumida], [duracion_extendida], [fecha_creacion], [fecha_primer_ingreso], [fecha_registracion], [id_estilo], [id_tecnica], [id_tematica], [descripcion], [codigo_sensor], [id_empleado_creador]) VALUES (2, N'La Celestina', 2, CAST(1.98 AS Decimal(4, 2)), CAST(1.35 AS Decimal(4, 2)), CAST(2.74 AS Decimal(6, 2)), CAST(870.50 AS Decimal(11, 2)), CAST(N'00:15:00' AS Time), CAST(N'00:20:00' AS Time), CAST(N'2021-05-07' AS Date), CAST(N'2021-05-15' AS Date), CAST(N'2021-05-24' AS Date), NULL, NULL, NULL, N'Romance', 2, 4)
INSERT [dbo].[OBRAS] ([id_obra], [nombre_obra], [id_artista], [alto], [ancho], [peso], [valuacion], [duracion_resumida], [duracion_extendida], [fecha_creacion], [fecha_primer_ingreso], [fecha_registracion], [id_estilo], [id_tecnica], [id_tematica], [descripcion], [codigo_sensor], [id_empleado_creador]) VALUES (3, N'La Divina Comedia', 3, CAST(1.45 AS Decimal(4, 2)), CAST(1.74 AS Decimal(4, 2)), CAST(2.21 AS Decimal(6, 2)), CAST(935.50 AS Decimal(11, 2)), CAST(N'00:20:00' AS Time), CAST(N'00:25:00' AS Time), CAST(N'2021-06-07' AS Date), CAST(N'2021-06-15' AS Date), CAST(N'2021-06-10' AS Date), NULL, NULL, NULL, N'Comedia', 1, 2)
INSERT [dbo].[OBRAS] ([id_obra], [nombre_obra], [id_artista], [alto], [ancho], [peso], [valuacion], [duracion_resumida], [duracion_extendida], [fecha_creacion], [fecha_primer_ingreso], [fecha_registracion], [id_estilo], [id_tecnica], [id_tematica], [descripcion], [codigo_sensor], [id_empleado_creador]) VALUES (4, N'La vida es sueño', 4, CAST(1.67 AS Decimal(4, 2)), CAST(1.54 AS Decimal(4, 2)), CAST(2.88 AS Decimal(6, 2)), CAST(465.50 AS Decimal(11, 2)), CAST(N'00:17:00' AS Time), CAST(N'00:23:00' AS Time), CAST(N'2021-02-04' AS Date), CAST(N'2021-02-06' AS Date), CAST(N'2021-02-10' AS Date), NULL, NULL, NULL, N'Terror', 3, 1)
INSERT [dbo].[OBRAS] ([id_obra], [nombre_obra], [id_artista], [alto], [ancho], [peso], [valuacion], [duracion_resumida], [duracion_extendida], [fecha_creacion], [fecha_primer_ingreso], [fecha_registracion], [id_estilo], [id_tecnica], [id_tematica], [descripcion], [codigo_sensor], [id_empleado_creador]) VALUES (5, N'Hamlet', 5, CAST(1.88 AS Decimal(4, 2)), CAST(1.65 AS Decimal(4, 2)), CAST(2.97 AS Decimal(6, 2)), CAST(990.10 AS Decimal(11, 2)), CAST(N'00:15:00' AS Time), CAST(N'00:20:00' AS Time), CAST(N'2021-09-09' AS Date), CAST(N'2021-09-10' AS Date), CAST(N'2021-09-15' AS Date), NULL, NULL, NULL, N'Tragedia', 4, 5)
INSERT [dbo].[OBRAS] ([id_obra], [nombre_obra], [id_artista], [alto], [ancho], [peso], [valuacion], [duracion_resumida], [duracion_extendida], [fecha_creacion], [fecha_primer_ingreso], [fecha_registracion], [id_estilo], [id_tecnica], [id_tematica], [descripcion], [codigo_sensor], [id_empleado_creador]) VALUES (6, N'Don Juan Tenorio', 6, CAST(1.45 AS Decimal(4, 2)), CAST(1.20 AS Decimal(4, 2)), CAST(3.54 AS Decimal(6, 2)), CAST(500.45 AS Decimal(11, 2)), CAST(N'00:10:00' AS Time), CAST(N'00:15:00' AS Time), CAST(N'2021-05-10' AS Date), CAST(N'2021-05-12' AS Date), CAST(N'2021-05-15' AS Date), NULL, NULL, NULL, N'Comedia', 5, 6)
INSERT [dbo].[OBRAS] ([id_obra], [nombre_obra], [id_artista], [alto], [ancho], [peso], [valuacion], [duracion_resumida], [duracion_extendida], [fecha_creacion], [fecha_primer_ingreso], [fecha_registracion], [id_estilo], [id_tecnica], [id_tematica], [descripcion], [codigo_sensor], [id_empleado_creador]) VALUES (7, N'Mona Lisa', 2, CAST(1.65 AS Decimal(4, 2)), CAST(1.10 AS Decimal(4, 2)), CAST(1.85 AS Decimal(6, 2)), CAST(900.00 AS Decimal(11, 2)), CAST(N'00:10:00' AS Time), CAST(N'00:15:00' AS Time), CAST(N'2021-09-04' AS Date), CAST(N'2021-09-07' AS Date), CAST(N'2021-09-12' AS Date), NULL, NULL, NULL, N'Terror', 8, 2)
INSERT [dbo].[OBRAS] ([id_obra], [nombre_obra], [id_artista], [alto], [ancho], [peso], [valuacion], [duracion_resumida], [duracion_extendida], [fecha_creacion], [fecha_primer_ingreso], [fecha_registracion], [id_estilo], [id_tecnica], [id_tematica], [descripcion], [codigo_sensor], [id_empleado_creador]) VALUES (8, N'El Grito', 3, CAST(2.05 AS Decimal(4, 2)), CAST(2.10 AS Decimal(4, 2)), CAST(2.50 AS Decimal(6, 2)), CAST(850.00 AS Decimal(11, 2)), CAST(N'00:05:00' AS Time), CAST(N'00:10:00' AS Time), CAST(N'2021-12-06' AS Date), CAST(N'2021-12-08' AS Date), CAST(N'2021-12-15' AS Date), NULL, NULL, NULL, N'Suspenso', 7, 4)
INSERT [dbo].[OBRAS] ([id_obra], [nombre_obra], [id_artista], [alto], [ancho], [peso], [valuacion], [duracion_resumida], [duracion_extendida], [fecha_creacion], [fecha_primer_ingreso], [fecha_registracion], [id_estilo], [id_tecnica], [id_tematica], [descripcion], [codigo_sensor], [id_empleado_creador]) VALUES (9, N'Guernica', 4, CAST(1.58 AS Decimal(4, 2)), CAST(1.32 AS Decimal(4, 2)), CAST(2.15 AS Decimal(6, 2)), CAST(980.00 AS Decimal(11, 2)), CAST(N'00:06:00' AS Time), CAST(N'00:13:00' AS Time), CAST(N'2021-04-11' AS Date), CAST(N'2021-04-15' AS Date), CAST(N'2021-04-18' AS Date), NULL, NULL, NULL, N'Romance', 2, 3)
INSERT [dbo].[OBRAS] ([id_obra], [nombre_obra], [id_artista], [alto], [ancho], [peso], [valuacion], [duracion_resumida], [duracion_extendida], [fecha_creacion], [fecha_primer_ingreso], [fecha_registracion], [id_estilo], [id_tecnica], [id_tematica], [descripcion], [codigo_sensor], [id_empleado_creador]) VALUES (10, N'El nacimiento de Venus', 5, CAST(2.50 AS Decimal(4, 2)), CAST(2.15 AS Decimal(4, 2)), CAST(4.20 AS Decimal(6, 2)), CAST(500.00 AS Decimal(11, 2)), CAST(N'00:10:00' AS Time), CAST(N'00:15:00' AS Time), CAST(N'2021-11-09' AS Date), CAST(N'2021-11-15' AS Date), CAST(N'2021-11-22' AS Date), NULL, NULL, NULL, N'Comedia', 7, 6)
SET IDENTITY_INSERT [dbo].[OBRAS] OFF
GO
SET IDENTITY_INSERT [dbo].[PUBLICOS_DESTINO] ON 

INSERT [dbo].[PUBLICOS_DESTINO] ([id_publico], [nombre], [caracteristicas]) VALUES (1, N'Público General', N'Exposición destinada a cualquier tipo de visitante')
INSERT [dbo].[PUBLICOS_DESTINO] ([id_publico], [nombre], [caracteristicas]) VALUES (2, N'Menores', N'Exposición destinada a chicos de entre 4 y 17 años')
INSERT [dbo].[PUBLICOS_DESTINO] ([id_publico], [nombre], [caracteristicas]) VALUES (3, N'Jubilados', N'Exposición destinada a jubilados')
INSERT [dbo].[PUBLICOS_DESTINO] ([id_publico], [nombre], [caracteristicas]) VALUES (4, N'Estudiantes de Arte', N'Exposición destinada a estudiantes de carreras de arte')
SET IDENTITY_INSERT [dbo].[PUBLICOS_DESTINO] OFF
GO
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (1, 1)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (1, 2)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (2, 1)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (2, 2)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (3, 2)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (3, 4)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (4, 1)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (5, 3)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (5, 4)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (6, 1)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (6, 2)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (6, 3)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (7, 3)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (7, 4)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (8, 1)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (8, 2)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (9, 1)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (9, 2)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (9, 3)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (10, 1)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (11, 3)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (11, 4)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (12, 4)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (13, 1)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (13, 2)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (14, 3)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (14, 4)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (15, 4)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (16, 1)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (17, 1)
INSERT [dbo].[PUBLICOS_X_EXPOSICIONES] ([id_exposicion], [id_publico]) VALUES (17, 2)
GO
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (1, 200, 200, CAST(N'2020-06-06T12:23:00.000' AS DateTime), CAST(N'2020-06-20T10:30:00.000' AS DateTime), CAST(N'00:50:00' AS Time), CAST(N'10:30:00' AS Time), CAST(N'11:30:00' AS Time), 1, 2, 1)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (2, 200, 180, CAST(N'2020-08-09T09:12:30.000' AS DateTime), CAST(N'2020-08-13T08:40:00.000' AS DateTime), CAST(N'00:35:00' AS Time), CAST(N'08:45:00' AS Time), CAST(N'09:30:00' AS Time), 2, 4, 2)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (3, 150, 136, CAST(N'2021-02-10T16:53:43.000' AS DateTime), CAST(N'2021-03-01T14:00:00.000' AS DateTime), CAST(N'00:30:00' AS Time), CAST(N'14:10:00' AS Time), CAST(N'14:43:00' AS Time), 5, 3, 5)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (4, 50, 50, CAST(N'2021-01-06T09:49:20.000' AS DateTime), CAST(N'2021-01-08T09:00:00.000' AS DateTime), CAST(N'00:43:00' AS Time), CAST(N'09:00:00' AS Time), CAST(N'09:43:00' AS Time), 6, 5, 6)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (5, 100, 90, CAST(N'2021-03-06T10:58:49.000' AS DateTime), CAST(N'2021-03-13T17:00:00.000' AS DateTime), CAST(N'01:15:00' AS Time), CAST(N'17:01:00' AS Time), CAST(N'18:05:00' AS Time), 2, 2, 3)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (6, 112, 100, CAST(N'2021-07-07T16:53:40.000' AS DateTime), CAST(N'2021-07-09T10:00:00.000' AS DateTime), CAST(N'01:20:00' AS Time), NULL, NULL, 3, 1, 2)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (7, 60, 58, CAST(N'2021-06-03T18:46:49.000' AS DateTime), CAST(N'2021-07-01T15:32:00.000' AS DateTime), CAST(N'00:25:00' AS Time), CAST(N'15:35:00' AS Time), CAST(N'11:00:00' AS Time), 1, 1, 1)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (8, 73, 73, CAST(N'2021-08-06T14:43:30.000' AS DateTime), CAST(N'2021-08-16T10:50:00.000' AS DateTime), CAST(N'01:10:00' AS Time), NULL, NULL, 7, 3, 4)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (9, 100, NULL, CAST(N'2021-06-20T00:29:34.600' AS DateTime), CAST(N'2021-06-21T15:00:00.000' AS DateTime), CAST(N'01:08:00' AS Time), NULL, NULL, 5, 4, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (10, 50, NULL, CAST(N'2021-06-20T08:30:52.347' AS DateTime), CAST(N'2021-06-21T16:00:00.000' AS DateTime), CAST(N'00:55:00' AS Time), NULL, NULL, 5, 7, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (11, 50, NULL, CAST(N'2021-06-20T09:22:52.557' AS DateTime), CAST(N'2021-06-21T15:30:00.000' AS DateTime), CAST(N'01:08:00' AS Time), NULL, NULL, 3, 4, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (12, 100, NULL, CAST(N'2021-06-20T09:26:13.260' AS DateTime), CAST(N'2021-06-21T15:30:00.000' AS DateTime), CAST(N'00:35:00' AS Time), NULL, NULL, 3, 10, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (13, 50, NULL, CAST(N'2021-06-20T09:29:06.913' AS DateTime), CAST(N'2021-06-26T15:00:00.000' AS DateTime), CAST(N'00:13:00' AS Time), NULL, NULL, 2, 4, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (14, 100, NULL, CAST(N'2021-06-21T17:19:06.183' AS DateTime), CAST(N'2021-06-24T16:00:00.000' AS DateTime), CAST(N'00:40:00' AS Time), NULL, NULL, 4, 6, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (15, 100, NULL, CAST(N'2021-06-22T13:11:23.817' AS DateTime), CAST(N'2021-06-28T16:00:00.000' AS DateTime), CAST(N'01:08:00' AS Time), NULL, NULL, 4, 4, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (16, 100, NULL, CAST(N'2021-06-22T13:29:36.280' AS DateTime), CAST(N'2021-06-28T16:00:00.000' AS DateTime), CAST(N'01:08:00' AS Time), NULL, NULL, 3, 4, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (17, 50, NULL, CAST(N'2021-08-03T17:38:02.177' AS DateTime), CAST(N'2021-08-30T15:30:00.000' AS DateTime), CAST(N'00:45:00' AS Time), NULL, NULL, 3, 4, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (18, 100, NULL, CAST(N'2021-08-03T17:44:53.347' AS DateTime), CAST(N'2021-08-23T15:30:00.000' AS DateTime), CAST(N'00:45:00' AS Time), NULL, NULL, 4, 4, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (19, 50, NULL, CAST(N'2021-08-12T18:03:31.203' AS DateTime), CAST(N'2021-08-26T16:00:00.000' AS DateTime), CAST(N'00:45:00' AS Time), NULL, NULL, 3, 3, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (20, 50, NULL, CAST(N'2021-08-13T11:17:01.290' AS DateTime), CAST(N'2021-08-27T15:30:00.000' AS DateTime), CAST(N'00:45:00' AS Time), NULL, NULL, 3, 4, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (21, 50, NULL, CAST(N'2021-08-19T17:19:35.950' AS DateTime), CAST(N'2021-08-26T15:30:00.000' AS DateTime), CAST(N'00:45:00' AS Time), NULL, NULL, 1, 4, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (22, 50, NULL, CAST(N'2021-08-21T18:42:20.703' AS DateTime), CAST(N'2021-08-23T15:30:00.000' AS DateTime), CAST(N'01:20:00' AS Time), NULL, NULL, 5, 6, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (23, 50, NULL, CAST(N'2021-08-22T06:38:45.150' AS DateTime), CAST(N'2021-08-23T15:00:00.000' AS DateTime), CAST(N'00:45:00' AS Time), NULL, NULL, 5, 6, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (24, 20, NULL, CAST(N'2021-08-22T11:09:57.597' AS DateTime), CAST(N'2021-08-24T15:30:00.000' AS DateTime), CAST(N'00:45:00' AS Time), NULL, NULL, 4, 4, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (25, 50, NULL, CAST(N'2021-08-22T12:07:34.740' AS DateTime), CAST(N'2021-08-26T15:00:00.000' AS DateTime), CAST(N'00:45:00' AS Time), NULL, NULL, 4, 4, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (26, 20, NULL, CAST(N'2021-08-22T12:09:39.190' AS DateTime), CAST(N'2021-09-01T15:00:00.000' AS DateTime), CAST(N'00:45:00' AS Time), NULL, NULL, 5, 4, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (27, 50, NULL, CAST(N'2021-08-24T14:00:04.727' AS DateTime), CAST(N'2021-08-24T15:30:00.000' AS DateTime), CAST(N'00:45:00' AS Time), NULL, NULL, 5, 3, 11)
INSERT [dbo].[RESERVAS_DE_VISITA] ([id_reserva], [cantidad_alumnos], [cantidad_alumnos_confirmada], [fecha_hora_creacion], [fecha_hora_reserva], [duracion_estimada], [hora_inicio_real], [hora_fin_real], [id_escuela], [id_sede], [id_empleado_creador]) VALUES (28, 20, NULL, CAST(N'2021-08-26T11:38:17.090' AS DateTime), CAST(N'2021-09-02T15:00:00.000' AS DateTime), CAST(N'00:45:00' AS Time), NULL, NULL, 7, 6, 11)
GO
SET IDENTITY_INSERT [dbo].[SEDES] ON 

INSERT [dbo].[SEDES] ([id_sede], [nombre], [cantidad_maxima_visitantes], [cantidad_maxima_por_guia], [calle_nombre], [calle_numero]) VALUES (1, N'Sede Cordoba', 600, 65, N'Obispo Trejos', 825)
INSERT [dbo].[SEDES] ([id_sede], [nombre], [cantidad_maxima_visitantes], [cantidad_maxima_por_guia], [calle_nombre], [calle_numero]) VALUES (2, N'Sede Buenos Aires', 500, 25, N'Rondeau', 300)
INSERT [dbo].[SEDES] ([id_sede], [nombre], [cantidad_maxima_visitantes], [cantidad_maxima_por_guia], [calle_nombre], [calle_numero]) VALUES (3, N'Bellas Artes', 400, 30, N'Brasil', 128)
INSERT [dbo].[SEDES] ([id_sede], [nombre], [cantidad_maxima_visitantes], [cantidad_maxima_por_guia], [calle_nombre], [calle_numero]) VALUES (4, N'Louvre', 300, 50, N'Agustín Roque Arias', 1600)
INSERT [dbo].[SEDES] ([id_sede], [nombre], [cantidad_maxima_visitantes], [cantidad_maxima_por_guia], [calle_nombre], [calle_numero]) VALUES (5, N'Museo Británico', 200, 40, N'Av. De Mayo', 1000)
INSERT [dbo].[SEDES] ([id_sede], [nombre], [cantidad_maxima_visitantes], [cantidad_maxima_por_guia], [calle_nombre], [calle_numero]) VALUES (6, N'Museo Nacional del Prado', 450, 80, N'Blamey Lafore', 594)
INSERT [dbo].[SEDES] ([id_sede], [nombre], [cantidad_maxima_visitantes], [cantidad_maxima_por_guia], [calle_nombre], [calle_numero]) VALUES (7, N'Museo Metropolitano de Arte', 550, 60, N'Uruguay', 495)
INSERT [dbo].[SEDES] ([id_sede], [nombre], [cantidad_maxima_visitantes], [cantidad_maxima_por_guia], [calle_nombre], [calle_numero]) VALUES (8, N'Historia Natural', 400, 50, N'José Manuel Estrada', 15)
INSERT [dbo].[SEDES] ([id_sede], [nombre], [cantidad_maxima_visitantes], [cantidad_maxima_por_guia], [calle_nombre], [calle_numero]) VALUES (9, N'Sede centro', 500, 90, N'Entre Rios', 105)
INSERT [dbo].[SEDES] ([id_sede], [nombre], [cantidad_maxima_visitantes], [cantidad_maxima_por_guia], [calle_nombre], [calle_numero]) VALUES (10, N'Sede Zona Norte', 320, 45, N'Obispo Lascano', 2448)
SET IDENTITY_INSERT [dbo].[SEDES] OFF
GO
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (1, CAST(N'2020-05-20T09:00:00.000' AS DateTime), CAST(N'2020-05-20T09:30:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (1, CAST(N'2020-05-23T08:00:00.000' AS DateTime), CAST(N'2020-05-23T10:45:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (1, CAST(N'2020-05-23T12:00:00.000' AS DateTime), CAST(N'2020-05-23T12:22:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (1, CAST(N'2020-05-24T12:00:00.000' AS DateTime), CAST(N'2020-05-25T12:55:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (1, CAST(N'2020-05-26T11:00:00.000' AS DateTime), CAST(N'2020-05-26T12:30:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (2, CAST(N'2020-05-20T09:30:00.000' AS DateTime), CAST(N'2020-05-20T11:30:09.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (2, CAST(N'2020-05-22T08:40:00.000' AS DateTime), CAST(N'2020-05-22T10:55:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (2, CAST(N'2020-05-23T11:00:00.000' AS DateTime), CAST(N'2020-05-23T12:22:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (2, CAST(N'2020-05-25T12:30:00.000' AS DateTime), CAST(N'2020-05-25T12:55:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (2, CAST(N'2020-05-26T11:00:00.000' AS DateTime), CAST(N'2020-05-26T12:30:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (3, CAST(N'2020-05-20T08:30:00.000' AS DateTime), CAST(N'2020-05-20T11:30:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (3, CAST(N'2020-05-22T07:40:00.000' AS DateTime), CAST(N'2020-05-22T10:55:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (3, CAST(N'2020-05-23T10:00:00.000' AS DateTime), CAST(N'2020-05-23T12:22:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (3, CAST(N'2020-05-25T11:30:00.000' AS DateTime), CAST(N'2020-05-25T12:55:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (3, CAST(N'2020-05-26T08:00:00.000' AS DateTime), CAST(N'2020-05-26T12:30:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (4, CAST(N'2020-05-20T08:30:00.000' AS DateTime), CAST(N'2020-05-20T11:30:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (4, CAST(N'2020-05-21T07:40:00.000' AS DateTime), CAST(N'2020-05-21T10:55:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (4, CAST(N'2020-05-22T10:00:00.000' AS DateTime), CAST(N'2020-05-22T12:22:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (4, CAST(N'2020-05-25T09:30:00.000' AS DateTime), CAST(N'2020-05-25T12:55:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (4, CAST(N'2020-05-26T08:00:00.000' AS DateTime), CAST(N'2020-05-26T12:30:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (5, CAST(N'2020-05-20T11:15:00.000' AS DateTime), CAST(N'2020-05-20T11:30:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (5, CAST(N'2020-05-21T08:40:00.000' AS DateTime), CAST(N'2020-05-21T09:55:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (6, CAST(N'2020-05-22T10:00:00.000' AS DateTime), CAST(N'2020-05-22T12:22:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (6, CAST(N'2020-05-25T10:30:00.000' AS DateTime), CAST(N'2020-05-25T11:55:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (7, CAST(N'2020-05-26T09:00:00.000' AS DateTime), CAST(N'2020-05-26T11:30:00.000' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-06-20T00:22:41.423' AS DateTime), CAST(N'2021-06-20T00:22:57.237' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-06-20T00:24:13.647' AS DateTime), CAST(N'2021-06-20T00:24:29.877' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-06-20T00:28:10.257' AS DateTime), CAST(N'2021-06-20T00:28:25.063' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-06-20T00:29:17.353' AS DateTime), CAST(N'2021-06-20T00:29:34.600' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-06-20T08:28:14.540' AS DateTime), CAST(N'2021-06-20T08:28:32.547' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-06-20T08:30:37.190' AS DateTime), CAST(N'2021-06-20T08:30:52.347' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-06-20T08:42:04.700' AS DateTime), CAST(N'2021-06-20T08:42:21.120' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-06-20T08:45:26.197' AS DateTime), CAST(N'2021-06-20T08:45:40.840' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-06-20T09:22:26.810' AS DateTime), CAST(N'2021-06-20T09:22:52.557' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-06-20T09:25:44.423' AS DateTime), CAST(N'2021-06-20T09:26:13.260' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-06-20T09:28:28.543' AS DateTime), CAST(N'2021-06-20T09:29:06.913' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-06-21T17:17:46.023' AS DateTime), CAST(N'2021-06-21T17:19:06.183' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-06-22T13:07:36.870' AS DateTime), CAST(N'2021-06-22T13:10:22.933' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-06-22T13:11:02.997' AS DateTime), CAST(N'2021-06-22T13:11:23.817' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-06-22T13:27:05.027' AS DateTime), CAST(N'2021-06-22T13:29:36.280' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-08-03T17:37:29.137' AS DateTime), CAST(N'2021-08-03T17:38:02.177' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-08-03T17:44:21.240' AS DateTime), CAST(N'2021-08-03T17:44:53.347' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-08-12T18:02:57.940' AS DateTime), CAST(N'2021-08-12T18:03:31.203' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-08-13T11:16:37.767' AS DateTime), CAST(N'2021-08-13T11:17:01.290' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-08-19T17:18:51.967' AS DateTime), CAST(N'2021-08-19T17:19:35.950' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-08-21T18:41:50.907' AS DateTime), CAST(N'2021-08-21T18:42:20.703' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-08-22T06:38:07.697' AS DateTime), CAST(N'2021-08-22T06:38:45.150' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-08-22T11:09:41.187' AS DateTime), CAST(N'2021-08-22T11:09:57.597' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-08-22T12:07:17.957' AS DateTime), CAST(N'2021-08-22T12:07:34.740' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-08-22T12:09:08.417' AS DateTime), CAST(N'2021-08-22T12:09:39.190' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-08-24T13:59:29.933' AS DateTime), CAST(N'2021-08-24T14:00:04.727' AS DateTime))
INSERT [dbo].[SESIONES] ([id_usuario], [fecha_hora_inicio], [fecha_hora_fin]) VALUES (28, CAST(N'2021-08-26T11:37:57.707' AS DateTime), CAST(N'2021-08-26T11:38:17.090' AS DateTime))
GO
INSERT [dbo].[SEXOS] ([id_sexo], [nombre]) VALUES (0, N'MASCULINO')
INSERT [dbo].[SEXOS] ([id_sexo], [nombre]) VALUES (1, N'FEMENINO')
GO
SET IDENTITY_INSERT [dbo].[TIPOS_DE_EXPOSICION] ON 

INSERT [dbo].[TIPOS_DE_EXPOSICION] ([id_tipo_exposicion], [nombre], [descripcion]) VALUES (1, N'Permanente', N'Tienen vigencia durante varios meses y pueden ser visitadas dentro de los horarios de atención de cada sede.')
INSERT [dbo].[TIPOS_DE_EXPOSICION] ([id_tipo_exposicion], [nombre], [descripcion]) VALUES (2, N'Temporal', N'Suelen organizarse en fechas cercanas a ciertos acontecimientos de importancia general (por ejemplo, fechas patrias, día de la mujer, día del niño, etc.), tienen vigencia durante pocos días y pueden ser visitadas sólo en determinados horarios (definidos específicamente para cada exposición).')
SET IDENTITY_INSERT [dbo].[TIPOS_DE_EXPOSICION] OFF
GO
SET IDENTITY_INSERT [dbo].[TIPOS_DE_VISITA] ON 

INSERT [dbo].[TIPOS_DE_VISITA] ([id_tipo_visita], [nombre]) VALUES (1, N'Completa')
INSERT [dbo].[TIPOS_DE_VISITA] ([id_tipo_visita], [nombre]) VALUES (2, N'Por Exposición')
SET IDENTITY_INSERT [dbo].[TIPOS_DE_VISITA] OFF
GO
SET IDENTITY_INSERT [dbo].[USUARIOS] ON 

INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (1, N'milagros_ramos', N'ACD8141', CAST(N'2022-05-02' AS Date), 3)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (2, N'eduardo_suarez', N'AXQ8541', CAST(N'2021-07-03' AS Date), 5)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (3, N'juan_chavez', N'AHG7252', CAST(N'2022-02-01' AS Date), 7)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (4, N'raul_gonzalez', N'AFB7841', CAST(N'2022-04-09' AS Date), 4)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (5, N'mbelen_lopez', N'AMW7486', CAST(N'2021-08-10' AS Date), 8)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (6, N'lautaro_rodriguez', N'NDF5418', CAST(N'2021-10-21' AS Date), 2)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (7, N'agustina_gomez', N'HGD2541', CAST(N'2021-09-17' AS Date), 6)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (8, N'carla_juarez', N'HGT2514', CAST(N'2022-03-17' AS Date), 9)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (9, N'maria_robles', N'KJL3671', CAST(N'2022-03-17' AS Date), 1)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (10, N'mauricio_cuevas', N'JGT2410', CAST(N'2021-07-20' AS Date), 10)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (11, N'mauro_gabrielli', N'OWK4271', CAST(N'2021-10-20' AS Date), 11)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (12, N'martin_sastre', N'HIM8965', CAST(N'2021-08-10' AS Date), 12)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (13, N'peter_sanchez', N'PPT2398', CAST(N'2021-06-28' AS Date), 13)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (14, N'juan_obolo', N'GFD7643', CAST(N'2021-11-15' AS Date), 14)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (15, N'agus_limon', N'limonagrio', CAST(N'2021-09-13' AS Date), 15)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (16, N'joaco_lopez', N'tututu123', CAST(N'2022-01-23' AS Date), 16)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (17, N'yenifer45', N'yeni5412', CAST(N'2022-05-03' AS Date), 17)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (18, N'mari78', N'nononon', CAST(N'2023-10-30' AS Date), 18)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (19, N'anaDelvalle', N'flipflopJk', CAST(N'2021-11-11' AS Date), 19)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (20, N'cristina_diaz', N'HQW2145', CAST(N'2022-05-21' AS Date), 20)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (21, N'veronica_rodriguez', N'PYT4500', CAST(N'2021-09-08' AS Date), 21)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (22, N'josefina_gonzalez', N'KLY8333', CAST(N'2022-08-21' AS Date), 22)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (23, N'eugenia_martinez', N'LVF5123', CAST(N'2022-01-20' AS Date), 23)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (24, N'guille_gomez', N'LSS5163', CAST(N'2021-10-22' AS Date), 24)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (25, N'franco_rodriguez', N'LLE4523', CAST(N'2022-11-15' AS Date), 25)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (26, N'agos_gonzalez', N'LKF2501', CAST(N'2022-09-05' AS Date), 26)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (27, N'vicki_fernandez', N'TRE2105', CAST(N'2022-11-25' AS Date), 27)
INSERT [dbo].[USUARIOS] ([id_usuario], [nombre_usuario], [contrasena], [caducidad], [id_empleado]) VALUES (28, N'tomas_delizia', N'290501', CAST(N'2022-11-25' AS Date), 11)
SET IDENTITY_INSERT [dbo].[USUARIOS] OFF
GO
ALTER TABLE [dbo].[ASIGNACIONES_DE_GUIA]  WITH CHECK ADD  CONSTRAINT [asig_guia_id_guia_fk] FOREIGN KEY([id_guia])
REFERENCES [dbo].[EMPLEADOS] ([id_empleado])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ASIGNACIONES_DE_GUIA] CHECK CONSTRAINT [asig_guia_id_guia_fk]
GO
ALTER TABLE [dbo].[ASIGNACIONES_DE_GUIA]  WITH CHECK ADD  CONSTRAINT [FKbng1b8wqvjgtvbtq5rk534waj] FOREIGN KEY([id_guia])
REFERENCES [dbo].[EMPLEADOS] ([id_empleado])
GO
ALTER TABLE [dbo].[ASIGNACIONES_DE_GUIA] CHECK CONSTRAINT [FKbng1b8wqvjgtvbtq5rk534waj]
GO
ALTER TABLE [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA]  WITH CHECK ADD  CONSTRAINT [asig_guia_x_reserva_id_asig_fk] FOREIGN KEY([id_asignacion])
REFERENCES [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] CHECK CONSTRAINT [asig_guia_x_reserva_id_asig_fk]
GO
ALTER TABLE [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA]  WITH CHECK ADD  CONSTRAINT [asig_guia_x_reserva_id_reserva_fk] FOREIGN KEY([id_reserva])
REFERENCES [dbo].[RESERVAS_DE_VISITA] ([id_reserva])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] CHECK CONSTRAINT [asig_guia_x_reserva_id_reserva_fk]
GO
ALTER TABLE [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA]  WITH CHECK ADD  CONSTRAINT [FK2cxakwy1difbbshcof82aiye3] FOREIGN KEY([id_asignacion])
REFERENCES [dbo].[ASIGNACIONES_DE_GUIA] ([id_asignacion])
GO
ALTER TABLE [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] CHECK CONSTRAINT [FK2cxakwy1difbbshcof82aiye3]
GO
ALTER TABLE [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA]  WITH CHECK ADD  CONSTRAINT [FKm0ob1aw79f8f1w7usrnhm5e0t] FOREIGN KEY([id_reserva])
REFERENCES [dbo].[RESERVAS_DE_VISITA] ([id_reserva])
GO
ALTER TABLE [dbo].[ASIGNACIONES_DE_GUIA_X_RESERVA] CHECK CONSTRAINT [FKm0ob1aw79f8f1w7usrnhm5e0t]
GO
ALTER TABLE [dbo].[CAMBIOS_DE_ESTADO]  WITH CHECK ADD  CONSTRAINT [cambios_de_estado_res_id_est_fk] FOREIGN KEY([id_estado_reserva])
REFERENCES [dbo].[ESTADOS_DE_RESERVA] ([id_estado_reserva])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CAMBIOS_DE_ESTADO] CHECK CONSTRAINT [cambios_de_estado_res_id_est_fk]
GO
ALTER TABLE [dbo].[CAMBIOS_DE_ESTADO]  WITH CHECK ADD  CONSTRAINT [FK53jkqa4aljqst9kl9t4tguf4g] FOREIGN KEY([id_estado_reserva])
REFERENCES [dbo].[ESTADOS_DE_RESERVA] ([id_estado_reserva])
GO
ALTER TABLE [dbo].[CAMBIOS_DE_ESTADO] CHECK CONSTRAINT [FK53jkqa4aljqst9kl9t4tguf4g]
GO
ALTER TABLE [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS]  WITH CHECK ADD  CONSTRAINT [cambios_de_estado_x_reserva_id_cambio_fk] FOREIGN KEY([id_cambio_de_estado])
REFERENCES [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] CHECK CONSTRAINT [cambios_de_estado_x_reserva_id_cambio_fk]
GO
ALTER TABLE [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS]  WITH CHECK ADD  CONSTRAINT [cambios_estado_x_reserva_id_reserva_fk] FOREIGN KEY([id_reserva])
REFERENCES [dbo].[RESERVAS_DE_VISITA] ([id_reserva])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] CHECK CONSTRAINT [cambios_estado_x_reserva_id_reserva_fk]
GO
ALTER TABLE [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS]  WITH CHECK ADD  CONSTRAINT [FKdi3dd2jnp60bhev1klbfcp3ja] FOREIGN KEY([id_reserva])
REFERENCES [dbo].[RESERVAS_DE_VISITA] ([id_reserva])
GO
ALTER TABLE [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] CHECK CONSTRAINT [FKdi3dd2jnp60bhev1klbfcp3ja]
GO
ALTER TABLE [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS]  WITH CHECK ADD  CONSTRAINT [FKqscs1bgopn1rq6lnid2kttv7b] FOREIGN KEY([id_cambio_de_estado])
REFERENCES [dbo].[CAMBIOS_DE_ESTADO] ([id_cambio_de_estado])
GO
ALTER TABLE [dbo].[CAMBIOS_DE_ESTADO_DE_RESERVAS] CHECK CONSTRAINT [FKqscs1bgopn1rq6lnid2kttv7b]
GO
ALTER TABLE [dbo].[DETALLES_DE_EXPOSICION]  WITH CHECK ADD  CONSTRAINT [dt_expo_id_expo_fk] FOREIGN KEY([id_exposicion])
REFERENCES [dbo].[EXPOSICIONES] ([id_exposicion])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DETALLES_DE_EXPOSICION] CHECK CONSTRAINT [dt_expo_id_expo_fk]
GO
ALTER TABLE [dbo].[DETALLES_DE_EXPOSICION]  WITH CHECK ADD  CONSTRAINT [dt_expo_id_obra_fk] FOREIGN KEY([id_obra])
REFERENCES [dbo].[OBRAS] ([id_obra])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DETALLES_DE_EXPOSICION] CHECK CONSTRAINT [dt_expo_id_obra_fk]
GO
ALTER TABLE [dbo].[DETALLES_DE_EXPOSICION]  WITH CHECK ADD  CONSTRAINT [FK44voft3n5a21dmlq0j24yjmho] FOREIGN KEY([id_obra])
REFERENCES [dbo].[OBRAS] ([id_obra])
GO
ALTER TABLE [dbo].[DETALLES_DE_EXPOSICION] CHECK CONSTRAINT [FK44voft3n5a21dmlq0j24yjmho]
GO
ALTER TABLE [dbo].[EMPLEADOS]  WITH CHECK ADD  CONSTRAINT [empleados_id_cargo_fk] FOREIGN KEY([id_cargo])
REFERENCES [dbo].[CARGOS] ([id_cargo])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[EMPLEADOS] CHECK CONSTRAINT [empleados_id_cargo_fk]
GO
ALTER TABLE [dbo].[EMPLEADOS]  WITH CHECK ADD  CONSTRAINT [empleados_id_sede_fk] FOREIGN KEY([id_sede])
REFERENCES [dbo].[SEDES] ([id_sede])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[EMPLEADOS] CHECK CONSTRAINT [empleados_id_sede_fk]
GO
ALTER TABLE [dbo].[EMPLEADOS]  WITH CHECK ADD  CONSTRAINT [empleados_id_sexo_fk] FOREIGN KEY([id_sexo])
REFERENCES [dbo].[SEXOS] ([id_sexo])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[EMPLEADOS] CHECK CONSTRAINT [empleados_id_sexo_fk]
GO
ALTER TABLE [dbo].[EMPLEADOS]  WITH CHECK ADD  CONSTRAINT [FKn14uiyrxw0j2y9vlluhlc7k9] FOREIGN KEY([id_cargo])
REFERENCES [dbo].[CARGOS] ([id_cargo])
GO
ALTER TABLE [dbo].[EMPLEADOS] CHECK CONSTRAINT [FKn14uiyrxw0j2y9vlluhlc7k9]
GO
ALTER TABLE [dbo].[EMPLEADOS]  WITH CHECK ADD  CONSTRAINT [FKnv1x586qjmsurun7o29gjmb4c] FOREIGN KEY([id_sede])
REFERENCES [dbo].[SEDES] ([id_sede])
GO
ALTER TABLE [dbo].[EMPLEADOS] CHECK CONSTRAINT [FKnv1x586qjmsurun7o29gjmb4c]
GO
ALTER TABLE [dbo].[EXPOSICIONES]  WITH CHECK ADD  CONSTRAINT [exposiciones_id_empleado_fk] FOREIGN KEY([id_empleado_creador])
REFERENCES [dbo].[EMPLEADOS] ([id_empleado])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[EXPOSICIONES] CHECK CONSTRAINT [exposiciones_id_empleado_fk]
GO
ALTER TABLE [dbo].[EXPOSICIONES]  WITH CHECK ADD  CONSTRAINT [exposiciones_id_tipo_expo_fk] FOREIGN KEY([id_tipo_exposicion])
REFERENCES [dbo].[TIPOS_DE_EXPOSICION] ([id_tipo_exposicion])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[EXPOSICIONES] CHECK CONSTRAINT [exposiciones_id_tipo_expo_fk]
GO
ALTER TABLE [dbo].[EXPOSICIONES]  WITH CHECK ADD  CONSTRAINT [FKbavvcqgix12lc24a6ulq32rxl] FOREIGN KEY([id_empleado_creador])
REFERENCES [dbo].[EMPLEADOS] ([id_empleado])
GO
ALTER TABLE [dbo].[EXPOSICIONES] CHECK CONSTRAINT [FKbavvcqgix12lc24a6ulq32rxl]
GO
ALTER TABLE [dbo].[EXPOSICIONES]  WITH CHECK ADD  CONSTRAINT [FKl2jl7l3rgt6hc3t4wttgvqyvl] FOREIGN KEY([id_tipo_exposicion])
REFERENCES [dbo].[TIPOS_DE_EXPOSICION] ([id_tipo_exposicion])
GO
ALTER TABLE [dbo].[EXPOSICIONES] CHECK CONSTRAINT [FKl2jl7l3rgt6hc3t4wttgvqyvl]
GO
ALTER TABLE [dbo].[EXPOSICIONES_X_RESERVAS]  WITH CHECK ADD  CONSTRAINT [expo_x_reserva_id_expo_fk] FOREIGN KEY([id_exposicion])
REFERENCES [dbo].[EXPOSICIONES] ([id_exposicion])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[EXPOSICIONES_X_RESERVAS] CHECK CONSTRAINT [expo_x_reserva_id_expo_fk]
GO
ALTER TABLE [dbo].[EXPOSICIONES_X_RESERVAS]  WITH CHECK ADD  CONSTRAINT [expo_x_reserva_id_reserva_fk] FOREIGN KEY([id_reserva])
REFERENCES [dbo].[RESERVAS_DE_VISITA] ([id_reserva])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[EXPOSICIONES_X_RESERVAS] CHECK CONSTRAINT [expo_x_reserva_id_reserva_fk]
GO
ALTER TABLE [dbo].[EXPOSICIONES_X_RESERVAS]  WITH CHECK ADD  CONSTRAINT [FKd92bymc3l91awnps7oqs5xg0l] FOREIGN KEY([id_exposicion])
REFERENCES [dbo].[EXPOSICIONES] ([id_exposicion])
GO
ALTER TABLE [dbo].[EXPOSICIONES_X_RESERVAS] CHECK CONSTRAINT [FKd92bymc3l91awnps7oqs5xg0l]
GO
ALTER TABLE [dbo].[EXPOSICIONES_X_RESERVAS]  WITH CHECK ADD  CONSTRAINT [FKg2j804479jn318mn6rgjvhfm6] FOREIGN KEY([id_reserva])
REFERENCES [dbo].[RESERVAS_DE_VISITA] ([id_reserva])
GO
ALTER TABLE [dbo].[EXPOSICIONES_X_RESERVAS] CHECK CONSTRAINT [FKg2j804479jn318mn6rgjvhfm6]
GO
ALTER TABLE [dbo].[EXPOSICIONES_X_SEDES]  WITH CHECK ADD  CONSTRAINT [expo_x_sede_id_expo_fk] FOREIGN KEY([id_exposicion])
REFERENCES [dbo].[EXPOSICIONES] ([id_exposicion])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[EXPOSICIONES_X_SEDES] CHECK CONSTRAINT [expo_x_sede_id_expo_fk]
GO
ALTER TABLE [dbo].[EXPOSICIONES_X_SEDES]  WITH CHECK ADD  CONSTRAINT [expo_x_sede_id_sede_fk] FOREIGN KEY([id_sede])
REFERENCES [dbo].[SEDES] ([id_sede])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[EXPOSICIONES_X_SEDES] CHECK CONSTRAINT [expo_x_sede_id_sede_fk]
GO
ALTER TABLE [dbo].[EXPOSICIONES_X_SEDES]  WITH CHECK ADD  CONSTRAINT [FKhm9qcmoc31w9kvsfetbo6t8lp] FOREIGN KEY([id_exposicion])
REFERENCES [dbo].[EXPOSICIONES] ([id_exposicion])
GO
ALTER TABLE [dbo].[EXPOSICIONES_X_SEDES] CHECK CONSTRAINT [FKhm9qcmoc31w9kvsfetbo6t8lp]
GO
ALTER TABLE [dbo].[EXPOSICIONES_X_SEDES]  WITH CHECK ADD  CONSTRAINT [FKrnq9x8hyd5cch4qf6w7hdaiqb] FOREIGN KEY([id_sede])
REFERENCES [dbo].[SEDES] ([id_sede])
GO
ALTER TABLE [dbo].[EXPOSICIONES_X_SEDES] CHECK CONSTRAINT [FKrnq9x8hyd5cch4qf6w7hdaiqb]
GO
ALTER TABLE [dbo].[HORARIOS]  WITH CHECK ADD  CONSTRAINT [horarios_id_dia] FOREIGN KEY([id_dia])
REFERENCES [dbo].[DIAS_DE_SEMANA] ([id_dia])
GO
ALTER TABLE [dbo].[HORARIOS] CHECK CONSTRAINT [horarios_id_dia]
GO
ALTER TABLE [dbo].[HORARIOS_DE_EMPLEADOS]  WITH CHECK ADD  CONSTRAINT [FK3h07xpw5herghtuna2otmgtto] FOREIGN KEY([id_empleado])
REFERENCES [dbo].[EMPLEADOS] ([id_empleado])
GO
ALTER TABLE [dbo].[HORARIOS_DE_EMPLEADOS] CHECK CONSTRAINT [FK3h07xpw5herghtuna2otmgtto]
GO
ALTER TABLE [dbo].[HORARIOS_DE_EMPLEADOS]  WITH CHECK ADD  CONSTRAINT [FKhthrc94vhgi658lj9gf26imra] FOREIGN KEY([id_horario])
REFERENCES [dbo].[HORARIOS] ([id_horario])
GO
ALTER TABLE [dbo].[HORARIOS_DE_EMPLEADOS] CHECK CONSTRAINT [FKhthrc94vhgi658lj9gf26imra]
GO
ALTER TABLE [dbo].[HORARIOS_DE_EMPLEADOS]  WITH CHECK ADD  CONSTRAINT [horarios_de_empleados_id_emp_fk] FOREIGN KEY([id_empleado])
REFERENCES [dbo].[EMPLEADOS] ([id_empleado])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HORARIOS_DE_EMPLEADOS] CHECK CONSTRAINT [horarios_de_empleados_id_emp_fk]
GO
ALTER TABLE [dbo].[HORARIOS_DE_EMPLEADOS]  WITH CHECK ADD  CONSTRAINT [horarios_de_empleados_id_horario_fk] FOREIGN KEY([id_horario])
REFERENCES [dbo].[HORARIOS] ([id_horario])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HORARIOS_DE_EMPLEADOS] CHECK CONSTRAINT [horarios_de_empleados_id_horario_fk]
GO
ALTER TABLE [dbo].[HORARIOS_DE_SEDES]  WITH CHECK ADD  CONSTRAINT [FK6oo59jabx3bfnodm7i9auiqch] FOREIGN KEY([id_sede])
REFERENCES [dbo].[SEDES] ([id_sede])
GO
ALTER TABLE [dbo].[HORARIOS_DE_SEDES] CHECK CONSTRAINT [FK6oo59jabx3bfnodm7i9auiqch]
GO
ALTER TABLE [dbo].[HORARIOS_DE_SEDES]  WITH CHECK ADD  CONSTRAINT [FKrla9gv0alxjfptf6bk8dupbtb] FOREIGN KEY([id_horario])
REFERENCES [dbo].[HORARIOS] ([id_horario])
GO
ALTER TABLE [dbo].[HORARIOS_DE_SEDES] CHECK CONSTRAINT [FKrla9gv0alxjfptf6bk8dupbtb]
GO
ALTER TABLE [dbo].[HORARIOS_DE_SEDES]  WITH CHECK ADD  CONSTRAINT [horarios_de_sedes_id_emp_fk] FOREIGN KEY([id_sede])
REFERENCES [dbo].[SEDES] ([id_sede])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HORARIOS_DE_SEDES] CHECK CONSTRAINT [horarios_de_sedes_id_emp_fk]
GO
ALTER TABLE [dbo].[HORARIOS_DE_SEDES]  WITH CHECK ADD  CONSTRAINT [horarios_de_sedes_id_horario_fk] FOREIGN KEY([id_horario])
REFERENCES [dbo].[HORARIOS] ([id_horario])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HORARIOS_DE_SEDES] CHECK CONSTRAINT [horarios_de_sedes_id_horario_fk]
GO
ALTER TABLE [dbo].[OBRAS]  WITH CHECK ADD  CONSTRAINT [FKen0o87wi3hlo5iwfo8tx7smcs] FOREIGN KEY([id_empleado_creador])
REFERENCES [dbo].[EMPLEADOS] ([id_empleado])
GO
ALTER TABLE [dbo].[OBRAS] CHECK CONSTRAINT [FKen0o87wi3hlo5iwfo8tx7smcs]
GO
ALTER TABLE [dbo].[PUBLICOS_X_EXPOSICIONES]  WITH CHECK ADD  CONSTRAINT [FK80a3nk79t1mlr2wvbauwvjlov] FOREIGN KEY([id_publico])
REFERENCES [dbo].[PUBLICOS_DESTINO] ([id_publico])
GO
ALTER TABLE [dbo].[PUBLICOS_X_EXPOSICIONES] CHECK CONSTRAINT [FK80a3nk79t1mlr2wvbauwvjlov]
GO
ALTER TABLE [dbo].[PUBLICOS_X_EXPOSICIONES]  WITH CHECK ADD  CONSTRAINT [FKkscd5ecs34who6gsh86ygvu7v] FOREIGN KEY([id_exposicion])
REFERENCES [dbo].[EXPOSICIONES] ([id_exposicion])
GO
ALTER TABLE [dbo].[PUBLICOS_X_EXPOSICIONES] CHECK CONSTRAINT [FKkscd5ecs34who6gsh86ygvu7v]
GO
ALTER TABLE [dbo].[PUBLICOS_X_EXPOSICIONES]  WITH CHECK ADD  CONSTRAINT [publi_x_expo_id_expo_fk] FOREIGN KEY([id_exposicion])
REFERENCES [dbo].[EXPOSICIONES] ([id_exposicion])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PUBLICOS_X_EXPOSICIONES] CHECK CONSTRAINT [publi_x_expo_id_expo_fk]
GO
ALTER TABLE [dbo].[PUBLICOS_X_EXPOSICIONES]  WITH CHECK ADD  CONSTRAINT [publi_x_expo_id_publi_fk] FOREIGN KEY([id_publico])
REFERENCES [dbo].[PUBLICOS_DESTINO] ([id_publico])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PUBLICOS_X_EXPOSICIONES] CHECK CONSTRAINT [publi_x_expo_id_publi_fk]
GO
ALTER TABLE [dbo].[RESERVAS_DE_VISITA]  WITH CHECK ADD  CONSTRAINT [FK6g5hb1s6swpypucbn4rq00v1] FOREIGN KEY([id_empleado_creador])
REFERENCES [dbo].[EMPLEADOS] ([id_empleado])
GO
ALTER TABLE [dbo].[RESERVAS_DE_VISITA] CHECK CONSTRAINT [FK6g5hb1s6swpypucbn4rq00v1]
GO
ALTER TABLE [dbo].[RESERVAS_DE_VISITA]  WITH CHECK ADD  CONSTRAINT [FK9qmnn943e1dg6lf1slajen23c] FOREIGN KEY([id_escuela])
REFERENCES [dbo].[ESCUELAS] ([id_escuela])
GO
ALTER TABLE [dbo].[RESERVAS_DE_VISITA] CHECK CONSTRAINT [FK9qmnn943e1dg6lf1slajen23c]
GO
ALTER TABLE [dbo].[RESERVAS_DE_VISITA]  WITH CHECK ADD  CONSTRAINT [FKjgfm7uj6dd05xpc19pivabjg7] FOREIGN KEY([id_sede])
REFERENCES [dbo].[SEDES] ([id_sede])
GO
ALTER TABLE [dbo].[RESERVAS_DE_VISITA] CHECK CONSTRAINT [FKjgfm7uj6dd05xpc19pivabjg7]
GO
ALTER TABLE [dbo].[RESERVAS_DE_VISITA]  WITH CHECK ADD  CONSTRAINT [reservas_de_visita_id_empleado_creador_fk] FOREIGN KEY([id_empleado_creador])
REFERENCES [dbo].[EMPLEADOS] ([id_empleado])
GO
ALTER TABLE [dbo].[RESERVAS_DE_VISITA] CHECK CONSTRAINT [reservas_de_visita_id_empleado_creador_fk]
GO
ALTER TABLE [dbo].[RESERVAS_DE_VISITA]  WITH CHECK ADD  CONSTRAINT [reservas_de_visita_id_escuela_fk] FOREIGN KEY([id_escuela])
REFERENCES [dbo].[ESCUELAS] ([id_escuela])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[RESERVAS_DE_VISITA] CHECK CONSTRAINT [reservas_de_visita_id_escuela_fk]
GO
ALTER TABLE [dbo].[RESERVAS_DE_VISITA]  WITH CHECK ADD  CONSTRAINT [reservas_de_visita_id_sede_fk] FOREIGN KEY([id_sede])
REFERENCES [dbo].[SEDES] ([id_sede])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[RESERVAS_DE_VISITA] CHECK CONSTRAINT [reservas_de_visita_id_sede_fk]
GO
ALTER TABLE [dbo].[SESIONES]  WITH CHECK ADD  CONSTRAINT [FK8cv9h8j6my5ft6atqfu9uurd] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[USUARIOS] ([id_usuario])
GO
ALTER TABLE [dbo].[SESIONES] CHECK CONSTRAINT [FK8cv9h8j6my5ft6atqfu9uurd]
GO
ALTER TABLE [dbo].[SESIONES]  WITH CHECK ADD  CONSTRAINT [sesiones_id_usuario_fk] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[USUARIOS] ([id_usuario])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[SESIONES] CHECK CONSTRAINT [sesiones_id_usuario_fk]
GO
ALTER TABLE [dbo].[USUARIOS]  WITH CHECK ADD  CONSTRAINT [FKgqymju3ywshi678hefxf52ev6] FOREIGN KEY([id_empleado])
REFERENCES [dbo].[EMPLEADOS] ([id_empleado])
GO
ALTER TABLE [dbo].[USUARIOS] CHECK CONSTRAINT [FKgqymju3ywshi678hefxf52ev6]
GO
ALTER TABLE [dbo].[USUARIOS]  WITH CHECK ADD  CONSTRAINT [usuarios_id_empleado_fk] FOREIGN KEY([id_empleado])
REFERENCES [dbo].[EMPLEADOS] ([id_empleado])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[USUARIOS] CHECK CONSTRAINT [usuarios_id_empleado_fk]
GO
USE [master]
GO
ALTER DATABASE [MUSEO_PICTORICO] SET  READ_WRITE 
GO
