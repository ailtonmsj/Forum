INSERT INTO PERFIL(nome) VALUES('ACTUATOR');
INSERT INTO PERFIL(nome) VALUES('ADMIN');
INSERT INTO PERFIL(nome) VALUES('USER');

INSERT INTO USUARIO(nome, email, senha) VALUES('actuatorUser', 'actuator@email.com', '$2a$10$hDQMpKvUreyNgq7dNBI8AekfMnwjDTxceSMX5vq5ug0NlDm6J5OGS');
INSERT INTO USUARIO(nome, email, senha) VALUES('Aluno1', 'aluno1@email.com', '$2a$10$hDQMpKvUreyNgq7dNBI8AekfMnwjDTxceSMX5vq5ug0NlDm6J5OGS');
INSERT INTO USUARIO(nome, email, senha) VALUES('Aluno2', 'aluno2@email.com', '$2a$10$hDQMpKvUreyNgq7dNBI8AekfMnwjDTxceSMX5vq5ug0NlDm6J5OGS');

INSERT INTO USUARIO_PERFIL(USUARIO_ID, PERFIS_ID) VALUES (1, 1);
INSERT INTO USUARIO_PERFIL(USUARIO_ID, PERFIS_ID) VALUES (2, 2);
INSERT INTO USUARIO_PERFIL(USUARIO_ID, PERFIS_ID) VALUES (3, 3);

INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);