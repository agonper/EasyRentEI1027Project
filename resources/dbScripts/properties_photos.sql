CREATE TABLE properties_photos (
  property_photo_id   NUMERIC(8, 0)   NOT NULL,
  property_id         NUMERIC(8, 0)   NOT NULL,

  CONSTRAINT pk_properties_photos PRIMARY KEY (property_photo_id, property_id),
  CONSTRAINT fk_prph_property_photo_id FOREIGN KEY (property_photo_id)
    REFERENCES photos(property_photo_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,

  CONSTRAINT fk_prph_property_id FOREIGN KEY (property_id)
    REFERENCES properties(property_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);