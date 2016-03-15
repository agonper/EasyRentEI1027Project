CREATE TABLE BOOKING_INVOICE (
  trackingNumber    NUMERIC(10, 0)    NOT NULL,
  invoiceNumber     NUMERIC(10, 0)    NOT NULL,

  CONSTRAINT pk_bookingInvoice PRIMARY KEY (trackingNumber)
);