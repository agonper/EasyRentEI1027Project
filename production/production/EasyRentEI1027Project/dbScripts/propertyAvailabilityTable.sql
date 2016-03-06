CREATE TABLE PROPERTY_AVAILABILITY (
  propertyID NUMERIC(8) NOT NULL,
  startDate DATE,
  endDate DATE,

  CONSTRAINT pk_propertyAvailability PRIMARY KEY (propertyID),
  CONSTRAINT fk_propertyAvailability FOREIGN KEY (propertyID),
  CONSTRAINT ur_propertyID
);