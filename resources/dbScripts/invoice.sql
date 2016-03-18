CREATE TABLE invoice (
  invoice_number   NUMERIC(10, 0)    NOT NULL,
  proposal_id      NUMERIC(10, 0)    NOT NULL,
  actual_vat       NUMERIC(2, 0)     NOT NULL,
  address          VARCHAR(20)       NOT NULL,
  invoice_date     DATE              NOT NULL,

  CONSTRAINT pk_invoice PRIMARY KEY (invoice_number),

  CONSTRAINT fk_i_tracking_number FOREIGN KEY (proposal_id)
    REFERENCES booking_proposal(proposal_id)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);