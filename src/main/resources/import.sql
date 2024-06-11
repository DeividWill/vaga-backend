-- Empregador
INSERT INTO empregador (id, nome, empresa, email, telefone) VALUES
(1, 'John Doe', 'Tech Solutions', 'john.doe@techsolutions.com', '9999845315476'),
(2, 'Jane Smith', 'Innovative Designs', 'jane.smith@innovativedesigns.com', '999486154795');

-- Vaga
INSERT INTO vaga (id, titulo, descricao, salario, data_publicacao, id_empregador) VALUES
(1, 'Desenvolvedor Java', 'Desenvolvimento de aplicações web em Java', 5000.00, '2023-06-01', 1),
(2, 'Designer Gráfico', 'Criação de designs para web e impressão', 4000.00, '2023-06-02', 2);

-- Candidato
INSERT INTO candidato (id, nome, email, telefone, curriculo) VALUES
(1, 'Alice Johnson', 'alice.johnson@gmail.com', '639546813248', 'Currículo de Alice'),
(2, 'Bob Brown', 'bob.brown@gmail.com', '99654186794', 'Currículo de Bob');

-- Candidatura
INSERT INTO candidatura (id, id_candidato, id_vaga) VALUES
(1, 1, 1),
(2, 2, 2);