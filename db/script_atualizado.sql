create table tb_aluno
(
    id    int auto_increment
        primary key,
    nome  varchar(255) null,
    cpf   varchar(255) null,
    senha varchar(255) null,
    curso varchar(255) null,
    constraint cpf
        unique (cpf)
);

create table tb_disciplina
(
    id            int auto_increment
        primary key,
    nome          varchar(255) null,
    carga_horaria varchar(255) null,
    descricao     varchar(255) null
);

create table tb_laudo
(
    id        bigint auto_increment
        primary key,
    aluno_id  int          null,
    descricao varchar(255) not null,
    condicao  varchar(255) not null,
    validade  varchar(255) not null,
    constraint tb_laudo_ibfk_1
        foreign key (aluno_id) references tb_aluno (id)
);

create index aluno_id
    on tb_laudo (aluno_id);

create table tb_professor
(
    id    int auto_increment
        primary key,
    nome  varchar(255) null,
    cpf   varchar(255) null,
    senha varchar(255) null,
    constraint cpf
        unique (cpf)
);

create table tb_sala
(
    id         int auto_increment
        primary key,
    numero     int not null,
    capacidade int not null
);

create table tb_turma
(
    id            int auto_increment
        primary key,
    nome          varchar(255) null,
    disciplina_id int          not null,
    professor_id  int          not null,
    sala_id       int          not null,
    constraint tb_turma_ibfk_1
        foreign key (disciplina_id) references tb_disciplina (id),
    constraint tb_turma_ibfk_2
        foreign key (professor_id) references tb_professor (id),
    constraint tb_turma_ibfk_3
        foreign key (sala_id) references tb_sala (id)
);

create table tb_feedback
(
    id           bigint auto_increment
        primary key,
    turma_id     int          null,
    professor_id int          null,
    aluno_id     int          null,
    titulo       varchar(255) not null,
    corpo        varchar(255) not null,
    constraint tb_feedback_ibfk_1
        foreign key (turma_id) references tb_turma (id),
    constraint tb_feedback_ibfk_2
        foreign key (professor_id) references tb_professor (id),
    constraint tb_feedback_ibfk_3
        foreign key (aluno_id) references tb_aluno (id)
);

create index aluno_id
    on tb_feedback (aluno_id);

create index professor_id
    on tb_feedback (professor_id);

create index turma_id
    on tb_feedback (turma_id);

create index discilpina_id
    on tb_turma (disciplina_id);

create index professor_id
    on tb_turma (professor_id);

create index sala_id
    on tb_turma (sala_id);

create table tb_turma_alunos
(
    id        bigint auto_increment
        primary key,
    turma_id  int    null,
    aluno_id  int    null,
    alunos_id bigint not null,
    nota      float  null,
    constraint tb_turma_alunos_ibfk_1
        foreign key (turma_id) references tb_turma (id),
    constraint tb_turma_alunos_ibfk_2
        foreign key (aluno_id) references tb_aluno (id)
);

create index aluno_id
    on tb_turma_alunos (aluno_id);

create index tb_turma_aluno_ibfk_1
    on tb_turma_alunos (turma_id);


