DELETE FROM skideo_user;

INSERT INTO skideo_user(id, created, updated,
                        login, email, password,
                        role, has_agent)
  values (1, '2020-05-05', '2020-05-05',
          'test', 'test', '$2y$10$4YEQMxPRxUxWoLAU17lg9.kQxPx7uMXRjDf1FuHoJs1Zh9lHz76T.',
          'UNCONFIRMED', false);

INSERT INTO skideo_user(id, created, updated,
                        login, email, password,
                        role, has_agent)
values (2, '2020-05-06', '2020-05-06',
        'test2', 'test2', '$2y$10$4YEQMxPRxUxWoLAU17lg9.kQxPx7uMXRjDf1FuHoJs1Zh9lHz76T.',
        'UNCONFIRMED', false);


