CREATE TABLE endereco (
                          id_endereco SERIAL PRIMARY KEY,
                          estado VARCHAR(100) NOT NULL,
                          bairro VARCHAR(100) NOT NULL,
                          uf CHAR(2) NOT NULL,
                          cidade VARCHAR(100) NOT NULL,
                          rua VARCHAR(100) NOT NULL,
                          cep VARCHAR(8) NOT NULL,
                          numero INTEGER,
                          complemento VARCHAR(100)
);
CREATE TABLE pessoa (
                        id_pessoa SERIAL PRIMARY KEY,
                        email VARCHAR(100) NOT NULL UNIQUE,
                        senha VARCHAR(50) NOT NULL,
                        usuario VARCHAR(50) NOT NULL UNIQUE,
                        nome_pessoa VARCHAR(100) NOT NULL,
                        telefone VARCHAR(11) NOT NULL UNIQUE,
                        cnpj CHAR(14) UNIQUE,
                        id_endereco INTEGER,
                        FOREIGN KEY(id_endereco) REFERENCES endereco (id_endereco)
);
CREATE TABLE especialidade (
                               id_especialidade SERIAL PRIMARY KEY,
                               descricao_especialidade VARCHAR(100) NOT NULL UNIQUE
);
CREATE TABLE medida (
                        id_medida SERIAL PRIMARY KEY,
                        categoria VARCHAR(100) NOT NULL,
                        valor_medida DECIMAL(10,2) NOT NULL,
                        UNIQUE (categoria, valor_medida)
);
CREATE TABLE materia_prima (
                               id_materia_prima SERIAL PRIMARY KEY,
                               qtde_materia_prima DECIMAL(10,2) NOT NULL,
                               unidade_materia_prima VARCHAR(10) NOT NULL,
                               nome_materia_prima VARCHAR(100) NOT NULL
);
CREATE TABLE cliente (
                         id_pessoa SERIAL PRIMARY KEY,
                         data_nascimento DATE NOT NULL,
                         cpf CHAR(11) UNIQUE,
                         id_pessoaC INTEGER,
                         FOREIGN KEY(id_pessoa) REFERENCES pessoa(id_pessoa)
);
CREATE TABLE servico (
                         id_servico SERIAL PRIMARY KEY,
                         tempo_medio INTEGER NOT NULL,
                         valor_servico DECIMAL(10,2) NOT NULL,
                         nome_servico VARCHAR(100) NOT NULL
);
CREATE TABLE forma_pagamento (
                                 id_forma_pagamento SERIAL PRIMARY KEY,
                                 nome_forma_pagamento VARCHAR(100) NOT NULL UNIQUE
);
CREATE TABLE atelie (
                        id_pessoa SERIAL PRIMARY KEY,
                        qntd_notas INTEGER NOT NULL,
                        nota_avaliacao DECIMAL(10,2) NOT NULL,
                        inicio_01 INTEGER NOT NULL,
                        fim_01 INTEGER NOT NULL,
                        inicio_02 INTEGER,
                        fim_02 INTEGER,
                        id_pessoaA INTEGER,
                        id_especialidade INTEGER,
                        FOREIGN KEY(id_pessoa) REFERENCES pessoa(id_pessoa),
                        FOREIGN KEY(id_especialidade) REFERENCES especialidade (id_especialidade)
);
CREATE TABLE pedido (
                        id_pedido SERIAL PRIMARY KEY,
                        status INTEGER NOT NULL,
                        data_entrega DATE,
                        valor_total DECIMAL(10,2) NOT NULL,
                        data_previsao_entrega DATE,
                        id_atelie INTEGER,
                        id_cliente INTEGER,
                        foto_peca BYTEA,
                        descricao_pedido VARCHAR(100) NOT NULL,
                        id_forma_pagamento INTEGER,
                        FOREIGN KEY(id_forma_pagamento) REFERENCES forma_pagamento (id_forma_pagamento),
                        FOREIGN KEY(id_atelie) REFERENCES atelie (id_pessoa),
                        FOREIGN KEY(id_cliente) REFERENCES cliente (id_pessoa)
);
CREATE TABLE materia_prima_servico (
                                       id_servico INTEGER,
                                       id_materia_prima INTEGER,
                                       PRIMARY KEY(id_servico, id_materia_prima),
                                       valor_materia_servico DECIMAL(10,2) NOT NULL,
                                       unidade_medida_materia_servico VARCHAR(10) NOT NULL,
                                       FOREIGN KEY(id_servico) REFERENCES servico (id_servico),
                                       FOREIGN KEY(id_materia_prima) REFERENCES materia_prima (id_materia_prima)
);

