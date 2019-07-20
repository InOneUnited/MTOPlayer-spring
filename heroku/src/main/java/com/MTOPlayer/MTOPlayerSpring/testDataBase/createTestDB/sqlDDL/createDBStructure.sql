create table if not exists login
(
	id serial not null
		constraint login_pk
			primary key,
	login varchar(255) not null,
	email varchar(255) not null
);

alter table login owner to player;

create table if not exists apikey
(
	id serial not null
		constraint apikeys_pkey
			primary key,
	token varchar(255) not null,
	login_id integer not null
		constraint fk8k5p3e1k9dy4uja9sihta2h4w
			references login
				on update cascade on delete cascade,
	api_username varchar(255) not null,
	api_name varchar(255) not null
);

alter table apikey owner to player;

create table if not exists password
(
	id serial not null
		constraint passwords_pkey
			primary key,
	password varchar(255) not null,
	login_id integer not null
		constraint fkfwkvav5c9yg37fqsrmqqp1fkr
			references login
				on update cascade on delete cascade
);

alter table password owner to player;

create unique index if not exists password_login_id_uindex
	on password (login_id);

create table if not exists playlist
(
	id serial not null
		constraint playlists_pkey
			primary key,
	creation_day date not null,
	name varchar(255) not null,
	login_id integer not null
		constraint fk1ubcp0b37c4mm8bfa539divc7
			references login,
	creation_time time not null
);

alter table playlist owner to player;

create table if not exists salt
(
	id serial not null
		constraint salt_pkey
			primary key,
	salt varchar(255) not null,
	password_id integer not null
		constraint fkmxjy0kqpuhb5u6dicmgyk842y
			references password
				on update cascade on delete cascade
);

alter table salt owner to player;

create unique index if not exists salt_password_id_uindex
	on salt (password_id);

create unique index if not exists salt_salt_uindex
	on salt (salt);

create table if not exists session
(
	id serial not null
		constraint sessions_pkey
			primary key,
	expiration_day date not null,
	start_day date not null,
	login_id integer not null
		constraint fkksxkv8clo17639d9df2khf30n
			references login
				on update cascade on delete cascade,
	start_time time not null,
	expiration_time time not null,
	ip text not null
);

alter table session owner to player;

create table if not exists song
(
	id serial not null
		constraint songs_pkey
			primary key,
	link varchar(255) not null,
	name varchar(255) not null,
	playlist_id integer not null
		constraint fk39ob1yiehwqeld3sifva49ygn
			references playlist
				on update cascade on delete cascade,
	apikey_id integer not null
		constraint songs_apikeys_id_fk
			references apikey
				on update cascade on delete cascade
);

alter table song owner to player;

create table if not exists user_info
(
	id serial not null
		constraint users_pkey
			primary key,
	first_name varchar(255),
	gender varchar(255),
	birthday date,
	last_name varchar(255),
	join_date date not null,
	login_id serial not null
		constraint user_info_login_id_fk
			references login
				on update cascade on delete cascade
);

alter table user_info owner to player;

create unique index if not exists user_info_login_id_uindex
	on user_info (login_id);

create unique index if not exists login_email_uindex
	on login (email);

create unique index if not exists login_id_uindex
	on login (id);

create unique index if not exists login_login_uindex
	on login (login);

