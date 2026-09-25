-- ============================================================
-- SISTEMA GERENCIADOR DE BIBLIOTECA
-- PostgreSQL
-- ============================================================

-- ============================================================
-- LIMPEZA
-- ============================================================

DROP TABLE IF EXISTS multas CASCADE;
DROP TABLE IF EXISTS devolucoes CASCADE;
DROP TABLE IF EXISTS emprestimos CASCADE;
DROP TABLE IF EXISTS exemplares CASCADE;
DROP TABLE IF EXISTS periodicos CASCADE;
DROP TABLE IF EXISTS livros CASCADE;
DROP TABLE IF EXISTS editoras CASCADE;
DROP TABLE IF EXISTS funcionarios CASCADE;
DROP TABLE IF EXISTS alunos CASCADE;
DROP TABLE IF EXISTS usuarios CASCADE;


-- ============================================================
-- USUARIOS
-- ============================================================

CREATE TABLE usuarios (

    id BIGSERIAL PRIMARY KEY,

    nome VARCHAR(150) NOT NULL,

    usuario VARCHAR(80) NOT NULL,

    senha VARCHAR(255) NOT NULL,

    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uk_usuarios_usuario
        UNIQUE (usuario)
);


-- ============================================================
-- ALUNOS
-- ============================================================

CREATE TABLE alunos (

    id BIGSERIAL PRIMARY KEY,

    nome VARCHAR(150) NOT NULL,

    matricula VARCHAR(50) NOT NULL,

    email VARCHAR(150),

    telefone VARCHAR(30),

    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uk_alunos_matricula
        UNIQUE (matricula)
);


-- ============================================================
-- FUNCIONARIOS
-- ============================================================

CREATE TABLE funcionarios (

    id BIGSERIAL PRIMARY KEY,

    nome VARCHAR(150) NOT NULL,

    matricula VARCHAR(50) NOT NULL,

    email VARCHAR(150),

    telefone VARCHAR(30),

    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uk_funcionarios_matricula
        UNIQUE (matricula)
);


-- ============================================================
-- EDITORAS
-- ============================================================

CREATE TABLE editoras (

    id BIGSERIAL PRIMARY KEY,

    nome VARCHAR(150) NOT NULL,

    cidade VARCHAR(100),

    estado VARCHAR(2),

    email VARCHAR(150),

    telefone VARCHAR(30),

    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uk_editoras_nome
        UNIQUE (nome),

    CONSTRAINT ck_editoras_estado
        CHECK (
            estado IS NULL
            OR char_length(estado) = 2
        )
);


-- ============================================================
-- LIVROS
-- ============================================================

