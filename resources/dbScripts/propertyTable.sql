CREATE TABLE PROPERTY (
  propertyID      NUMERIC(8, 0)   NOT NULL,
  title           VARCHAR(20)     NOT NULL,
  location        VARCHAR(15)     NOT NULL,
  rooms           NUMERIC(2, 0)   NOT NULL,
  capacity        NUMERIC(2, 0)   NOT NULL,
  beds            NUMERIC(2, 0)   NOT NULL,
  bathrooms       NUMERIC(2, 0)   NOT NULL,
  floorSpace      NUMERIC(3, 0)   NOT NULL,
  pricePerDay     FLOAT(4)        NOT NULL,
  creationDate    DATE            NOT NULL,
  type            VARCHAR(20)     NOT NULL,
  description     VARCHAR(500)    NOT NULL,

  CONSTRAINT pk_property PRIMARY KEY (propertyID)
);