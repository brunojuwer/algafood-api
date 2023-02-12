alter table restaurante add column aberto boolean default(1) not null;
update restaurante set aberto = true;