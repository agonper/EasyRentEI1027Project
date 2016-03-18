CREATE TABLE properties_services (
  property_id      NUMERIC(8, 0)   NOT NULL,
  service_id       NUMERIC(8, 0)   NOT NULL,
  offered_since    DATE            NOT NULL,

  CONSTRAINT pk_property_services PRIMARY KEY (property_id, service_id),
  CONSTRAINT fk_ps_property_id FOREIGN KEY (property_id)
    REFERENCES properties(property_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,

  CONSTRAINT fk_ps_service_id FOREIGN KEY (service_id)
    REFERENCES services(service_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);