ALTER TABLE ONLY conta
    ADD CONSTRAINT conta_pkey PRIMARY KEY (id);

ALTER TABLE ONLY contato
    ADD CONSTRAINT contato_pkey PRIMARY KEY (id);

ALTER TABLE ONLY empresa_conta
    ADD CONSTRAINT empresa_conta_pkey PRIMARY KEY (empresa_id);

ALTER TABLE ONLY empresa
    ADD CONSTRAINT empresa_pkey PRIMARY KEY (id);

ALTER TABLE ONLY endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);

ALTER TABLE ONLY folha_pagamento_item
    ADD CONSTRAINT folha_pagamento_item_pkey PRIMARY KEY (id);

ALTER TABLE ONLY folha_pagamento
    ADD CONSTRAINT folha_pagamento_pkey PRIMARY KEY (id);

ALTER TABLE ONLY funcionario_conta
    ADD CONSTRAINT funcionario_conta_pkey PRIMARY KEY (funcionario_id);

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (id);

ALTER TABLE ONLY funcionario_contato
    ADD CONSTRAINT uk_qisfcmj2mqshb2twdym1fyx6 UNIQUE (contato_id);

ALTER TABLE ONLY empresa_contato
    ADD CONSTRAINT uk_qvbh7gg77v9o67objwvaguai UNIQUE (contato_id);

ALTER TABLE ONLY empresa
    ADD CONSTRAINT fk1bsdq6lhlktaf86nwgbftqrag FOREIGN KEY (endereco_id) REFERENCES endereco(id);

ALTER TABLE ONLY folha_pagamento_item
    ADD CONSTRAINT fk2c9c9jh7fhco3ywq7cm8e2ire FOREIGN KEY (folha_pagamento_id) REFERENCES folha_pagamento(id);

ALTER TABLE ONLY folha_pagamento
    ADD CONSTRAINT fk3qkrrfaxc3jtoyv09edg48c8k FOREIGN KEY (empresa_id) REFERENCES empresa(id);

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT fk4cm1kg523jlopyexjbmi6y54j FOREIGN KEY (empresa_id) REFERENCES empresa(id);

ALTER TABLE ONLY empresa_contato
    ADD CONSTRAINT fk9yiowheghmfncg5559thr5nvx FOREIGN KEY (contato_id) REFERENCES contato(id);

ALTER TABLE ONLY funcionario_conta
    ADD CONSTRAINT fkcfc9ehlu82ynfvw8f3f2hefe8 FOREIGN KEY (conta_id) REFERENCES conta(id);

ALTER TABLE ONLY funcionario_contato
    ADD CONSTRAINT fkdx5am3xpamqbm1x6eaakp5j05 FOREIGN KEY (contato_id) REFERENCES contato(id);

ALTER TABLE ONLY empresa_conta
    ADD CONSTRAINT fkf25s27463lpenbggbouk97gtj FOREIGN KEY (empresa_id) REFERENCES empresa(id);

ALTER TABLE ONLY funcionario_contato
    ADD CONSTRAINT fkgatrapm5s74ppc6xry37dei5y FOREIGN KEY (funcionario_id) REFERENCES funcionario(id);

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT fkld57l49lwv8ik9ottu7yo5d6k FOREIGN KEY (endereco_id) REFERENCES endereco(id);

ALTER TABLE ONLY empresa_contato
    ADD CONSTRAINT fkmcw40bnokkeoa9pnn9ue7afci FOREIGN KEY (empresa_id) REFERENCES empresa(id);

ALTER TABLE ONLY funcionario_conta
    ADD CONSTRAINT fknq9g0pa38io2gkhl5lffk21ve FOREIGN KEY (funcionario_id) REFERENCES funcionario(id);

ALTER TABLE ONLY empresa_conta
    ADD CONSTRAINT fkrvwxvym8x832j62fmuv0k5oq FOREIGN KEY (conta_id) REFERENCES conta(id);

ALTER TABLE ONLY folha_pagamento_item
    ADD CONSTRAINT fktqofyhadjtnm6xfwsmc93mcsr FOREIGN KEY (funcionario_id) REFERENCES funcionario(id);