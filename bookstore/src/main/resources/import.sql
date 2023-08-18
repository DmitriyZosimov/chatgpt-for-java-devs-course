-- Insert genres
INSERT INTO genres (id, name) VALUES (1, 'Fiction');
INSERT INTO genres (id, name) VALUES (2, 'Mystery');
INSERT INTO genres (id, name) VALUES (3, 'Science Fiction');
INSERT INTO genres (id, name) VALUES (4, 'Fantasy');

-- Insert authors
INSERT INTO authors (id, name) VALUES (1, 'Jane Doe');
INSERT INTO authors (id, name) VALUES (2, 'John Smith');
INSERT INTO authors (id, name) VALUES (3, 'Emily Johnson');

-- Insert books
INSERT INTO books (id, title, price, quantity_available, author_id, genre_id) VALUES (1, 'The Mystery Mansion', 15.99, 50, 1, 2);
INSERT INTO books (id, title, price, quantity_available, author_id, genre_id) VALUES (2, 'Lost in Space', 12.49, 30, 2, 3);
INSERT INTO books (id, title, price, quantity_available, author_id, genre_id) VALUES (3, 'Realm of Fantasy', 19.99, 25, 3, 4);
INSERT INTO books (id, title, price, quantity_available, author_id, genre_id) VALUES (4, 'The Secret Code', 10.99, 40, 1, 2);
