BEGIN TRANSACTION;

DROP SEQUENCE IF EXISTS genre_serial CASCADE;

DROP TABLE IF EXISTS users_genre_preferences, users, genre, user_preferences, watchlist CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	email_address varchar(100) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE SEQUENCE genre_serial;
CREATE TABLE genre (
    genre_id int NOT NULL DEFAULT nextval('genre_serial'),
    genre_name varchar(50) NOT NULL,
	CONSTRAINT PK_genre PRIMARY KEY(genre_id)
);

CREATE TABLE user_preferences (
	choice_id SERIAL,
    user_id int NOT NULL,
	movie_id int NOT NULL,
	liked boolean NOT NULL,
	CONSTRAINT PK_user_preferences PRIMARY KEY(choice_id),
    CONSTRAINT FK_user_preferences_users FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE users_genre_preferences (
	user_id int NOT NULL,
	genre_id int NOT NULL,
	CONSTRAINT PK_users_genre_preferences PRIMARY KEY(user_id, genre_id),
	CONSTRAINT FK_users FOREIGN KEY(user_id) REFERENCES users(user_id),
	CONSTRAINT FK_genre FOREIGN KEY(genre_id) REFERENCES genre(genre_id)
);

CREATE TABLE watchlist (
	watchlist_id SERIAL,
	user_id int NOT NULL,
	movie_id int NOT NULL,
	movie_title varchar(100) NOT NULL,
	poster_path varchar(100) NOT NULL,
	CONSTRAINT PK_watchlist PRIMARY KEY (watchlist_id),
	CONSTRAINT FK_watchlist_users FOREIGN KEY(user_id) REFERENCES users(user_id)
);


INSERT INTO genre (genre_id, genre_name) VALUES (28, 'Action');
INSERT INTO genre (genre_id, genre_name) VALUES (12, 'Adventure');
INSERT INTO genre (genre_id, genre_name) VALUES (16, 'Animation');
INSERT INTO genre (genre_id, genre_name) VALUES (35, 'Comedy');
INSERT INTO genre (genre_id, genre_name) VALUES (80, 'Crime');
INSERT INTO genre (genre_id, genre_name) VALUES (99, 'Documentary');
INSERT INTO genre (genre_id, genre_name) VALUES (18, 'Drama');
INSERT INTO genre (genre_id, genre_name) VALUES (10751, 'Family');
INSERT INTO genre (genre_id, genre_name) VALUES (14, 'Fantasy');
INSERT INTO genre (genre_id, genre_name) VALUES (36, 'History');
INSERT INTO genre (genre_id, genre_name) VALUES (27, 'Horror');
INSERT INTO genre (genre_id, genre_name) VALUES (10402, 'Music');
INSERT INTO genre (genre_id, genre_name) VALUES (9648, 'Mystery');
INSERT INTO genre (genre_id, genre_name) VALUES (10749, 'Romance');
INSERT INTO genre (genre_id, genre_name) VALUES (878, 'Science Fiction');
INSERT INTO genre (genre_id, genre_name) VALUES (10770, 'TV Movie');
INSERT INTO genre (genre_id, genre_name) VALUES (53, 'Thriller');
INSERT INTO genre (genre_id, genre_name) VALUES (10752, 'War');
INSERT INTO genre (genre_id, genre_name) VALUES (37, 'Western');

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO final_capstone_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_owner;

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser;

INSERT INTO users (username, email_address, password_hash, role) VALUES ('user', 'user@techelevator.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, email_address, password_hash, role) VALUES ('admin', 'admin@techelevator.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

COMMIT TRANSACTION;
