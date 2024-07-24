CREATE TABLE "funcionario" (
    "matricula" varchar(6) NOT NULL PRIMARY KEY,
    "nome" varchar(100) NOT NULL,
    "contrato" varchar(3) NOT NULL CHECK ("contrato" IN ('CLT', 'PJ')),
    "data_admissao" date NOT NULL,
    "email_institucional" varchar(100) NOT NULL,
    "gmail" varchar(100) NOT NULL,
    "senha" varchar(80) NOT NULL,
    "cargo" varchar(40) NOT NULL,
    "matricula_gestor_id" varchar(6) NULL
);
ALTER TABLE "funcionario" ADD CONSTRAINT "funcionario_matricula_gestor_id_6b1efb3d_fk_funcionario_matricula" FOREIGN KEY ("matricula_gestor_id") REFERENCES "funcionario" ("matricula") DEFERRABLE INITIALLY DEFERRED;

CREATE TABLE "solicitacao" (
    "id" serial NOT NULL PRIMARY KEY,
    "matricula_funcionario_id" varchar(6) NOT NULL REFERENCES "funcionario" ("matricula") DEFERRABLE INITIALLY DEFERRED,
    "data_inicio" date NOT NULL,
    "data_fim" date NOT NULL,
    "status" varchar(8) NOT NULL CHECK ("status" IN ('PENDENTE', 'RECUSADO', 'APROVADO')),
    "data_criacao" date NOT NULL,
    "antecipacao_salario" boolean NOT NULL,
    "comentarios" text NULL
);
