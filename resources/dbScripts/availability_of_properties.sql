CREATE TABLE availability_of_properties (
  property_id   UUID          NOT NULL,
  start_date    DATE          NOT NULL,
  end_date      DATE          ,

  CONSTRAINT pk_availability_of_properties PRIMARY KEY (property_id),
  CONSTRAINT fk_aop_property_id FOREIGN KEY (property_id)
    REFERENCES properties(property_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,

  CONSTRAINT ir_aop_dates CHECK (availability_of_properties.start_date < availability_of_properties.end_date)
);