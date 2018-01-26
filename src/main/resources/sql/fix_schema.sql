alter table "user"
drop NAZWISKO

alter table "user"
add LOGIN VARCHAR(255) 

alter table "user"
drop IMIE

alter table "user"
drop NUMER_ALBUMU

alter table user_group
add OPIS VARCHAR(255) 

alter table course
add OPIS VARCHAR(255) 

alter table template
add OPIS VARCHAR(255) 

alter table "user"
add OPIS VARCHAR(255) 

alter table edition
drop ROCZNIK

