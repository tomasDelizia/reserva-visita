USE MUSEO_PICTORICO;

SELECT s.nombre, h.hora_inicio, h.hora_fin, ds.nombre AS dia_semana
FROM HORARIOS h JOIN HORARIOS_DE_SEDES hs ON (hs.id_horario = h.id_horario)
JOIN DIAS_DE_SEMANA ds ON h.id_dia = ds.id_dia
JOIN SEDES s ON hs.id_sede = s.id_sede;

SELECT e.nombre, e.apellido, h.hora_inicio, h.hora_fin, ds.nombre AS dia_semana
FROM HORARIOS h JOIN HORARIOS_DE_EMPLEADOS he ON (he.id_horario = h.id_horario)
JOIN DIAS_DE_SEMANA ds ON h.id_dia = ds.id_dia
JOIN EMPLEADOS e ON he.id_empleado = e.id_empleado;