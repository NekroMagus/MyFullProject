CREATE TYPE role_on_site as ENUM (
  'ANONYMOUS',
  'USER',
  'ADMIN'
  );

CREATE TYPE role_football AS ENUM (
  'GK',
  'SW',
  'RB',
  'LB',
  'CB',
  'RBW',
  'LBW',
  'CDM',
  'RM',
  'CM',
  'LM',
  'RMM',
  'LMM',
  'CAM',
  'RF',
  'CF',
  'LF',
  'RS',
  'LS',
  'ST'
  );

CREATE TABLE users
(
  id               BIGSERIAL PRIMARY KEY,
  email            VARCHAR(255) NOT NULL,
  password         VARCHAR(255) NOT NULL,
  name             VARCHAR(64),
  surname          VARCHAR(64),
  telephone_number VARCHAR(18),
  country          VARCHAR(64),
  city             VARCHAR(256),
  role_on_the_site role_on_site,
  role_in_football role_football,
  date_of_birth        DATE,
  date_of_registration timestamp without time zone,
  social_network       VARCHAR(128)

);


