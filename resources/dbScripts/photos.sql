CREATE TABLE photos (
  id            UUID             NOT NULL,
  upload_date   DATE             NOT NULL,
  filename      VARCHAR(100)     NOT NULL,

  CONSTRAINT pk_photos PRIMARY KEY (id)
);