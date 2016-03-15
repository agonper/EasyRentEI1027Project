CREATE TABLE USER (
  username            VARCHAR(10)   NOT NULL,
  nationalDocument    VARCHAR(9)    NOT NULL,
  type                VARCHAR(6)    NOT NULL,
  password            VARCHAR(10)   NOT NULL,
  name                VARCHAR(10)   NOT NULL,
  surnames            VARCHAR(20)   NOT NULL,
  email               VARCHAR(20)   NOT NULL,
  phoneNumber         NUMERIC(9, 0) NOT NULL,
  postAddress         NUMERIC(5, 0) NOT NULL,
  signUpDate          DATE          NOT NULL,

  CONSTRAINT pk_user PRIMARY KEY (username),
  CONSTRAINT ak_user UNIQUE (nationalDocument),

  CONSTRAINT ir_userType CHECK (LOWER("USER".type) LIKE "owner" OR "tenant")
);