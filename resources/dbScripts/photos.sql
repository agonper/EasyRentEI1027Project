CREATE TABLE photos (
  photo_id      UUID             NOT NULL,
  upload_date   DATE             NOT NULL,
  filename      VARCHAR(50)      NOT NULL,

  CONSTRAINT pk_photos PRIMARY KEY (photo_id)
);