CREATE TABLE booking_proposals (
  proposal_id         UUID              NOT NULL,
  property_id         UUID              NOT NULL,
  tenant_id           UUID              NOT NULL,
  start_date          DATE              NOT NULL,
  end_date            DATE              NOT NULL,
  status              VARCHAR(8)        NOT NULL,
  payment_reference   NUMERIC(10, 0)    NOT NULL,
  total_amount        FLOAT(8)          NOT NULL,
  number_of_tenants   NUMERIC(2, 0)     NOT NULL,
  date_of_creation    DATE              NOT NULL,
  date_of_acceptation DATE              ,

  CONSTRAINT pk_booking_proposals PRIMARY KEY (proposal_id),
  CONSTRAINT fk_bp_property_id FOREIGN KEY (property_id)
    REFERENCES properties(property_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,

  CONSTRAINT fk_bp_tenant_id FOREIGN KEY (tenant_id)
    REFERENCES users(user_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,

  CONSTRAINT ir_bp_status CHECK (status LIKE 'accepted' OR status LIKE 'rejected' OR status LIKE 'pendent')
);