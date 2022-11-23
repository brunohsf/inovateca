create table usuarios (
    id_usuario serial,
    nome_usuario varchar(50),
    email_usuario varchar(50),
    senha_usuario varchar(20),
    constraint pk_usuarios primary key (id_usuario)
)