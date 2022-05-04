CREATE TABLE despesa
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    descricao VARCHAR(255)          NULL,
    valor     DECIMAL               NULL,
    data      date                  NULL,
    categoria VARCHAR(255)          NULL,
    CONSTRAINT pk_despesa PRIMARY KEY (id)
);

CREATE TABLE receita
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    descricao VARCHAR(255)          NULL,
    valor     DECIMAL               NULL,
    data      date                  NULL,
    CONSTRAINT pk_receita PRIMARY KEY (id)
);