CREATE TABLE atelie_servico (
                                id_atelie INTEGER,
                                id_servico INTEGER,
                                PRIMARY KEY(id_atelie, id_servico),
                                FOREIGN KEY(id_atelie) REFERENCES atelie (id_pessoa),
                                FOREIGN KEY(id_servico) REFERENCES servico (id_servico)
);
CREATE TABLE pedido_servico (
                                id_servico INTEGER,
                                id_pedido INTEGER,
                                PRIMARY KEY(id_servico, id_pedido),
                                valor_servico_pedido DECIMAL(10,2) NOT NULL,
                                FOREIGN KEY(id_servico) REFERENCES servico (id_servico),
                                FOREIGN KEY(id_pedido) REFERENCES pedido (id_pedido)
);
CREATE TABLE medida_pedido (
                               id_medida INTEGER,
                               id_pedido INTEGER,
                               PRIMARY KEY(id_medida, id_pedido),
                               FOREIGN KEY(id_medida) REFERENCES medida (id_medida),
                               FOREIGN KEY(id_pedido) REFERENCES pedido (id_pedido)
);

-- Tabela endereco
INSERT INTO endereco (estado, bairro, uf, cidade, rua, cep, numero, complemento) VALUES
                                                                                     ('RN', 'Alto Vera Cruz', 'PI', 'Viana', 'Fazenda Ana Lívia Freitas', '26542351', 26, 'Complemento 1'),
                                                                                     ('AP', 'Minaslandia', 'AM', 'Monteiro Paulista', 'Quadra Teixeira', '61849593', 760, 'Complemento 2'),
                                                                                     ('RR', 'Betânia', 'AL', 'Gonçalves', 'Fazenda Castro', '4752553', 282, 'Complemento 3'),
                                                                                     ('RR', 'Nova Granada', 'RO', 'da Cunha', 'Feira de da Cruz', '6483503', 251, 'Complemento 4'),
                                                                                     ('SE', 'Lagoa', 'MG', 'Campos do Oeste', 'Largo de da Rosa', '6724238', 229, 'Complemento 5'),
                                                                                     ('GO', 'Vila Primeiro De Maio', 'RJ', 'Nunes da Praia', 'Conjunto Silva', '1012269', 143, 'Complemento 6'),
                                                                                     ('MG', 'Mineirão', 'RN', 'Souza de Fernandes', 'Aeroporto de Correia', '5146270', 755, 'Complemento 7'),
                                                                                     ('PE', 'Vila Santo Antônio', 'CE', 'Jesus', 'Rodovia de da Paz', '52880957', 105, 'Complemento 8'),
                                                                                     ('AC', 'Camponesa 2ª Seção', 'MS', 'Duarte do Norte', 'Travessa Cardoso', '71822782', 693, 'Complemento 9'),
                                                                                     ('GO', 'São Geraldo', 'RN', 'da Rosa da Prata', 'Vila de da Paz', '6578713', 759, 'Complemento 10');

