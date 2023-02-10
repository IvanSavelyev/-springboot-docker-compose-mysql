CREATE TABLE USERS
(
    ID         INTEGER  GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NAME       VARCHAR(256)                        NOT NULL,
    COUNTRY    VARCHAR(256)                        NOT NULL,
    EMAIL      VARCHAR(256)                        NOT NULL
        CONSTRAINT UK_EMAIL unique,
    PASSWORD   VARCHAR(256)                        NOT NULL,
    REGISTERED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    ENABLED    BOOL      DEFAULT TRUE              NOT NULL
);

create table USER_ROLES
(
    USER_ID INTEGER not null,
    ROLE    VARCHAR(255),
    constraint UK_USER_ROLES unique (USER_ID, ROLE),
    constraint FK_USER_ROLES foreign key (USER_ID) references USERS (ID) on delete cascade
);

INSERT INTO USERS (NAME, EMAIL, PASSWORD, COUNTRY)
VALUES ('User', 'user@yandex.ru', '{noop}password', 'Russia'),
       ('Admin', 'admin@gmail.com', '{noop}admin', 'Russia');

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);