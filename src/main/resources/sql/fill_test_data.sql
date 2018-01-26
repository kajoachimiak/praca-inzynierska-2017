INSERT INTO course (name, description)
values('SSBD', "Wpis testowy");

INSERT INTO edition (name, description, course_id)
values('Edition 1', "Wpis testowy", 2);

INSERT INTO user_group (name, description, edition_id)
values('Group 1', "Wpis testowy", 3);

INSERT INTO "user"(description, login, group_id)
values('Wpis testowy','198955', 4);


INSERT INTO template (user_id, group_id, edition_id,course_id, name, content)
values(2,4,3,2, 'test','echo test');