-- Tabela pessoa
INSERT INTO pessoa (email, senha, usuario, nome_pessoa, telefone, cnpj, id_endereco) VALUES
                                                                                         ('augusto98@das.com', '^$b1)BkMkl', 'doliveira', 'Srta. Nicole Castro', '55119651333', '26973104000176', 1),
                                                                                         ('vianaantonio@duarte.br', 's0*7Gk9mgj', 'carlos-eduardoviana', 'Vinicius Lopes', '55119097882', '12940735000184', 2),
                                                                                         ('cardosoana-beatriz@ferreira.net', 'Hb5Hi^Lz_^', 'daniel35', 'Davi Lucca Pires', '55849498084', '41859726000161', 3),
                                                                                         ('juan48@fernandes.com', '6jI7gbJ!@r', 'ana-juliacostela', 'Henrique Moreira', '55119533158', '23961804000152', 4),
                                                                                         ('rfarias@hotmail.com', 'p1mMMb4@+C', 'vteixeira', 'Carlos Eduardo Ferreira', '55319940196', '6813472000183', 5),
                                                                                         ('silveiraesther@ig.com.br', '^b3LnfAA+H', 'rnascimento', 'Srta. Mariana Jesus', '55719804436', '57839162000135', 6),
                                                                                         ('gomeseduarda@carvalho.net', '2@_4o^Hz2d', 'cavalcantistella', 'Clarice da Luz', '55849708317', '89563472000166', 7),
                                                                                         ('pietraribeiro@uol.com.br', 'i%3U^9iBR_', 'voliveira', 'Letícia Peixoto', '55119366909', '05463817000110', 8),
                                                                                         ('ana-julia70@mendes.br', 'yRt+b2Oe17', 'raquel64', 'Lucas Aragão', '55849053100', '02317965000138', 9),
                                                                                         ('isaac99@ig.com.br', 'x+@1YwHe^f', 'barbosaryan', 'Lara Moura', '55219850671', '72615483000140', 10);

-- Tabela cliente
INSERT INTO cliente (data_nascimento, cpf, id_pessoaC) VALUES
                                                          ('1985-04-15', '12345678901', 1),
                                                          ('1990-12-20', '23456789012', 2),
                                                          ('1978-06-10', '34567890123', 3),
                                                          ('1995-08-05', '45678901234', 4),
                                                          ('1982-11-30', '56789012345', 5);

-- Tabela especialidade
INSERT INTO especialidade (descricao_especialidade) VALUES
                                                        ('Moda Feminina'),
                                                        ('Moda Masculina'),
                                                        ('Alta Costura'),
                                                        ('Moda Infantil'),
                                                        ('Reformas e Ajustes'),
                                                        ('Bordados'),
                                                        ('Estamparia'),
                                                        ('Alfaiataria'),
                                                        ('Confecção de Uniformes'),
                                                        ('Design de Acessórios');

-- Tabela atelie
INSERT INTO atelie (qntd_notas, nota_avaliacao, inicio_01, fim_01, inicio_02, fim_02, id_pessoaA, id_especialidade) VALUES
                                                                                                                       (25, 4.8, 8, 12, 14, 18, 6, 1),
                                                                                                                       (30, 4.5, 9, 13, NULL, NULL, 7, 2),
                                                                                                                       (15, 4.7, 8, 12, 13, 17, 8, 3),
                                                                                                                       (10, 4.9, 7, 11, 15, 19, 9, 4),
                                                                                                                       (20, 4.6, 8, 12, NULL, NULL, 10, 5);

-- Tabela servico
INSERT INTO servico (tempo_medio, valor_servico, nome_servico) VALUES
                                                                   (3, 150.00, 'Confecção de vestido'),
                                                                   (2, 100.00, 'Conserto de calça'),
                                                                   (4, 200.00, 'Terno sob medida'),
                                                                   (1, 50.00, 'Ajuste de camisa'),
                                                                   (3, 120.00, 'Costura de saia'),
                                                                   (5, 250.00, 'Casaco personalizado'),
                                                                   (2, 80.00, 'Bainha de calça'),
                                                                   (3, 140.00, 'Blusa bordada'),
                                                                   (4, 220.00, 'Conserto de vestido de festa'),
                                                                   (1, 60.00, 'Troca de zíper');

-- Tabela forma_pagamento
INSERT INTO forma_pagamento (nome_forma_pagamento) VALUES
                                                       ('Cartão de Crédito'),
                                                       ('Pix'),
                                                       ('Dinheiro'),
                                                       ('Boleto Bancário'),
                                                       ('Transferência Bancária');

