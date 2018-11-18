CREATE DATABASE my_library encoding = 'UTF8';
CREATE SCHEMA my_library;

CREATE TABLE my_library.catalog (
  id       SERIAL PRIMARY KEY,
  book_id  INTEGER REFERENCES my_library.book (id),
  quantity INTEGER
);

CREATE TABLE my_library.genre (
  id   SERIAL PRIMARY KEY,
  name CHARACTER VARYING(20) UNIQUE NOT NULL
);

CREATE TABLE my_library.publishing_house (
  id   SERIAL PRIMARY KEY,
  name CHARACTER VARYING(36) UNIQUE NOT NULL
);

CREATE TABLE my_library.author (
  id                SERIAL PRIMARY KEY,
  full_name         CHARACTER VARYING(128),
  years_of_life     CHARACTER VARYING(36),
  brief_information TEXT
);

CREATE TABLE my_library.book (
  id                     SERIAL PRIMARY KEY,
  genre_id               INTEGER REFERENCES my_library.genre (id),
  author_id              INTEGER REFERENCES my_library.author (id),
  name                   CHARACTER VARYING(256),
  publishing_house_id    INTEGER REFERENCES my_library.publishing_house (id),
  the_year_of_publishing INTEGER,
  pages                  INTEGER
);

CREATE TABLE my_library.user (
  id           SERIAL PRIMARY KEY,
  full_name    CHARACTER VARYING(128),
  address      CHARACTER VARYING(128),
  phone_number INTEGER,
  role         CHARACTER VARYING(12),
  login        CHARACTER VARYING(24) UNIQUE NOT NULL,
  password     CHARACTER VARYING(24)
);

CREATE TABLE my_library.black_list (
  id      SERIAL PRIMARY KEY,
  user_id INTEGER REFERENCES my_library.user (id)
);

CREATE TABLE my_library.book_order (
  id         BIGSERIAL PRIMARY KEY,
  user_id    INTEGER REFERENCES my_library.user (id),
  book_id    INTEGER REFERENCES my_library.book (id),
  order_date DATE
);

CREATE TABLE my_library.book_in_lending (
  id            SERIAL PRIMARY KEY,
  book_order_id      INTEGER REFERENCES my_library.book_order (id),
  date_of_issue DATE,
  return_date   DATE,
  delay         DATE
);
DROP TABLE my_library.catalog;

CREATE TABLE my_library.book_in_reading_room (
  id       SERIAL PRIMARY KEY,
  book_order_id INTEGER REFERENCES my_library.book_order (id),
  date     DATE
);

CREATE TABLE my_library.librarian(
  user_id BIGINT NOT NULL UNIQUE REFERENCES my_library.user(id),
  tariff_category SMALLINT
);

CREATE TABLE my_library.reader(
  user_id BIGINT NOT NULL UNIQUE REFERENCES my_library.user(id),
  passport_series CHARACTER VARYING(20) UNIQUE
);
DROP TABLE my_library.reader;
CREATE TABLE my_library.admin(
  user_id BIGINT NOT NULL UNIQUE REFERENCES my_library.user(id),
  e_mail CHARACTER VARYING(128) UNIQUE NOT NULL
);
DROP TABLE my_library.book_in_reading_room;

INSERT INTO my_library.author (full_name, years_of_life, brief_information) VALUES
  ('Стивен Кинг', '21 сентября 1947 - наст. время',
   'Сти́вен Э́двин Кинг (англ. Stephen Edwin King; род. Портленд, Мэн, США) — американский писатель, работающий в разнообразных жанрах, включая ужасы, триллер, фантастику, фэнтези, мистику, драму; получил прозвище «Король ужасов». Продано более 350 миллионов экземпляров его книг[3], по которым был снят ряд художественных фильмов, телевизионных постановок, а также нарисованы комиксы. Кинг опубликовал 56 романов, в том числе 7 под псевдонимом Ричард Бахман, и 5 научно-популярных книг. Последний роман Стивена Кинга «Аутсайдер» вышел 23 мая 2018 года.'),
  ('Генри Лайон Олди', '1963 - наст. время',
   'Ге́нри Ла́йон О́лди — псевдоним украинских писателей-фантастов Дмитрия Громова и Олега Ладыженского. Соавторы пишут как фэнтези, так и псевдонаучную фантастику; как в большой, так и в малой форме. Олег Ладыженский также известен как поэт, его стихи вошли во многие книги дуэта. Кроме того, Г. Л. Олди — в числе литературных критиков в области фантастики. Статьи Олди, публиковавшиеся в журналах «Мир фантастики» и «Если», получили ряд премий в области критики.'),
  ('Джек Лондон', '12 января 1876 — 22 ноября 1916',
   'Джек Ло́ндон (англ. Jack London; урождённый Джон Гри́ффит Че́йни, John Griffith Chaney; американский писатель, социалист, общественный деятель, наиболее известен как автор приключенческих рассказов и романов. Джек Лондон был вторым после Х. К. Андерсена по издаваемости в СССР зарубежным писателем за 1918—1986 годы: общий тираж 956 изданий составил 77,153 млн экземпляров');

INSERT INTO my_library.publishing_house (name) VALUES
  ('АСТ'),
  ('Эксмо'),
  ('Амфора');

INSERT INTO my_library.genre (name) VALUES
  ('Ужасы'),
  ('Фантастика'),
  ('Фэнтэзи'),
  ('Приключения');

INSERT INTO my_library.book (genre_id, author_id, name, publishing_house_id, the_year_of_publishing, pages) VALUES
  ((SELECT id
    FROM my_library.genre
    WHERE name = 'Ужасы'), (SELECT id
                            FROM my_library.author
                            WHERE full_name = 'Стивен Кинг'), 'Оно', (SELECT id
                                                                      FROM my_library.publishing_house
                                                                      WHERE name = 'АСТ'), 2013, 1248),
  ((SELECT id
    FROM my_library.genre
    WHERE name = 'Фантастика'), (SELECT id
                                 FROM my_library.author
                                 WHERE full_name = 'Генри Лайон Олди'), 'Бездна голодных глаз', (SELECT id
                                                                                                 FROM
                                                                                                   my_library.publishing_house
                                                                                                 WHERE name = 'Эксмо'),
   2011, 960),

  ((SELECT id
    FROM my_library.genre
    WHERE name = 'Приключения'), (SELECT id
                                  FROM my_library.author
                                  WHERE full_name = 'Джек Лондон'), 'Зов предков', (SELECT id
                                                                                    FROM my_library.publishing_house
                                                                                    WHERE name = 'Амфора'), 2015, 383);

INSERT INTO my_library.reader (full_name, address, phone_number, role, login, password, passport_series) VALUES
  ('Иванов Иван Иванович', 'проспект Победителей 51/25', 293825821, 'READER', 'Ivanov@gmail.com', '1111', 'AB2536252'),
  ('Малахов Андрей Константинович', 'ул. Краснознаменная 31/2', 532112525, 'READER', 'Malahov@gmail.com', '1111', 'AA1212121');

INSERT INTO my_library.admin (full_name, address, phone_number, role, login, password, passport_series,e_mail) VALUES
  ('Петров Петр Петрович', 'ул. Некрасова 25/6', 294002514, 'ADMIN', 'Petrov', '1111''Petrov@gmail.com');

INSERT INTO my_library.librarian (full_name, address, phone_number, role, login, password, tariff_category) VALUES
  ('Бузова Ольга Андреевна', 'ул. Минская 90/60', 5777777, 'LIBRARIAN', 'Buzova', '1111', '15');



