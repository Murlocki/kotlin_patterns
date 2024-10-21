insert into ref_student(surname,name,patronymic) values ('Fff','Fff','Fff');
insert into ref_student(surname,name,patronymic) values ('Mur','Mur','Mur');
insert into ref_student(surname,name,patronymic) values ('Faf','Faf','Faf');
insert into ref_student(surname,name,patronymic,gitHub) values ('Ffe','Ffe','Ffe','ffff');
insert into ref_student(surname,name,patronymic,telegram) values ('Faf','Fff','Fff','@fafer');
insert into ref_student(surname,name,patronymic,phoneNumber) values ('Fer','Fer','Fer','+79888889898');
insert into ref_student(surname,name,patronymic,email) values ('Kir','Kir','Kir','kk@gmail.com');

SELECT * FROM pg_settings WHERE name IN ('listen_addresses', 'port');
select * from ref_student;