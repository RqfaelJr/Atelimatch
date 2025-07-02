CREATE TABLE endereco (
                          id_endereco SERIAL PRIMARY KEY,
                          estado VARCHAR(100) NOT NULL,
                          bairro VARCHAR(100) NOT NULL,
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

-- Tabela endereco (20 endereços)
INSERT INTO endereco (estado, bairro, cidade, rua, cep, numero, complemento) VALUES
('Rio Grande do Norte', 'Alto Vera Cruz', 'Viana', 'Fazenda Ana Lívia Freitas', '26542351', 26, 'Complemento 1'),
('Amapá', 'Minaslandia', 'Monteiro Paulista', 'Quadra Teixeira', '61849593', 760, 'Complemento 2'),
('Roraima', 'Betânia', 'Gonçalves', 'Fazenda Castro', '4752553', 282, 'Complemento 3'),
('Roraima', 'Nova Granada', 'da Cunha', 'Feira de da Cruz', '6483503', 251, 'Complemento 4'),
('Sergipe', 'Lagoa', 'Campos do Oeste', 'Largo de da Rosa', '6724238', 229, 'Complemento 5'),
('Goiás', 'Vila Primeiro De Maio', 'Nunes da Praia', 'Conjunto Silva', '1012269', 143, 'Complemento 6'),
('Minas Gerais', 'Mineirão', 'Souza de Fernandes', 'Aeroporto de Correia', '5146270', 755, 'Complemento 7'),
('Pernambuco', 'Vila Santo Antônio', 'Jesus', 'Rodovia de da Paz', '52880957', 105, 'Complemento 8'),
('Acre', 'Camponesa 2ª Seção', 'Duarte do Norte', 'Travessa Cardoso', '71822782', 693, 'Complemento 9'),
('Goiás', 'São Geraldo', 'da Rosa da Prata', 'Vila de da Paz', '6578713', 759, 'Complemento 10'),
('São Paulo', 'Jardim Paulista', 'São Paulo', 'Avenida Paulista', '01310930', 1000, 'Conjunto 101'),
('Rio de Janeiro', 'Copacabana', 'Rio de Janeiro', 'Avenida Atlântica', '22021001', 500, 'Apto 302'),
('Minas Gerais', 'Savassi', 'Belo Horizonte', 'Rua Antônio de Albuquerque', '30112010', 245, 'Sala 405'),
('Rio Grande do Sul', 'Moinhos de Vento', 'Porto Alegre', 'Rua Padre Chagas', '90570050', 89, 'Casa 2'),
('Paraná', 'Batel', 'Curitiba', 'Avenida do Batel', '80420090', 1866, 'Bloco B'),
('Bahia', 'Barra', 'Salvador', 'Avenida Oceânica', '40140130', 345, 'Cobertura'),
('Pernambuco', 'Boa Viagem', 'Recife', 'Avenida Boa Viagem', '51021000', 1234, 'Apto 1501'),
('Ceará', 'Meireles', 'Fortaleza', 'Avenida Beira Mar', '60165080', 2200, 'Sala 10'),
('Distrito Federal', 'Asa Sul', 'Brasília', 'Quadra 302', '70232900', 12, 'Conjunto 8'),
('Santa Catarina', 'Centro', 'Florianópolis', 'Rua Felipe Schmidt', '88010200', 345, 'Loja 5');

-- Tabela pessoa (20 pessoas)
INSERT INTO pessoa (email, senha, usuario, nome_pessoa, telefone, cnpj, id_endereco) VALUES
('augusto98@das.com', '^$b1)BkMkl', 'doliveira', 'Srta. Nicole Castro', '11965133322', NULL, 1),
('vianaantonio@duarte.br', 's0*7Gk9mgj', 'carlos-eduardoviana', 'Vinicius Lopes', '11909788233', NULL, 2),
('cardosoana-beatriz@ferreira.net', 'daniel35', 'daniel35', 'Davi Lucca Pires', '84949808444', NULL, 3),
('juan48@fernandes.com', 'ana-juliacostela', 'ana-juliacostela', 'Henrique Moreira', '11953315855', NULL, 4),
('rfarias@hotmail.com', 'vteixeira', 'vteixeira', 'Carlos Eduardo Ferreira', '31994019666', NULL, 5),
('silveiraesther@ig.com.br', 'rnascimento', 'rnascimento', 'Srta. Mariana Jesus', '71980443677', '57839162000135', 6),
('gomeseduarda@carvalho.net', 'cavalcantistella', 'cavalcantistella', 'Clarice da Luz', '84970831788', '89563472000166', 7),
('pietraribeiro@uol.com.br', 'voliveira', 'voliveira', 'Letícia Peixoto', '11936690999', '05463817000110', 8),
('ana-julia70@mendes.br', 'raquel64', 'raquel64', 'Lucas Aragão', '84905310011', '02317965000138', 9),
('isaac99@ig.com.br', 'barbosaryan', 'barbosaryan', 'Lara Moura', '21985067122', '72615483000140', 10),
('joaosilva@email.com', 'joaosilva', 'joaosilva', 'João Silva', '11987654321', NULL, 11),
('mariasouza@email.com', 'mariasouza', 'mariasouza', 'Maria Souza', '11976543210', NULL, 12),
('pedroalves@email.com', 'pedroalves', 'pedroalves', 'Pedro Alves', '11965432109', NULL, 13),
('anapaula@email.com', 'anapaula', 'anapaula', 'Ana Paula Oliveira', '11954321098', NULL, 14),
('carlosrocha@email.com', 'carlosrocha', 'carlosrocha', 'Carlos Rocha', '11943210987', NULL, 15),
('admin@email.com', 'admin', 'admin', 'admin Atelie', '11000000000', '00000000000000', 1),
('admin2@email.com', 'admin', 'adm', 'admin Cliente', '11100000000', NULL, 2);

-- Tabela cliente (corrigida)
INSERT INTO cliente (id_pessoa, data_nascimento, cpf) VALUES
(1, '1985-04-15', '12345678901'),
(2, '1990-12-20', '23456789012'),
(3, '1978-06-10', '34567890123'),
(4, '1995-08-05', '45678901234'),
(5, '1982-11-30', '56789012345'),
(11, '1992-03-25', '67890123456'),
(12, '1988-07-12', '78901234567'),
(13, '1995-11-30', '89012345678'),
(14, '1983-05-18', '90123456789'),
(15, '1976-09-22', '01234567890'),
(17, '2000-01-01', '01277788899');

-- Tabela atelie (corrigida)


-- Tabela especialidade (10 especialidades)
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

INSERT INTO atelie (id_pessoa, qntd_notas, nota_avaliacao, inicio_01, fim_01, id_pessoaA, id_especialidade) VALUES
(6, 25, 4.8, 8, 12, 6, 1),
(7, 30, 4.5, 9, 13, 7, 2),
(8, 15, 4.7, 8, 12, 8, 3),
(9, 10, 4.9, 7, 11, 9, 4),
(10, 20, 4.6, 8, 12, 10, 5),
(16, 0, 0, 8, 17, 16, 3);


-- Tabela servico (20 serviços)
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
(1, 60.00, 'Troca de zíper'),
(6, 350.00, 'Vestido de noiva'),
(2, 70.00, 'Ajuste de barra'),
(5, 280.00, 'Terno social'),
(1, 40.00, 'Troca de botão'),
(4, 180.00, 'Blazer feminino'),
(7, 420.00, 'Traje de formatura'),
(3, 120.00, 'Customização de jeans'),
(2, 90.00, 'Bainha invisível'),
(5, 300.00, 'Vestido de festa longo'),
(1, 35.00, 'Reparo simples');

-- Tabela forma_pagamento (5 formas de pagamento)
INSERT INTO forma_pagamento (nome_forma_pagamento) VALUES
('Cartão de Crédito'),
('Pix'),
('Dinheiro'),
('Boleto Bancário'),
('Transferência Bancária');

-- Tabela pedido (20 pedidos distribuídos não-homogeneamente)
INSERT INTO pedido (status, data_entrega, valor_total, data_previsao_entrega, id_atelie, id_cliente, foto_peca, descricao_pedido, id_forma_pagamento) VALUES
-- Ateliê 1 (6 pedidos)
(1, '2025-07-15', 300.00, '2025-07-10', 6, 1, NULL, 'Vestido longo azul com bordados', 1),
(2, '2025-07-20', 100.00, '2025-07-18', 6, 2, NULL, 'Ajuste em calça jeans', 2),
(1, '2025-07-22', 220.00, '2025-07-20', 6, 3, NULL, 'Conserto em vestido de festa', 3),
(3, '2025-07-18', 140.00, '2025-07-15', 6, 4, NULL, 'Blusa bordada para evento', 1),
(2, '2025-07-25', 250.00, '2025-07-22', 6, 5, NULL, 'Casaco personalizado para inverno', 4),
(1, '2025-08-10', 420.00, '2025-08-05', 6, 11, NULL, 'Traje de formatura masculino', 1),

-- Ateliê 2 (4 pedidos)
(2, '2025-08-15', 180.00, '2025-08-12', 7, 11, NULL, 'Blazer feminino para entrevista', 2),
(3, '2025-08-20', 350.00, '2025-08-18', 7, 12, NULL, 'Vestido de noiva simples', 3),
(1, '2025-08-25', 280.00, '2025-08-22', 7, 13, NULL, 'Terno social para casamento', 4),
(2, '2025-08-30', 300.00, '2025-08-28', 7, 14, NULL, 'Vestido de festa longo vermelho', 5),

-- Ateliê 3 (5 pedidos)
(1, '2025-09-05', 150.00, '2025-09-01', 8, 1, NULL, 'Confecção de vestido simples', 1),
(2, '2025-09-10', 100.00, '2025-09-08', 8, 2, NULL, 'Conserto de calça jeans', 2),
(1, '2025-09-15', 200.00, '2025-09-12', 8, 3, NULL, 'Terno sob medida', 3),
(3, '2025-09-20', 120.00, '2025-09-18', 8, 4, NULL, 'Costura de saia', 1),
(2, '2025-09-25', 250.00, '2025-09-22', 8, 5, NULL, 'Casaco personalizado', 4),

-- Ateliê 4 (3 pedidos)
(1, '2025-10-01', 80.00, '2025-09-28', 9, 11, NULL, 'Bainha de calça', 1),
(2, '2025-10-05', 140.00, '2025-10-03', 9, 12, NULL, 'Blusa bordada', 2),
(1, '2025-10-10', 220.00, '2025-10-08', 9, 13, NULL, 'Conserto de vestido de festa', 3),

-- Ateliê 5 (2 pedidos
(2, '2025-10-15', 60.00, '2025-10-13', 10, 14, NULL, 'Troca de zíper', 4),
(1, '2025-10-20', 350.00, '2025-10-18', 10, 15, NULL, 'Vestido de noiva', 5);

-- Tabela materia_prima (20 materiais primas)
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
(18.0, 'm', 'Tecido linho'),
(30.0, 'm', 'Tecido organza'),
(15.0, 'un', 'Pedaços de renda'),
(25.0, 'm', 'Tecido de lã'),
(10.0, 'un', 'Botões decorativos'),
(18.0, 'm', 'Tecido de chiffon'),
(20.0, 'un', 'Fivelas'),
(12.0, 'm', 'Tecido de veludo'),
(8.0, 'un', 'Passamanaria'),
(5.0, 'un', 'Pedaços de pele sintética'),
(22.0, 'm', 'Tecido de malha');

-- Tabela materia_prima_servico (20 relações)
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
(10, 10, 22.00, 'm'),
(11, 11, 35.00, 'm'),
(12, 12, 8.00, 'un'),
(13, 13, 30.00, 'm'),
(14, 14, 5.00, 'un'),
(15, 15, 25.00, 'm'),
(16, 16, 40.00, 'm'),
(17, 17, 15.00, 'm'),
(18, 18, 6.00, 'un'),
(19, 19, 45.00, 'm'),
(20, 20, 20.00, 'm');

-- Tabela atelie_servico (10 relações - 2 serviços por ateliê)
INSERT INTO atelie_servico (id_atelie, id_servico) VALUES
-- Ateliê 1
(6, 1),
(6, 2),
-- Ateliê 2
(7, 3),
(7, 4),
-- Ateliê 3
(8, 5),
(8, 6),
-- Ateliê 4
(9, 7),
(9, 8),
-- Ateliê 5
(10, 9),
(10, 10);

-- Tabela pedido_servico (20 relações - 1 serviço por pedido)
INSERT INTO pedido_servico (id_servico, id_pedido, valor_servico_pedido) VALUES
-- Pedidos do Ateliê 1
(1, 1, 300.00),
(2, 2, 100.00),
(3, 3, 220.00),
(4, 4, 140.00),
(5, 5, 250.00),
(6, 6, 420.00),

-- Pedidos do Ateliê 2
(7, 7, 180.00),
(8, 8, 350.00),
(9, 9, 280.00),
(10, 10, 300.00),

-- Pedidos do Ateliê 3
(11, 11, 150.00),
(12, 12, 100.00),
(13, 13, 200.00),
(14, 14, 120.00),
(15, 15, 250.00),

-- Pedidos do Ateliê 4
(16, 16, 80.00),
(17, 17, 140.00),
(18, 18, 220.00),

-- Pedidos do Ateliê 5
(19, 19, 60.00),
(20, 20, 350.00);

-- Tabela medida (20 medidas)
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
('Gancho', 25.0),
('Pescoço', 38.0),
('Tórax', 95.0),
('Cintura Alta', 80.0),
('Altura do Joelho', 50.0),
('Largura do Peito', 35.0),
('Comprimento da Perna', 80.0),
('Circunferência da Coxa', 55.0),
('Circunferência da Panturrilha', 35.0),
('Altura do Quadril', 20.0),
('Largura do Ombro', 42.0);

-- Tabela medida_pedido (40 relações - 2 medidas por pedido)
INSERT INTO medida_pedido (id_medida, id_pedido) VALUES
-- Medidas para os pedidos do Ateliê 1
(1, 1), (2, 1),
(3, 2), (4, 2),
(5, 3), (6, 3),
(7, 4), (8, 4),
(9, 5), (10, 5),
(11, 6), (12, 6),

-- Medidas para os pedidos do Ateliê 2
(13, 7), (14, 7),
(15, 8), (16, 8),
(17, 9), (18, 9),
(19, 10), (20, 10),

-- Medidas para os pedidos do Ateliê 3
(1, 11), (2, 11),
(3, 12), (4, 12),
(5, 13), (6, 13),
(7, 14), (8, 14),
(9, 15), (10, 15),

-- Medidas para os pedidos do Ateliê 4
(11, 16), (12, 16),
(13, 17), (14, 17),
(15, 18), (16, 18),

-- Medidas para os pedidos do Ateliê 5
(17, 19), (18, 19),
(19, 20), (20, 20);