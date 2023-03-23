
create database hackernewsapp;

use hackernewsapp;

create table user(
  user_id varchar(32) not null,
  username varchar(254) not null,
  about varchar(1000) null,
  karma varchar(50) null,
  created_time timestamp not null,
	constraint user_pk primary key (user_id),
    constraint user_uk unique key (username)
);

create table story(
  story_id varchar(32) not null,
  user_id varchar(50) not null,
  descendants int not null default 0,
  title varchar(254) not null,
   score int not null default 0,
   submission_time timestamp not null,
   url varchar(1000) null,
	constraint story_pk primary key (story_id),
    constraint story_fk foreign key (user_id) references User(user_id)
);

create table comments(
  comment_id varchar(32) not null,
  user_id varchar(50) not null,
  parent_comment_id varchar(50) null,
  story_id varchar(50) not null,
  comment varchar(1000) not null,
   submission_time timestamp not null,
	constraint comment_pk primary key (comment_id),
    constraint comment_user_fk foreign key (user_id) references User(user_id),
    constraint comment_story_fk foreign key (story_id) references Story(story_id)
);

SELECT s.* FROM story s
WHERE s.submission_time >= now() - interval '15' minute order by s.score limit 10;

SELECT c1.*, COUNT(c2.comment_id) AS child_comment_count
FROM comments c1
LEFT JOIN comments c2 ON c1.comment_id = c2.parent_comment_id
WHERE c1.story_id = ''
ORDER BY child_comment_count DESC
LIMIT 10;

SELECT u.* FROM user u;
SELECT s.* FROM story s;
SELECT c.* FROM comments c;

SELECT s.* FROM story s
WHERE s.submission_time >= now() - interval '15' minute order by s.score limit 10;

select timestamp(now() - interval '15' minute);

SELECT c1.*,  cast(COUNT(c2.comment_id) as UNSIGNED) AS child_comment_count FROM comments c1 LEFT JOIN comments c2 
ON c1.comment_id = c2.parent_comment_id WHERE c1.story_id = '402880eb870b30c901870b30e6930000' 
ORDER BY child_comment_count DESC LIMIT 10;




