--liquibase formatted sql

--changeset DeafMist:1
-- CREATE TABLE pet
-- (
--     id BIGSERIAL PRIMARY KEY,
--     age_in_months INT,
--     name VARCHAR(30),
--     pet_type VARCHAR(3) NOT NULL ,
--     user_id BIGINT,
--     shelter_id BIGINT
-- );