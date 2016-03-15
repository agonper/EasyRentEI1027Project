CREATE TABLE PROPERTY_PHOTOS (
  propertyID          NUMERIC(8, 0)   NOT NULL,
  propertyPhotoID     NUMERIC(8, 0)   NOT NULL,

  CONSTRAINT pk_propertyPhotos PRIMARY KEY (propertyID, propertyPhotoID),
  CONSTRAINT fk_propertyPhotos FOREIGN KEY (propertyID)
    REFERENCES PROPERTY(propertyID)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);