-- Tabela pedido
INSERT INTO pedido (status, data_entrega, valor_total, data_previsao_entrega, id_atelie, id_cliente, foto_peca, descricao_pedido, id_forma_pagamento) VALUES
                                                                                                                                                          (1, '2025-07-15', 300.00, '2025-07-10', 1, 1, NULL, 'Vestido longo azul com bordados', 1),
                                                                                                                                                          (2, '2025-07-20', 100.00, '2025-07-18', 2, 2, NULL, 'Ajuste em calça jeans', 2),
                                                                                                                                                          (1, '2025-07-22', 220.00, '2025-07-20', 3, 3, NULL, 'Conserto em vestido de festa', 3),
                                                                                                                                                          (3, '2025-07-18', 140.00, '2025-07-15', 4, 4, NULL, 'Blusa bordada para evento', 1),
                                                                                                                                                          (2, '2025-07-25', 250.00, '2025-07-22', 5, 5, NULL, 'Casaco personalizado para inverno', 4);

-- Tabela materia_prima
INSERT INTO materia_prima (qtde_materia_prima, unidade_materia_prima, nome_materia_prima) VALUES
                                                                                              (10.0, 'm', 'Tecido algodão'),
                                                                                              (5.0, 'un', 'Zíper'),
                                                                                              (20.0, 'm', 'Tecido sintético'),
                                                                                              (8.0, 'm', 'Linha de costura'),
                                                                                              (15.0, 'un', 'Botão'),
                                                                                              (12.0, 'm', 'Tecido seda'),
                                                                                              (25.0, 'm', 'Tecido jeans'),
                                                                                              (7.0, 'un', 'Elástico'),
                                                                                              (3.0, 'un', 'Fita de cetim'),
                                                                                              (18.0, 'm', 'Tecido linho');

-- Tabela materia_prima_servico
INSERT INTO materia_prima_servico (id_servico, id_materia_prima, valor_materia_servico, unidade_medida_materia_servico) VALUES
                                                                                                                            (1, 1, 20.00, 'm'),
                                                                                                                            (2, 2, 5.00, 'un'),
                                                                                                                            (3, 3, 25.00, 'm'),
                                                                                                                            (4, 4, 10.00, 'm'),
                                                                                                                            (5, 5, 7.00, 'un'),
                                                                                                                            (6, 6, 30.00, 'm'),
                                                                                                                            (7, 7, 15.00, 'm'),
                                                                                                                            (8, 8, 4.00, 'un'),
                                                                                                                            (9, 9, 3.00, 'un'),
                                                                                                                            (10, 10, 22.00, 'm');

-- Tabela atelie_servico
INSERT INTO atelie_servico (id_atelie, id_servico) VALUES
                                                       (1, 1),
                                                       (1, 2),
                                                       (2, 3),
                                                       (2, 4),
                                                       (3, 5),
                                                       (3, 6),
                                                       (4, 7),
                                                       (4, 8),
                                                       (5, 9),
                                                       (5, 10);

-- Tabela pedido_servico
INSERT INTO pedido_servico (id_servico, id_pedido, valor_servico_pedido) VALUES
                                                                             (1, 1, 300.00),
                                                                             (2, 2, 100.00),
                                                                             (3, 3, 220.00),
                                                                             (4, 4, 140.00),
                                                                             (5, 5, 250.00);

-- Tabela medida
INSERT INTO medida (categoria, valor_medida) VALUES
                                                 ('Busto', 90.0),
                                                 ('Cintura', 75.0),
                                                 ('Quadril', 100.0),
                                                 ('Altura', 170.0),
                                                 ('Comprimento Manga', 60.0),
                                                 ('Ombro', 40.0),
                                                 ('Punho', 18.0),
                                                 ('Largura Costas', 35.0),
                                                 ('Cava', 20.0),
                                                 ('Gancho', 25.0);

-- Tabela medida_pedido
INSERT INTO medida_pedido (id_medida, id_pedido) VALUES
                                                     (1, 1),
                                                     (2, 1),
                                                     (3, 1),
                                                     (4, 2),
                                                     (5, 2),
                                                     (6, 3),
                                                     (7, 3),
                                                     (8, 4),
                                                     (9, 4),
                                                     (10, 5);
