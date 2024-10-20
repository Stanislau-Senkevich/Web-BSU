CREATE TABLE application
(
    id               SERIAL PRIMARY KEY,
    departure        VARCHAR(255) NOT NULL,
    departure_time   DATE  NOT NULL,
    destination      VARCHAR(255) NOT NULL,
    destination_time DATE  NOT NULL
);

CREATE TABLE park_user
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    name      varchar(100) NOT NULL,
    surname   varchar(100) NOT NULL,
    birthdate varchar(20)  NOT NULL
);

CREATE TABLE car
(
    id           SERIAL PRIMARY KEY,
    driver_id    INTEGER     NOT NULL REFERENCES park_user (id),
    fix_state    VARCHAR(40) NOT NULL,
    state_number VARCHAR(20) NOT NULL
);

CREATE TABLE trip
(
    id             SERIAL PRIMARY KEY,
    departure      VARCHAR(255) NOT NULL,
    departure_time DATE         NOT NULL,
    destination    VARCHAR(255) NOT NULL,
    destination_time DATE         NOT NULL,
    driver_id      INTEGER      NOT NULL REFERENCES park_user (id),
    car_id         INTEGER      NOT NULL REFERENCES car (id),
    is_completed   BOOLEAN      NOT NULL
);
