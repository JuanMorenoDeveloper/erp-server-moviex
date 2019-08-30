--Datos roles
-- TBD
--Datos roles
insert into rol (id, nombre, descripcion)
values (1, 'ROLE_USER', 'Rol de usuario');
insert into rol (id, nombre, descripcion)
values (2, 'ROLE_ADMIN', 'Rol de administrador');
--Datos usuarios
insert into usuario (login, password)
values ('user', 'user');
insert into usuario (login, password)
values ('usr', 'usr');
insert into usuario (login, password)
values ('admin', 'admin');
insert into usuario (login, password)
values ('adm', 'adm');

--Datos roles usuarios
insert into usuario_roles (usuario_id, roles_id)
values (1, 1);
insert into usuario_roles (usuario_id, roles_id)
values (2, 1);
insert into usuario_roles (usuario_id, roles_id)
values (3, 2);
insert into usuario_roles (usuario_id, roles_id)
values (4, 2);
--Datos pelicula
