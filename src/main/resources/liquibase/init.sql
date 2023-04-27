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
    pet_status status NOT NULL
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
    name         VARCHAR(30) NOT NULL,
    address      TEXT NOT NULL,
    driving_directions TEXT,
    description TEXT,
    schedule TEXT,
    rules TEXT,
    contacts TEXT NOT NULL
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

--changeset DeafMist:4
INSERT INTO shelter (shelter_id, name, address, driving_directions, description, schedule, rules, contacts)
VALUES (1, 'Муркоша',
        'Адрес: Москва, ул. Осташковская, д. 14 стр. 2.',
        'Выход из метро со стороны 1-ого вагона. Идите направо до светофора, затем перейдите узкую улочку, ' ||
        'далее еще раз перейдите дорогу и двигайтесь налево до второй по ходу движения остановки. ' ||
        '3 остановки на 601 автобусе от остановки \"м. Медведково\" (Широкая улица, 16А) ' ||
        'до остановки "Погранинститут". Переходим на другую сторону дороги и двигаемся налево 100 метров до ' ||
        'проходной (коричневое одноэтажное здание по адресу ул. Осташковская 14с6).',
        '"Муркоша" - единственный adoption центр для кошек в России, ' ||
        'который комплексно подходит к вопросу спасения животных. ' ||
        'Все сотрудники нашей команды – в прошлом волонтеры, ' ||
        'за плечами каждого из них десятки спасенных жизней. ' ||
        'На собственном опыте, методом проб и ошибок, мы разработали систему, при которой у любой кошки – ' ||
        'будь это возрастной питомец или животное-инвалид – появляется шанс стать домашней и счастливой.',
        'Время работы: ежедневно с 9.00 до 21.00 без перерывов, выходных и праздничных дней.',
        'Обязательно захватите с собой паспорт, на территории действует пропускная система. ' ||
        'Документ, удостоверяющий личность, должен быть у каждого посетителя. ' ||
        'С проходной позвоните нам со стационарного телефона, мы закажем вам пропуск.',
        'Телефон для получения пропуска: 8 (495) 023-02-29'
);

INSERT INTO shelter (shelter_id, name, address, driving_directions, description, schedule, rules, contacts)
VALUES (2, 'Приют Бирюлево',
        'Адрес: Москва, Востряковский пр-д, 10А.',
        'Сначала доехать до станции метро Академика Янгеля, оттуда дойти до ст. МЦД Красный Строитель, ' ||
        'а потом 20 минут пешком - и вы на месте!',
        'Приют Бирюлево - это муниципальный приют для бездомных собак и кошек в Южном округе г. Москвы. ' ||
        'В нем живет почти 2500 собак. Большие и маленькие, пушистые и гладкие, веселые и задумчивые - ' ||
        'и на всех одна большая мечта - встретить своего Человека и найти Дом.',
        'Среда-воскресенье с 10:00 до 18:00.',
        'Мы - сообщество волонтеров и находимся в приюте нерегулярно. ' ||
        'Поэтому просим вас планировать ваш визит заранее и приезжать только договорившись о встрече по ' ||
        'почте.',
        'Для согласования всех деталей пишите на почту: sobaka@izpriuta.ru'
);

INSERT INTO pet (age_in_months, name, pet_type, shelter_id, pet_status)
VALUES (3, 'Васька', 'CAT', 1, 'FREE');

INSERT INTO pet (age_in_months, name, pet_type, shelter_id, pet_status)
VALUES (11, 'Мурка', 'CAT', 1, 'FREE');

INSERT INTO pet (age_in_months, name, pet_type, shelter_id, pet_status)
VALUES (5, 'Шарик', 'DOG', 2, 'FREE');

INSERT INTO pet (age_in_months, name, pet_type, shelter_id, pet_status)
VALUES (17, 'Кошмарик', 'DOG', 2, 'FREE');