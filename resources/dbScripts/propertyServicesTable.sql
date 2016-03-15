CREATE TABLE PROPERTY_SERVICES (
  propertyID      NUMERIC(8, 0)   NOT NULL,
  service         VARCHAR(20)     NOT NULL,

  CONSTRAINT pk_propertyServices PRIMARY KEY (propertyID, service),
  CONSTRAINT fk_ps_propertyID FOREIGN KEY (propertyID)
    REFERENCES PROPERTY(propertyID)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);