alter table restaurante add column ativo boolean default(1) not null;
update restaurante set ativo = true;