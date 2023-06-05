INSERT INTO plano (nome, valor) VALUES ('MULTIPLAN 1',498.23);
INSERT INTO plano (nome, valor) VALUES ('MULTIPLAN 2',408.42);
INSERT INTO plano (nome, valor) VALUES ('MULTIPLAN 3',348.76);

INSERT INTO beneficiario (nome, email, idade, plano_id) VALUES ('João Silva','joao.silva@gmail.com',23, 1);
INSERT INTO beneficiario (nome, email, idade, plano_id) VALUES ('José Souza','jose.souza@gmail.com',80, 2);

INSERT INTO usuario (login, nome, senha, roles) VALUES ('alinson','Alinson','$2a$12$72ovOs9s3fiQQcXI.oDDme6DeZZszhrlt0LaMLPR2mwrGAVjTxU7O','USER');
INSERT INTO usuario (login, nome, senha, roles) VALUES ('admin','Administrador','$2a$12$72ovOs9s3fiQQcXI.oDDme6DeZZszhrlt0LaMLPR2mwrGAVjTxU7O','USER,ADMIN');