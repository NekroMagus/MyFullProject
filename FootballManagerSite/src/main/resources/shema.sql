CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  login varchar(32) NOT NULL,
  password varchar(32) NOT NULL,
  email varchar(64) NOT NULL ,
  name varchar(32),
  surname varchar(32),
  telephone_number varchar(18),
  address varchar(256),
  role_on_the_site ENUM('ANONYMOUS', 'USER','ADMIN'),
  role_in_football ENUM('GK','SW','RB','LB','CB','RBW','LBW','CDM','RM','CM',
                        'LM','RMM','LMM','CAM','RF','CF','LF','RS','LS','ST'),
  date_of_birth DATE,
  date_of_registration DATETIME,
  social_network VARCHAR(128)
);