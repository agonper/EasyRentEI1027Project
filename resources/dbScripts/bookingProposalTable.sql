CREATE TABLE BOOKING_PROPOSAL (
  trackingNumber    NUMERIC(10, 0)    NOT NULL,
  propertyID        NUMERIC(8, 0)     NOT NULL,
  startDate         DATE              NOT NULL,
  endDate           DATE              NOT NULL,
  status            VARCHAR(8)        NOT NULL,
  transactionNumber NUMERIC(10, 0)    NOT NULL,
  totalAmount       FLOAT(8)          NOT NULL,
  numberOfTenants   NUMERIC(2, 0)     NOT NULL,
  dateOfCreation    DATE              NOT NULL,
  dateOfAcceptation DATE,

  CONSTRAINT pk_bookingProposal PRIMARY KEY (trackingNumber),
  CONSTRAINT fk_bp_propertyID FOREIGN KEY (propertyID)
    REFERENCES PROPERTY(propertyID)
    ON UPDATE CASCADE
    ON DELETE CASCADE,

  CONSTRAINT ir_bp_status CHECK (LOWER(status) LIKE "accepted" OR "rejected" OR "pendent")
);