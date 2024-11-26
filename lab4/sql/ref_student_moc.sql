create table ref_student_moc(
	id serial primary key not null,
	name varchar not null,
	surname varchar not null,
	patronymic varchar not null,
	phoneNumber varchar,
    telegram varchar,
    email varchar,
    gitHub varchar,
	constraint ref_student_ch1 check(name is null or name ~ '^[A-Z][a-z]*(-([A-Z][a-z]*)|[a-z]+)*$'),
	constraint ref_student_ch2 check(surname is null or surname ~ '^[A-Z][a-z]*(-([A-Z][a-z]*)|[a-z]+)*$'),
	constraint ref_student_ch3 check(patronymic is null or patronymic ~ '^[A-Z][a-z]*(-([A-Z][a-z]*)|[a-z]+)*$'),
	constraint ref_student_ch4 check(phoneNumber is null or phoneNumber ~ '\+7\d{10}'),
	constraint ref_student_ch5 check(telegram is null or telegram ~ '@(?=.{5,64})(?!_)(?!.*__)[a-zA-Z0-9_]+(?<![_.])'),
	constraint ref_student_ch6 check(email is null or email ~ '^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z]{2,}$'),
	constraint ref_student_ch7 check(gitHub is null or gitHub !~ '[$%#@&/?]')
);