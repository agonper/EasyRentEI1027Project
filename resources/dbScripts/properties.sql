CREATE TABLE properties (
  property_id     NUMERIC(8, 0)   NOT NULL,
  user_id         VARCHAR(10)     ,
  title           VARCHAR(20)     NOT NULL,
  location        VARCHAR(15)     NOT NULL,
  rooms           NUMERIC(2, 0)   NOT NULL,
  capacity        NUMERIC(2, 0)   NOT NULL,
  beds            NUMERIC(2, 0)   NOT NULL,
  bathrooms       NUMERIC(2, 0)   NOT NULL,
  floor_space     NUMERIC(3, 0)   NOT NULL,
  price_per_day   FLOAT(4)        NOT NULL,
  creation_date   DATE            NOT NULL,
  type            VARCHAR(20)     NOT NULL,
  description     VARCHAR(500)    NOT NULL,

  CONSTRAINT pk_properties PRIMARY KEY (property_id),
  CONSTRAINT fk_pr_username FOREIGN KEY (user_id)
    REFERENCES users(user_id)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);