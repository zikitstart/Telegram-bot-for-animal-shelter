--liquibase formatted sql

-- changeset DeafMist:1
CREATE TYPE pet_type as enum ('CAT', 'DOG');
CREATE TYPE status as enum ('TRIAL', 'EXTRA_14', 'EXTRA_30', 'DENIED', 'ACCEPTED', 'WAIT_FOR_DECISION');

-- changeset DeafMist:2
CREATE TABLE pet
(
    pet_id BIGSERIAL PRIMARY KEY,
    age_in_months INT NOT NULL,
    name VARCHAR(30) NOT NULL,
    pet_type pet_type NOT NULL,
    user_details_id BIGINT,
    shelter_id      BIGINT      NOT NULL
);

-- changeset zikit:1
CREATE TABLE client
(
    client_id BIGSERIAL PRIMARY KEY,
    chat_id          BIGINT NOT NULL,
    surname          VARCHAR(25),
    first_name       VARCHAR(25),
    last_name        VARCHAR(25),
    phone_number     VARCHAR(25),
    pet_type         pet_type NOT NULL
);

CREATE TABLE shelter
(
    shelter_id         BIGSERIAL PRIMARY KEY,
    name               VARCHAR(30) NOT NULL,
    address            TEXT        NOT NULL,
    driving_directions TEXT,
    description        TEXT,
    schedule           TEXT,
    rules              TEXT,
    contacts           TEXT        NOT NULL
);

-- changeset jokeproofee:1
CREATE TABLE volunteer
(
    chat_id          BIGSERIAL PRIMARY KEY,
    surname          VARCHAR(25),
    first_name       VARCHAR(25),
    last_name        VARCHAR(25),
    phone_number     VARCHAR(25) NOT NULL
);

--changeset DeafMist:3
CREATE TABLE report
(
    report_id      BIGSERIAL PRIMARY KEY,
    pet_report     TEXT,
    photo          BYTEA,
    date_of_report DATE NOT NULL,
    pet_id         BIGINT    NOT NULL
);

CREATE TABLE client_details
(
    client_details_id BIGSERIAL PRIMARY KEY,
    client_id         BIGINT NOT NULL,
    pet_id            BIGINT NOT NULL,
    previous_status   status,
    status            status NOT NULL,
    start_date        DATE NOT NULL,
    was_notified_of_status_change BOOLEAN NOT NULL
);

CREATE TABLE shelter_volunteers
(
    volunteer_id BIGINT NOT NULL,
    shelter_id   BIGINT NOT NULL,
    PRIMARY KEY (volunteer_id, shelter_id)
);

-- changeset zikit:2
CREATE TABLE info
(
    info_id                               BIGSERIAL PRIMARY KEY,
    dating_rules_pet                      VARCHAR(500) NOT NULL,
    list_documents_pet                    VARCHAR(500) NOT NULL,
    transporting_pet                      VARCHAR(500) NOT NULL,
    recommendations_little_pet            VARCHAR(500) NOT NULL,
    recommendations_adult_pet             VARCHAR(500) NOT NULL,
    recommendations_with_disabilities_pet VARCHAR(500) NOT NULL,
    reasons_refusal_pet                   VARCHAR(500) NOT NULL,
    tips_handler_dog                      VARCHAR(500),
    recommendations_handler_dog           VARCHAR(500)
);

INSERT INTO client_details (client_details_id, client_id, pet_id, status, start_date, was_notified_of_status_change)
VALUES (1,1,1,'TRIAL','2023-5-15',true);

INSERT INTO volunteer (chat_id, surname, first_name, last_name, phone_number)
VALUES (1,'Сидоров','Павел','Николаевич','255-40-78');

INSERT INTO volunteer (chat_id, surname, first_name, last_name, phone_number)
VALUES (2,'Косыгина','Мария','Леонидовна','444-44-45');

INSERT INTO info (info_id, dating_rules_pet, list_documents_pet, transporting_pet, recommendations_little_pet,
                  recommendations_adult_pet,
                  recommendations_with_disabilities_pet, reasons_refusal_pet, tips_handler_dog,
                  recommendations_handler_dog)
VALUES (1,
        '1.Изучите породы котов. 2. Подберите животное, подходящее вашему уровню активности. 3. Учитывайте свои условия проживания. 4. Определитесь со своими требованиями. 5. Подумайте, стоит ли брать кота с особыми потребностями.',
        'Для усыновления котёнка/кота вам нужно иметь при себе: 1. Паспорт',
        '1. Взять клетку-переноску. 2. Взять одеяло. 3. Приехать на автомобиле или вызвать такси/спецтранспорт для транспортировки.',
        '1. Купите всё, что вам будет необходимо для котёнка (Миску,Корм,Адресник,Клетку-переноску) 2. Найдите ветеринара. 3. Проверьте, безопасен ли ваш дом для животного.',
        '1. Купите всё, что вам будет необходимо для кота (Миску,Корм,Клетку-переноску) 2. Найдите ветеринара. 3. Проверьте, безопасен ли ваш дом для животного.',
        '1. Обустроить свой дом для комфортного проживания кота с ограниченными возможностями. 2. Купить специальный корм. 3. Сделать для него личное место.',
        '1. Нет возможности содержать животное. 2. Нет документов подтверждающих личность. 3. Зафиксированные случаи плохого обращения с животными.',null,null);