CREATE TABLE livros (

    id BIGSERIAL PRIMARY KEY,

    titulo VARCHAR(200) NOT NULL,

    autor VARCHAR(150) NOT NULL,

    isbn VARCHAR(20) NOT NULL,

    assunto VARCHAR(150),

    editora_id BIGINT NOT NULL,

    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uk_livros_isbn
        UNIQUE (isbn),

    CONSTRAINT fk_livros_editoras
        FOREIGN KEY (editora_id)
        REFERENCES editoras(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);


-- ============================================================
-- EXEMPLARES
-- ============================================================

CREATE TABLE exemplares (

    id BIGSERIAL PRIMARY KEY,

    codigo VARCHAR(50) NOT NULL,

    livro_id BIGINT NOT NULL,

    situacao VARCHAR(30) NOT NULL DEFAULT 'DISPONIVEL',

    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uk_exemplares_codigo
        UNIQUE (codigo),

    CONSTRAINT fk_exemplares_livros
        FOREIGN KEY (livro_id)
        REFERENCES livros(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT ck_exemplares_situacao
        CHECK (
            situacao IN (
                'DISPONIVEL',
                'EMPRESTADO',
                'MANUTENCAO',
                'EXTRAVIADO'
            )
        )
);


-- ============================================================
-- PERIODICOS
-- ============================================================

CREATE TABLE periodicos (

    id BIGSERIAL PRIMARY KEY,

    titulo VARCHAR(200) NOT NULL,

    issn VARCHAR(20) NOT NULL,

    editora VARCHAR(150),

    periodicidade VARCHAR(50),

    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uk_periodicos_issn
        UNIQUE (issn)
);


-- ============================================================
-- EMPRESTIMOS
-- ============================================================

CREATE TABLE emprestimos (

    id BIGSERIAL PRIMARY KEY,

    aluno_id BIGINT NOT NULL,

    exemplar_id BIGINT NOT NULL,

    data_emprestimo DATE NOT NULL DEFAULT CURRENT_DATE,

    data_prevista_devolucao DATE NOT NULL,

    status VARCHAR(30) NOT NULL DEFAULT 'ABERTO',

    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_emprestimos_alunos
        FOREIGN KEY (aluno_id)
        REFERENCES alunos(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT fk_emprestimos_exemplares
        FOREIGN KEY (exemplar_id)
        REFERENCES exemplares(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT ck_emprestimos_status
        CHECK (
            status IN (
                'ABERTO',
                'DEVOLVIDO',
                'ATRASADO',
                'CANCELADO'
            )
        ),

    CONSTRAINT ck_emprestimos_datas
        CHECK (
            data_prevista_devolucao >= data_emprestimo
        )
);


-- ============================================================
-- DEVOLUCOES
-- ============================================================

CREATE TABLE devolucoes (

    id BIGSERIAL PRIMARY KEY,

    emprestimo_id BIGINT NOT NULL,

    data_devolucao DATE NOT NULL DEFAULT CURRENT_DATE,

    observacao VARCHAR(500),

    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uk_devolucoes_emprestimo
        UNIQUE (emprestimo_id),

    CONSTRAINT fk_devolucoes_emprestimos
        FOREIGN KEY (emprestimo_id)
        REFERENCES emprestimos(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);


-- ============================================================
-- MULTAS
-- ============================================================

CREATE TABLE multas (

    id BIGSERIAL PRIMARY KEY,

    aluno_id BIGINT NOT NULL,

    emprestimo_id BIGINT NOT NULL,

    valor NUMERIC(10,2) NOT NULL,

    data_geracao DATE NOT NULL DEFAULT CURRENT_DATE,

    paga BOOLEAN NOT NULL DEFAULT FALSE,

    data_pagamento DATE,

    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uk_multas_emprestimo
        UNIQUE (emprestimo_id),

    CONSTRAINT fk_multas_alunos
        FOREIGN KEY (aluno_id)
        REFERENCES alunos(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT fk_multas_emprestimos
        FOREIGN KEY (emprestimo_id)
        REFERENCES emprestimos(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT ck_multas_valor
        CHECK (valor >= 0),

    CONSTRAINT ck_multas_pagamento
        CHECK (
            (paga = FALSE AND data_pagamento IS NULL)
            OR
            (paga = TRUE AND data_pagamento IS NOT NULL)
        )
);


-- ============================================================
-- INDICES
-- ============================================================

CREATE INDEX idx_livros_editora
    ON livros(editora_id);

CREATE INDEX idx_exemplares_livro
    ON exemplares(livro_id);

CREATE INDEX idx_exemplares_situacao
    ON exemplares(situacao);

CREATE INDEX idx_emprestimos_aluno
    ON emprestimos(aluno_id);

CREATE INDEX idx_emprestimos_exemplar
    ON emprestimos(exemplar_id);

CREATE INDEX idx_emprestimos_status
    ON emprestimos(status);

CREATE INDEX idx_emprestimos_data_emprestimo
    ON emprestimos(data_emprestimo);

CREATE INDEX idx_emprestimos_data_prevista
    ON emprestimos(data_prevista_devolucao);

CREATE INDEX idx_devolucoes_data
    ON devolucoes(data_devolucao);

CREATE INDEX idx_multas_aluno
    ON multas(aluno_id);

CREATE INDEX idx_multas_paga
    ON multas(paga);

CREATE INDEX idx_multas_data_geracao
    ON multas(data_geracao);


-- ============================================================
-- DADOS INICIAIS
-- ============================================================

INSERT INTO usuarios (
    nome,
    usuario,
    senha,
    ativo
)
VALUES (
    'Administrador',
    'admin',
    '$2a$10$7EqJtq98hPqEX7fNZaFWoO5XKqKXz3uYQhN1r8qfN8xJQKfY8aW7S',
    TRUE
);


-- ============================================================
-- FIM
-- ============================================================