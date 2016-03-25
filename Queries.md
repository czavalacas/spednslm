```

SELECT lo.nid_log,u.nombres,u.usuario,r.desc_rol,lo.fecha_conexion,lo.navegador_web,lo.sistema_operativo,
e.desc_evento,lo.ip_privada,lo.ip_publica
FROM mmsi_sped.stdlogg lo,
mmsi_sped.admusua u,
mmsi_sped.stmeven e,
mmsi_sped.stmrole r
where u.nidUsuario = lo.nid_usuario
and e.nid_evento = lo.nid_evento
and r.nidRol = u.nidRol
order by nid_log desc;
```
```

SELECT * FROM mmsi_sped.admprof where apellidos like '%Casia%';
SELECT * FROM mmsi_sped.admusua where nombres like '%Anne%';
SELECT nidMain FROM mmsi_sped.addmain where dniProfesor = '44388113';
SELECT * FROM mmsi_sped.admprof where dniProfesor = '44388113';
SELECT * FROM mmsi_sped.evmeval e WHERE nid_evaluador = 18
AND e.nidMain in (SELECT nidMain FROM mmsi_sped.addmain where dniProfesor = '40835739');
SELECT * FROM mmsi_sped.evmeval e WHERE nidEvaluacion = 144;
SELECT * FROM mmsi_sped.evmeval e where nid_evaluador = 5 and estado_evaluacion = 'EJECUTADO';
SELECT * FROM mmsi_sped.evdresu r where r.nidEvaluacion = 157;
SELECT COUNT(1) FROM mmsi_sped.evmeval e where estado_evaluacion = 'EJECUTADO';
SELECT * FROM mmsi_sped.evmeval e where estado_evaluacion = 'JUSTIFICADO';
select COUNT(1),e.estado_evaluacion
from evmeval e
GROUP BY e.estado_evaluacion;
SELECT nidMain FROM mmsi_sped.addmain where dniProfesor = '40835739';
SELECT * FROM mmsi_sped.addmain where nidMain = 985; -- 1270 810
SELECT * FROM mmsi_sped.admcurs where nidCurso = 1;
SELECT * FROM mmsi_sped.admarac where nidAreaAcademica = 5;
SELECT * FROM mmsi_sped.evmeval e WHERE e.nid_evaluador = 184 order by e.fecha_evaluacion desc;
SELECT * FROM mmsi_sped.admusua u where nidUsuario = 5;
SELECT * FROM mmsi_sped.evmeval e WHERE nidEvaluacion = 100;
select
e.nidEvaluacion,
u.nombres,
concat(p.nombres, p.apellidos),
e.start_date,
e.end_date,
e.fecha_evaluacion,
c.desc_curso
from
mmsi_sped.evmeval e,
mmsi_sped.addmain m,
mmsi_sped.admprof p,
mmsi_sped.admusua u,
mmsi_sped.admcurs c
where
e.nid_evaluador = u.nidUsuario
and u.nidRol = 2
and e.estado_evaluacion = 'INJUSTIFICADO'
and m.nidMain = e.nidMain
and m.dniProfesor = p.dniProfesor
and m.nidCurso = c.nidCurso
ORDER BY e.start_date ASC;

```

```

select
e.nidEvaluacion,
u.nombres as "EVALUADOR",
concat(p.nombres, p.apellidos) as "PROFESOR",
e.start_date as "FECHA INICIO",
e.end_date as "FECHA FIN",
e.fecha_evaluacion as "EVALUADO EL DIA",
c.desc_curso as "CURSO",
e.estado_evaluacion as "ESTADO"
from
mmsi_sped.evmeval e,
mmsi_sped.addmain m,
mmsi_sped.admprof p,
mmsi_sped.admusua u,
mmsi_sped.admcurs c
where
e.nid_evaluador = u.nidUsuario
and e.nid_evaluador = 15
and m.nidMain = e.nidMain
and m.dniProfesor = p.dniProfesor
and m.nidCurso = c.nidCurso
ORDER BY e.start_date ASC;
```