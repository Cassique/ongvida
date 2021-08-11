create table professor(
   id bigint not null auto_increment,
   nome varchar(68) not null,
   email varchar(255) not null,
   rg varchar(100) not null,
   cpf varchar(100) not null,
   data_nascimento date not null,
   primary key(id)
   
);
