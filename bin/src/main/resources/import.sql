insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Japonesa');
insert into cozinha (nome) values ('Chinesa');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_bairro, endereco_cep, endereco_logradouro, endereco_numero, endereco_cidade_id, endereco_complemento, data_cadastro, data_atualizacao) values (1, 'Thai Gourmet', 10, 1, 'Centro', '99999-000', 'Rua dos Anjos', '999', 4, 'Perto do Banco Nacional', utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);

insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (1, 'Pastel Fit', 'Pastel feito na Air Fryer', 15, true, 1);
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (2, 'Pastel de flango', 'Paste feito de flango', 10, true, 2);
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (3, 'Filé Fit', 'File com Whey', 25, false, 3);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
insert into permissao (id, nome, descricao) values (3, 'CONSULTAR_RESTAURANTE', 'Permite consultar cozinhas');

insert into grupo (id, nome) values (1, 'Administrador');
insert into grupo (id, nome) values (2, 'Convidado');
insert into grupo (id, nome) values (3, 'Help Desk');

insert into usuario (id, nome, email, senha, data_cadastro) values (1, 'Bruno', 'bruno@bruno.com.br', '12345678', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (2, 'João', 'joao@bruno.com.br', '123456asd78', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (3, 'Pedro', 'pedro@bruno.com.br', '1234567asdas8', utc_timestamp);

insert into grupo_permissoes (grupo_id, permissao_id) values (1, 1);
insert into grupo_permissoes (grupo_id, permissao_id) values (1, 2);
insert into grupo_permissoes (grupo_id, permissao_id) values (1, 3);
insert into grupo_permissoes (grupo_id, permissao_id) values (2, 1);
insert into grupo_permissoes (grupo_id, permissao_id) values (2, 3);
insert into grupo_permissoes (grupo_id, permissao_id) values (3, 1);
insert into grupo_permissoes (grupo_id, permissao_id) values (3, 2);

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1);
insert into usuario_grupo (usuario_id, grupo_id) values (2, 2);
insert into usuario_grupo (usuario_id, grupo_id) values (3, 2);
insert into usuario_grupo (usuario_id, grupo_id) values (3, 3);


