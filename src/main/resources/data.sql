-- Drop Triggers
DROP TRIGGER IF EXISTS rent_book;
DROP TRIGGER IF EXISTS return_book;
DROP TRIGGER IF EXISTS count_rentals;

-- Delete Tables
DELETE
FROM rental;
DELETE
FROM book;
DELETE
FROM author;

-- Insert Authors
INSERT INTO author (id, name)
VALUES (1, 'Charles Dickens'),
       (2, 'Antoine de Saint-Exupéry'),
       (3, 'J. K. Rowling'),
       (4, 'Agatha Christie'),
       (5, 'Cao Xueqin'),
       (6, 'J. R. R. Tolkien'),
       (7, 'C. S. Lewis'),
       (8, 'H. Rider Haggard'),
       (9, 'Ved Prakash Sharma'),
       (10, 'Dan Brown');

-- Insert Books
INSERT INTO book (id, title, author_id, genre, available, rental_count)
VALUES (1, 'A Tale of Two Cities', 1, 'Historical fiction', false, 1),
       (2, 'The Little Prince (Le Petit Prince)', 2, '	Novella', true, 1),
       (3, 'Harry Potter and the Philosopher''s Stone', 3, 'Fantasy', false, 1),
       (4, 'And Then There Were None', 4, 'Mystery', true, 1),
       (5, 'Dream of the Red Chamber', 5, 'Family saga', false, 1),
       (6, 'The Hobbit', 6, 'Fantasy', true, 1),
       (7, 'The Lion, the Witch and the Wardrobe', 7, 'Childrens fiction', false, 1),
       (8, 'She: A History of Adventure	', 8, 'Adventure', true, 1),
       (9, 'Vardi Wala Gunda', 9, 'Detective', false, 1),
       (10, 'The Da Vinci Code', 10, 'Mystery thriller', true, 1),
       (11, 'Harry Potter and the Chamber of Secrets', 3, 'Fantasy', false, 1),
       (12, 'Harry Potter and the Prisoner of Azkaban', 3, 'Fantasy', true, 1),
       (13, 'Harry Potter and the Goblet of Fire', 3, 'Fantasy', false, 1),
       (14, 'Harry Potter and the Order of the Phoenix', 3, 'Fantasy', true, 1),
       (15, 'Harry Potter and the Half-Blood Prince', 3, 'Fantasy', false, 1),
       (16, 'Harry Potter and the Deathly Hallows', 3, 'Fantasy', true, 1);

-- Insert Rentals
INSERT INTO rental (id, book_id, return_date, returned)
VALUES (1, 1, '2023-06-01', false),
       (2, 2, '2023-06-03', true),
       (3, 3, '2023-06-05', false),
       (4, 4, '2023-06-07', true),
       (5, 5, '2023-06-09', false),
       (6, 6, '2023-06-11', true),
       (7, 7, '2023-06-13', false),
       (8, 8, '2023-06-15', true),
       (9, 9, '2023-06-17', false),
       (10, 10, '2023-06-19', true),
       (11, 11, '2023-06-21', false),
       (12, 12, '2023-06-23', true),
       (13, 13, '2023-06-25', false),
       (14, 14, '2023-06-27', true),
       (15, 15, '2023-06-29', false),
       (16, 16, '2023-07-01', true);
-- Set Autoincrement to fitting ID
UPDATE rental_seq
SET next_val = 17;
UPDATE book_seq
SET next_val = 17;
UPDATE author_seq
SET next_val = 11;

-- Create Triggers
CREATE TRIGGER IF NOT EXISTS rent_book
    AFTER
INSERT ON rental FOR EACH ROW
UPDATE book
SET available = false
WHERE id = NEW.book_id;

CREATE TRIGGER IF NOT EXISTS return_book
    AFTER
Update ON rental
    FOR EACH ROW
UPDATE book
SET available = true
WHERE id = NEW.book_id;

CREATE TRIGGER IF NOT EXISTS count_rentals
    AFTER
INSERT ON rental FOR EACH ROW
UPDATE book
SET rental_count = rental_count + 1
WHERE id = NEW.book_id;