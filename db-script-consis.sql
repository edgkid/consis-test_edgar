CREATE TABLE person(

	id serial primary key,
	dni varchar,
	name varchar,
	lastname varchar,
	phone varchar,
	birthday date,
	address varchar
);

CREATE TABLE user_client(

 	id serial primary key,
	password varchar,
	status integer,
	person_id integer
);

ALTER TABLE user_client ADD CONSTRAINT fk_user_by_person 
	FOREIGN KEY (person_id) REFERENCES person (id);

CREATE TABLE type_account(

	id serial primary key,
	name varchar
);

CREATE TABLE account(
	
	id serial primary key,
	account_number varchar,
	amount float,
	type_account_id integer,
	person_id integer
	
);

ALTER TABLE account ADD CONSTRAINT fk_type_account_by_account 
	FOREIGN KEY (type_account_id) REFERENCES type_account (id);
	
ALTER TABLE account ADD CONSTRAINT fk_person_by_account 
	FOREIGN KEY (person_id) REFERENCES person (id);

CREATE TABLE account_record_type(
	id serial primary key,
	name varchar
);

CREATE TABLE account_record(
	
	id serial primary key,
	record_date date,
	amount float,
	amount_value float,
	account_record_type_id integer,
	account_id integer
);

ALTER TABLE account_record ADD CONSTRAINT fk_account_record_type_by_record 
	FOREIGN KEY (account_record_type_id) REFERENCES account_record_type (id);
ALTER TABLE account_record ADD CONSTRAINT fk_account_record_by_account 
	FOREIGN KEY (account_id) REFERENCES account (id);

ALTER TABLE person
ADD CONSTRAINT person_dni_inique UNIQUE (dni);

ALTER TABLE account
ADD CONSTRAINT account_number_inique UNIQUE (account_number);



--////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
--///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
insert into person (birthday, dni, name, lastname, phone, address) values('12/01/1970', '10946654','Jose', 'Lema', '98254785', 'Oavalon y prinicpal');
insert into person (birthday, dni, name, lastname, phone, address) values('21/01/1980', '13946781','Laura', 'Palacio', '98254785', 'Oavalon y prinicpal');
insert into person (birthday, dni, name, lastname, phone, address) values('09/06/1990', '24946781', 'Alan', 'brito', '98254785', 'Oavalon y prinicpal');

insert into type_account(name)values('Corriente');
insert into type_account(name)values('Ahorro');

insert into account_record_type (name)values('Abono');
insert into account_record_type (name)values('Debito');

insert into user_client (password, status, person_id) values(E'123456',1,1); 
insert into user_client (password, status, person_id) values(E'123456',1,2); 
insert into user_client (password, status, person_id) values(E'123456',1,3); 

insert into account (account_number, amount, type_account_id, person_id) values ('12300045678900101235', 12500, 1, 1);
insert into account (account_number, amount, type_account_id, person_id) values ('12300045678900101238', 120590, 1, 2);

commit;