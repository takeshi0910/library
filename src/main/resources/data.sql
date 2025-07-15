INSERT INTO genres (name, deleted) VALUES
  ('小説', FALSE),
  ('ビジネス', FALSE),
  ('歴史', FALSE),
  ('技術', FALSE),
  ('ファンタジー', FALSE),
  ('漫画', FALSE),
  ('その他', FALSE);

INSERT INTO book (title, author, genre_id, isbn, deleted) VALUES
('ノルウェイの森', '村上春樹', 1, '9784101001014', FALSE),
('ハリー・ポッターと秘密の部屋', 'J.K.ローリング', 5, '9780747538493', FALSE),
('ザ・ゴール', 'エリヤフ・ゴールドラット', 2, '9784478370140', FALSE),
('進撃の巨人', '諫山創', 6, '9784063842760', FALSE);