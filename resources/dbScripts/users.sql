CREATE TABLE users (
  user_id             UUID          NOT NULL,
  username            VARCHAR(20)   NOT NULL,
  national_document   VARCHAR(9)    NOT NULL,
  role                VARCHAR(6)    NOT NULL,
  password            VARCHAR(10)   NOT NULL,
  name                VARCHAR(10)   NOT NULL,
  surnames            VARCHAR(20)   NOT NULL,
  email               VARCHAR(20)   NOT NULL,
  phone_number        VARCHAR(13)   ,
  country             VARCHAR(20)   ,
  post_address        VARCHAR(50)   ,
  post_code           NUMERIC(5, 0) ,
  sign_up_date        DATE          NOT NULL,
  active              BOOLEAN       NOT NULL,
  deactived_since     DATE          ,

  CONSTRAINT pk_users PRIMARY KEY (user_id),
  CONSTRAINT ak_u_username UNIQUE (username),
  CONSTRAINT ak_u_national_document UNIQUE (national_document)
);