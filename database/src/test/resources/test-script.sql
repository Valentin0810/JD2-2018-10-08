INSERT INTO my_library.publishing_house(name)
VALUES
  ('АСТ'),
  ('ЭКСМО'),
  ('Амфора');

INSERT INTO my_library.genre(name)
VALUES
  ('Ужасы'),
  ('Приключения'),
  ('Фантастика');

INSERT INTO my_library.book (genre_id, name, publishing_house_id, year_publishing, pages)
VALUES
  ((SELECT id FROM my_library.genre WHERE name = 'Ужасы'), 'Оно', (SELECT id FROM my_library.publishing_house WHERE name = 'АСТ'), 2015, 450),
  ((SELECT id FROM my_library.genre WHERE name = 'Приключения'), 'Бездна голожных глаз', (SELECT id FROM my_library.publishing_house WHERE name = 'ЭКСМО'), 2015, 450),
  ((SELECT id FROM my_library.genre WHERE name = 'Фантастика'), 'Зов предков', (SELECT id FROM my_library.publishing_house WHERE name = 'Амфора'), 2015, 450);
