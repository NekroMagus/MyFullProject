DELETE from scout;
DELETE from skideo_post;
DELETE from club;
DELETE from likes;
DELETE from videos;
DELETE from skideo_user;

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

INSERT INTO videos(id,user_id) values(1,2);

INSERT INTO likes(id,user_id,video_id,rating) values (1,1,1,'ONE');

INSERT INTO club(id,login,password,title_club,logo_link) values (1,'apache','password','title','link');

INSERT INTO skideo_post(id,agent) values (1,false);

INSERT INTO scout(id,login,password,region,club_id,name) values (1,'apache','password','region',1,'name');
