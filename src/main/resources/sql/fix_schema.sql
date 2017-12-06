alter table UCZESTNIK
drop NAZWISKO

alter table UCZESTNIK
add LOGIN VARCHAR(255) 

alter table UCZESTNIK
drop IMIE

alter table UCZESTNIK
drop NUMER_ALBUMU

alter table GRUPA
add OPIS VARCHAR(255) 

alter table PRZEDMIOT
add OPIS VARCHAR(255) 

alter table SZABLONY
add OPIS VARCHAR(255) 

alter table UCZESTNIK
add OPIS VARCHAR(255) 

alter table EDYCJA
drop ROCZNIK

