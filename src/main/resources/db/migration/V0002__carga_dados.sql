INSERT INTO conta VALUES (1, 1234, 'Banco do Brasil', 0, 2, 4321, NULL, 700542.50);
INSERT INTO conta VALUES (2, 8796, 'Banco Bradesco', 1, 5, 2584, NULL, 505424.50);
INSERT INTO conta VALUES (3, 4587, 'Banco Bradesco', 1, 4, 7854, NULL, 3000.00);
INSERT INTO conta VALUES (4, 9087, 'Banco Bradesco', 1, 0, 7704, NULL, 4000.00);
INSERT INTO conta VALUES (5, 1254, 'Banco Bradesco', 1, 0, 4572, NULL, 8000.00);
INSERT INTO conta VALUES (6, 1254, 'Banco Bradesco', 1, 0, 4572, NULL, 8000.00);

INSERT INTO contato VALUES (1, '8525976446', 'TELEFONE');
INSERT INTO contato VALUES (2, '8525976446', 'TELEFONE');
INSERT INTO contato VALUES (3, '8525976446', 'TELEFONE');
INSERT INTO contato VALUES (4, '8827411054', 'TELEFONE');
INSERT INTO contato VALUES (5, '8829603701', 'TELEFONE');
INSERT INTO contato VALUES (6, '8828879279', 'TELEFONE');

INSERT INTO empresa VALUES (1, '75253226000101', '2016-10-05', 'contato@kaueesuelibuffetltda.com.br', '406276552', 'Kauê e Sueli Buffet Ltda', 'www.kaueesuelibuffetltda.com.br', 1);
INSERT INTO empresa VALUES (2, '19358335000104', '2016-05-04', 'treinamento@ritaepatriciaadvocaciame.com.br', '046893199', 'Rita e Patrícia Advocacia ME', 'www.ritaepatriciaadvocaciame.com.br', 2);

INSERT INTO empresa_conta VALUES (1, 1);
INSERT INTO empresa_conta VALUES (2, 2);

INSERT INTO empresa_contato VALUES (1, 1);
INSERT INTO empresa_contato VALUES (2, 2);

INSERT INTO endereco VALUES (1, 'Jandaiguaba', '61615700', 'Caucaia', 'CE', 'Rua Quinze de Novembro', '491');
INSERT INTO endereco VALUES (2, 'Conjunto Ceará', '60530520', 'Fortaleza', 'CE', 'Rua 301B', '261');
INSERT INTO endereco VALUES (3, 'Aldeota', '60150050', 'Fortaleza', 'CE', 'Rua Júlio Ventura', '748');
INSERT INTO endereco VALUES (4, 'Centro', '63645970', 'Deputado Irapuan Pinheiro', 'CE', 'Rua Raquel Magalhães 69', '473');
INSERT INTO endereco VALUES (5, 'Centro', '62010310', 'Sobral', 'CE', 'Rua Menino Deus', '249');
INSERT INTO endereco VALUES (6, 'Pedrinhas', '63018030', 'Juazeiro do Norte', 'CE', 'Rua Francisca Pereira Lopes', '250');

INSERT INTO funcionario VALUES (1, '29739145400', '2020-03-10', NULL, '1992-12-11', 'renataelzamoraes..renataelzamoraes@hotamail.com', 'Auxiliar Administrativo', 'Renata Elza Moraes', '391567731', 2500.00, 'FEMININO', 1, 3);
INSERT INTO funcionario VALUES (2, '06589916390', '2020-04-15', NULL, '1997-11-05', 'olivercalebdossantos__olivercalebdossantos@imail.com', 'Vendedor', 'Oliver Caleb dos Santos', '119964752', 3000.00, 'MASCULINO', 1, 4);
INSERT INTO funcionario VALUES (3, '72436659346', '2015-01-15', NULL, '1950-11-02', 'josebenicionogueira-83@selaz.com.br', 'Gerente', 'José Benício Nogueira', '393024702', 3800.00, 'MASCULINO', 2, 5);
INSERT INTO funcionario VALUES (4, '96462362359', '2018-03-20', NULL, '1990-03-22', 'cauacauevicentemoraes-74@eguia.com.br', 'Balconista', 'Cauã Cauê Vicente Moraes', '210035912', 2500.00, 'MASCULINO', 2, 6);

INSERT INTO funcionario_conta VALUES (3, 1);
INSERT INTO funcionario_conta VALUES (4, 2);
INSERT INTO funcionario_conta VALUES (5, 3);
INSERT INTO funcionario_conta VALUES (6, 4);

INSERT INTO funcionario_contato VALUES (1, 3);
INSERT INTO funcionario_contato VALUES (2, 4);
INSERT INTO funcionario_contato VALUES (3, 5);
INSERT INTO funcionario_contato VALUES (4, 6);

SELECT pg_catalog.setval('public.conta_id_seq', 6, true);
SELECT pg_catalog.setval('public.contato_id_seq', 6, true);
SELECT pg_catalog.setval('public.empresa_id_seq', 2, true);
SELECT pg_catalog.setval('public.endereco_id_seq', 6, true);
SELECT pg_catalog.setval('public.folha_pagamento_id_seq', 1, false);
SELECT pg_catalog.setval('public.funcionario_id_seq', 4, true);