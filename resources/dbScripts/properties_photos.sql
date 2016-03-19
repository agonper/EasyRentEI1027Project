CREATE TABLE properties_photos (
  photo_id            UUID     NOT NULL,
  property_id         UUID     NOT NULL,

  CONSTRAINT pk_properties_photos PRIMARY KEY (photo_id, property_id),

  CONSTRAINT fk_prph_photo_id FOREIGN KEY (photo_id)
    REFERENCES photos(photo_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,

  CONSTRAINT fk_prph_property_id FOREIGN KEY (property_id)
    REFERENCES properties(property_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);