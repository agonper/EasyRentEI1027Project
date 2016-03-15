CREATE TABLE INVOICE (
  invoiceNumber   NUMERIC(10, 0)    NOT NULL,
  actualVAT       NUMERIC(2, 0)     NOT NULL,
  address         VARCHAR(20)       NOT NULL,
  invoiceDate     DATE              NOT NULL,

  CONSTRAINT pk_invoice PRIMARY KEY (invoiceNumber)
);