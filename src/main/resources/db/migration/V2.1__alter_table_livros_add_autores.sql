alter table livros add column autor_id int not null;
alter table livros
    add constraint fk_livros_autores
    foreign key (autor_id) references autores (id);