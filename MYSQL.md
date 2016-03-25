**Ver la lista de procesos corriendo en la BD**
```

show processlist;
```

**Activar la variable para que se ejecuten los Events o demonios**
```

SET GLOBAL event_scheduler = ON;
```

**Otra forma de ver los eventos de un determinado esquema**
```

SHOW EVENTS FROM mmsi_sped;
```

**Alterar un Event**
```

ALTER EVENT demonioEvmeval
ON SCHEDULE
EVERY 1 HOUR
STARTS '2014-05-02 16:00:00' ;
```

**Ver los eventos de un determinado esquema**
```

SELECT * FROM INFORMATION_SCHEMA.EVENTS
WHERE EVENT_SCHEMA='mmsi_sped';
```

**Para agregar minutos u horas a un datetime**
```

SELECT DATE_ADD(end_Date,INTERVAL 59 MINUTE)
FROM evmeval e
WHERE e.nidEvaluacion = 27;
```

```

SELECT lo.nid_log,u.nombres,u.usuario,r.desc_rol,lo.fecha_conexion,lo.fecha_desconexion,lo.navegador_web,lo.sistema_operativo,
e.desc_evento,lo.ip_privada,lo.ip_publica,lo.detalle
FROM mmsi_sped.stdlogg lo,
mmsi_sped.admusua u,
mmsi_sped.stmeven e,
mmsi_sped.stmrole r
where u.nidUsuario = lo.nid_usuario
and e.nid_evento = lo.nid_evento and u.nidUsuario = 69
and r.nidRol = u.nidRol
order by nid_log desc;
SELECT * FROM mmsi_sped.stdlogd order by fechaRegistro DESC;
delete from mmsi_sped.stdlogg where nid_log in (955);
delete from mmsi_sped.stdlogd where idstlogd = 'f63633be-e100-11e3-9fae-0040f4eb2f0a';
select * from stmcodi;
select * from evdresu where nidEvaluacion = 192;
select * from evdrefc where nidEvaluacion = 224;
delete from mmsi_sped.evmeval where nidEvaluacion = 192;
delete from mmsi_sped.evmeval where nidEvaluacion = 102;
-- select * from evdcrin where nidEvaluacion = 224;
select * from evdresu r,evdcrin c where c.nidCriterioIndicador = r.nidCriterioIndicador and r.nidEvaluacion = 224;
select count(1) as "cant",
DATE_FORMAT(fecha_conexion, '%Y-%m-%d') from mmsi_sped.stdlogg
group by DATE_FORMAT(fecha_conexion, '%Y-%m-%d') order by count(1) desc;

select count(1) as "cant",
u.usuario,r.desc_rol from mmsi_sped.stdlogg d,mmsi_sped.admusua u,mmsi_sped.stmrole r where d.nid_usuario = u.nidUsuario
and r.nidRol = u.nidRol
group by u.usuario order by count(1) desc;
```