CREATE TABLE services (
  service_id      NUMERIC(8, 0)   NOT NULL,
  service_name    VARCHAR(20)     NOT NULL,
  service_value   VARCHAR(20)     NOT NULL,
  user_id         VARCHAR(10)     ,
  active          BOOLEAN         NOT NULL,
  creation_date   DATE            NOT NULL,
  active_since    DATE            NOT NULL,

  CONSTRAINT pk_services PRIMARY KEY (service_id),
  CONSTRAINT fk_s_username FOREIGN KEY (user_id)
    REFERENCES users(user_id)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);