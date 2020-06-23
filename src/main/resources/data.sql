drop sequence if exists seq_correntista;
drop table if exists TB_CORRENTISTA;
drop sequence if exists seq_conta_corrente;
drop table if exists TB_CONTA_CORRENTE;

CREATE SEQUENCE seq_conta_corrente minvalue 1
    maxvalue 9999999999
    start with 1
              increment by 1
              nocache
              cycle;

CREATE TABLE TB_CONTA_CORRENTE
(
    ID_conta_corrente         NUMBER(19,0) NOT NULL,
    nu_saldo          Number(10,2) NOT NULL,
    nu_limite          NUMBER(10,0) NOT NULL,
    CONSTRAINT conta_pk PRIMARY KEY (ID_conta_corrente)


);

CREATE SEQUENCE seq_correntista minvalue 1
    maxvalue 9999999999
    start with 1
              increment by 1
              nocache
              cycle;

CREATE TABLE TB_CORRENTISTA
(
    ID_correntista         NUMBER(19,0) NOT NULL,
    ds_nome            VARCHAR2 NOT NULL,
    ds_cpf           VARCHAR2 NOT NULL,
    ds_telefone            VARCHAR2 NOT NULL,
    ID_conta_corrente         NUMBER(19,0) NOT NULL,
    ds_endereco           VARCHAR2 NOT NULL,
    CONSTRAINT correntista_pk PRIMARY KEY (ID_correntista),
    CONSTRAINT ID_conta_fk FOREIGN KEY (ID_conta_corrente) REFERENCES TB_CONTA_CORRENTE(ID_conta_corrente)

);

