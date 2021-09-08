insert into clientes(apellido_paterno,apellido_materno ,nombre,tipo_documento,numero_documento ) values ("Perez","Sanchez","Pedro","DNI","75432154");
insert into clientes(apellido_paterno,apellido_materno ,nombre,tipo_documento,numero_documento ) values ("Rondon","Martinez","Maria","DNI","23454345");
insert into clientes(apellido_paterno,apellido_materno ,nombre,tipo_documento,numero_documento ) values ("Lira","Godoya","Luna","DNI","34567845");

insert into lineas_moviles(estado,nombre_plan,numero_telefono,tipo_linea,cliente_id) values ("Activo","Plan Prepago",912356489,"Prepago",1);
insert into lineas_moviles(estado,nombre_plan,numero_telefono,tipo_linea,cliente_id) values ("Cancelado","Plan S/ 29.90",928374829,"Postpago",1);
insert into lineas_moviles(estado,nombre_plan,numero_telefono,tipo_linea,cliente_id) values ("Activo","Plan S/. 39.90",983728376,"Postpago",1);
insert into lineas_moviles(estado,nombre_plan,numero_telefono,tipo_linea,cliente_id) values ("Activo","Plan S/. 49.90",965726387,"Postpago",2);
insert into lineas_moviles(estado,nombre_plan,numero_telefono,tipo_linea,cliente_id) values ("Cancelado","Plan Prepago",983748392,"Prepago",2);
insert into lineas_moviles(estado,nombre_plan,numero_telefono,tipo_linea,cliente_id) values ("Activo","Plan S/. 49.90",974637485,"Postpago",1);

insert into ofertas(descripcion,fecha_inicio,fecha_fin) values ("Oferta de 5 meses de 10 soles","2021-01-05","2021-06-05");
insert into ofertas(descripcion,fecha_inicio,fecha_fin) values ("Oferta de 6 meses de 5 soles","2021-03-15","2021-09-15");
insert into ofertas(descripcion,fecha_inicio,fecha_fin) values ("Oferta de 4 meses de 20 soles","2021-08-10","2021-11-10");
insert into ofertas(descripcion,fecha_inicio,fecha_fin) values ("Oferta de 2 meses de 40 soles","2021-05-13","2021-07-13");
insert into ofertas(descripcion,fecha_inicio,fecha_fin) values ("Oferta de 1 meses de 50 soles","2021-09-02","2021-10-02");
insert into ofertas(descripcion,fecha_inicio,fecha_fin) values ("Oferta de 1 meses de 50 soles","2021-06-02","2021-07-02");
insert into ofertas(descripcion,fecha_inicio,fecha_fin) values ("Oferta de 1 meses de 20 soles","2021-04-01","2021-04-29");
insert into ofertas(descripcion,fecha_inicio,fecha_fin) values ("Oferta de 2 meses de 20 soles","2021-05-01","2021-07-29");
insert into ofertas(descripcion,fecha_inicio,fecha_fin) values ("Oferta de 3 meses de 45 soles","2021-08-25","2021-11-25");
insert into ofertas(descripcion,fecha_inicio,fecha_fin) values ("Oferta de 2 meses de 35 soles","2021-09-12","2021-11-12");

insert into lineas_ofertas values (1,1);
insert into lineas_ofertas values (1,2);
insert into lineas_ofertas values (1,3);
insert into lineas_ofertas values (1,4);
insert into lineas_ofertas values (2,5);
insert into lineas_ofertas values (3,3);
insert into lineas_ofertas values (4,6);
insert into lineas_ofertas values (5,7);
insert into lineas_ofertas values (6,5);
insert into lineas_ofertas values (1,9);
insert into lineas_ofertas values (1,10);