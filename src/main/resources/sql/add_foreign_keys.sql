alter table edition add PRZEDMIOT_ID INT;
alter table edition add FOREIGN KEY (course_id) REFERENCES course(id);

alter table user_group add EDYCJA_ID INT;
alter table user_group add FOREIGN KEY (edition_id) REFERENCES edition(id);

alter table "user" add group_id INT;
alter table "user" add FOREIGN KEY (group_id) REFERENCES user_group(id);

alter table template add FOREIGN KEY (user_id) REFERENCES user(id);
alter table template add FOREIGN KEY (group_id) REFERENCES user_group(id);
alter table template add FOREIGN KEY (edition_id) REFERENCES edition(id);
alter table template add FOREIGN KEY (course_id) REFERENCES course(id);
