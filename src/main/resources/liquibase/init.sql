--liquibase formatted sql
DROP TABLE IF EXISTS pet;
DROP TABLE IF EXISTS "user";
DROP TABLE IF EXISTS shelter;
DROP TABLE IF EXISTS info;
DROP TABLE IF EXISTS volunteer;

-- changeset DeafMist:1
CREATE TABLE pet
(
    id BIGSERIAL PRIMARY KEY,
    age_in_months INT,
    name VARCHAR(30),
    pet_type VARCHAR(3) NOT NULL ,
    user_id BIGINT,
    shelter_id BIGINT
);

-- changeset zikit:1
CREATE TABLE "user"
    (
    chat_id          BIGSERIAL PRIMARY KEY,
    surname          VARCHAR(25),
    first_name       VARCHAR(25),
    last_name        VARCHAR(25),
    phone_number     VARCHAR(25)
    );

CREATE TABLE shelter
    (
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(25),
    address      VARCHAR(255),
    driving_directions VARCHAR(255),
    description VARCHAR(255),
    schedule VARCHAR(255),
    rules VARCHAR(255),
    contacts VARCHAR(255)
    );

-- changeset jokeproofee:1
CREATE TABLE info
(
    id               BIGSERIAL PRIMARY KEY,
    recommendations  VARCHAR(255),
    instructions     VARCHAR(255)
);

CREATE TABLE volunteer
(
    chat_id          BIGSERIAL PRIMARY KEY,
    surname          VARCHAR(25),
    first_name       VARCHAR(25),
    last_name        VARCHAR(25),
    phone_number     VARCHAR(25)
);

INSERT INTO pet
(name, pet_type)
VALUES ('Васька','CAT');