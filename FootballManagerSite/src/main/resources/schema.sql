CREATE TABLE users
(
  id                   BIGINT PRIMARY KEY AUTO_INCREMENT,
  username             varchar(32)  NOT NULL,
  password             varchar(255) NOT NULL,
  email                varchar(64),
  name                 varchar(32),
  surname              varchar(32),
  telephone_number     varchar(18),
  address              varchar(256),
  role_on_the_site     ENUM ('ANONYMOUS','USER','ADMIN'),
  role_in_football     ENUM ('GK','SW','RB','LB','CB','RBW','LBW','CDM','RM','CM',
    'LM','RMM','LMM','CAM','RF','CF','LF','RS','LS','ST'),
  date_of_birth        DATE,
  date_of_registration DATETIME,
  social_network       VARCHAR(128)
) DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE user_photo
(
  id          BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name        varchar(64) NOT NULL,
  type        varchar(32) NOT NULL,
  description varchar(1024),
  content     LONGBLOB    NOT NULL
);

CREATE TABLE user_video
(
  id          BIGINT        NOT NULL PRIMARY KEY AUTO_INCREMENT,
  url         VARCHAR(2048) NOT NULL,
  description VARCHAR(1024)
);