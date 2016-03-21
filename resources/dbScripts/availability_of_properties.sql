CREATE TABLE availability_of_properties (
  id            UUID          NOT NULL,
  start_date    DATE          NOT NULL,
  end_date      DATE          ,

  CONSTRAINT pk_availability_of_properties PRIMARY KEY (id),
  CONSTRAINT fk_aop_property_id FOREIGN KEY (id)
    REFERENCES properties(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,

  CONSTRAINT ir_aop_dates CHECK (availability_of_properties.start_date < availability_of_properties.end_date)
);