CREATE TABLE invoices (
  invoice_id       UUID              NOT NULL,
  invoice_number   SERIAL            NOT NULL,
  proposal_id      UUID              NOT NULL,
  actual_vat       NUMERIC(2, 0)     NOT NULL,
  address          VARCHAR(20)       NOT NULL,
  invoice_date     DATE              NOT NULL,

  CONSTRAINT pk_invoices PRIMARY KEY (invoice_id),
  CONSTRAINT ak_invoices UNIQUE (invoice_number),

  CONSTRAINT fk_i_tracking_number FOREIGN KEY (proposal_id)
    REFERENCES booking_proposals(proposal_id)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);