INSERT INTO info (info_id, dating_rules_pet, list_documents_pet, transporting_pet, recommendations_little_pet,
                  recommendations_adult_pet,
                  recommendations_with_disabilities_pet, reasons_refusal_pet, tips_handler_dog,
                  recommendations_handler_dog)
VALUES (2,
        '1.Изучите породы собак. 2. Подберите животное, подходящее вашему уровню активности. 3. Учитывайте свои условия проживания. 4. Определитесь со своими требованиями. 5. Подумайте, стоит ли брать собаку с особыми потребностями.',
        'Для усыновления щенка/собаки вам нужно иметь при себе: 1. Паспорт',
        '1. Взять ошейник, намордник. 2. Взять одеяло. 3. Приехать на автомобиле или вызвать такси/спецтранспорт для транспортировки.',
        '1. Купите всё, что вам будет необходимо для щенка (Миску,Корм,Адресник,ошейник) 2. Найдите ветеринара. 3. Проверьте, безопасен ли ваш дом для животного.',
        '1. Купите всё, что вам будет необходимо для собаки (Миску,Корм) 2. Найдите ветеринара. 3. Проверьте, безопасен ли ваш дом для животного.',
        '1. Обустроить свой дом для комфортного проживания собаки с ограниченными возможностями. 2. Купить специальный корм. 3. Сделать для него личное место.',
        '1. Нет  возможности содержать животное. 2. Нет документов подтверждающих личность. 3. Зафиксированные случаи плохого обращения с животными.',
        'Многие владельцы кормят своего любимца перед прогулкой. Однако так делать неправильно и даже вредит вашему питомцу. Собака — хищник. С точки зрения физиологии, хищник должен сначала получить нагрузку, поймать свою добычу и только потом её съесть. После еды хищники отдыхают, и собака так же должна отдыхать после прогулки и кормления.',
        'Сергей Петров Стаж 3год. Номер телефона: 678-54-89.  Георгий Иванов Стаж 7лет. Номер телефона: 679-76-99.');

--changeset DeafMist:4
INSERT INTO shelter (shelter_id, name, address, driving_directions, description, schedule, rules, contacts)
VALUES (1, 'Муркоша',
        'Адрес: Москва, ул. Осташковская, д. 14 стр. 2.',
        'Схема проезда: Выход из метро со стороны 1-ого вагона. Идите направо до светофора, затем перейдите узкую улочку, ' ||
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
        'При посещении данного заведения,запрещено кормить животных,общаться с ними без контроля волонтёра,курить,употреблять алкоголь и пищу.',
        'Телефон для получения пропуска: 8 (495) 023-02-29');

INSERT INTO shelter (shelter_id, name, address, driving_directions, description, schedule, rules, contacts)
VALUES (2, 'Приют Бирюлево',
        'Адрес: Москва, Востряковский пр-д, 10А.',
        'Сначала доехать до станции метро Академика Янгеля, оттуда дойти до ст. МЦД Красный Строитель, ' ||
        'а потом 20 минут пешком - и вы на месте!',
        'Приют Бирюлево - это муниципальный приют для бездомных собак и кошек в Южном округе г. Москвы. ' ||
        'В нем живет почти 2500 собак. Большие и маленькие, пушистые и гладкие, веселые и задумчивые - ' ||
        'и на всех одна большая мечта - встретить своего Человека и найти Дом.',
        'Время работы: среда-воскресенье с 10:00 до 18:00.',
        'При посещении данного заведения,запрещено кормить животных,общаться с ними без контроля волонтёра,курить,употреблять алкоголь и пищу.',
        'Телефон для получения пропуска: 783-02-29');

INSERT INTO pet (age_in_months, name, pet_type,user_details_id, shelter_id)
VALUES (3, 'Васька', 'CAT',1, 1);

INSERT INTO pet (age_in_months, name, pet_type, shelter_id)
VALUES (11, 'Мурка', 'CAT', 1);

INSERT INTO pet (age_in_months, name, pet_type, shelter_id)
VALUES (5, 'Шарик', 'DOG', 2);

INSERT INTO pet (age_in_months, name, pet_type, shelter_id)
VALUES (17, 'Кошмарик', 'DOG', 2);