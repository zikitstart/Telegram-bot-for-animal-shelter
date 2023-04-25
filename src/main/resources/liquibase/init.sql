--liquibase formatted sql

-- changeset DeafMist:1
CREATE TYPE pet_type as enum ('CAT', 'DOG');
CREATE TYPE status as enum ('FREE', 'TRIAL', 'EXTRA_14', 'EXTRA_30', 'DENIED');

-- changeset DeafMist:2
CREATE TABLE pet
(
    pet_id BIGSERIAL PRIMARY KEY,
    age_in_months INT NOT NULL,
    name VARCHAR(30) NOT NULL,
    pet_type pet_type NOT NULL,
    user_details_id BIGINT,
    shelter_id BIGINT NOT NULL,
    pet_status status
);

-- changeset zikit:1
CREATE TABLE "user"
(
    chat_id          BIGSERIAL PRIMARY KEY,
    surname          VARCHAR(25) NOT NULL,
    first_name       VARCHAR(25),
    last_name        VARCHAR(25),
    phone_number     VARCHAR(25) NOT NULL
);

CREATE TABLE shelter
(
    shelter_id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(25) NOT NULL,
    address      VARCHAR(255) NOT NULL,
    driving_directions VARCHAR(255),
    description VARCHAR(255),
    schedule VARCHAR(255),
    rules VARCHAR(255),
    contacts VARCHAR(255) NOT NULL
);

-- changeset jokeproofee:1
CREATE TABLE volunteer
(
    chat_id          BIGSERIAL PRIMARY KEY,
    surname          VARCHAR(25) NOT NULL,
    first_name       VARCHAR(25),
    last_name        VARCHAR(25),
    phone_number     VARCHAR(25) NOT NULL
);

--changeset DeafMist:3
CREATE TABLE report
(
    report_id BIGSERIAL PRIMARY KEY,
    pet_report TEXT,
    photo BYTEA,
    date_of_report TIMESTAMP NOT NULL,
    pet_id BIGINT NOT NULL
);

CREATE TABLE user_details
(
    chat_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    pets BIGINT[]
);

CREATE TABLE shelter_volunteers
(
    volunteer_id BIGINT NOT NULL,
    shelter_id BIGINT NOT NULL,
    PRIMARY KEY (volunteer_id, shelter_id)
);

-- INSERT INTO pet
-- (name, pet_type)
-- VALUES ('Васька','cat');