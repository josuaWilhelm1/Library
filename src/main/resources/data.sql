# -- Insert Authors
# INSERT INTO author (id, name) VALUES
#                                   (1, 'Author 1'),
#                                   (2, 'Author 2'),
#                                   (3, 'Author 3');
#
# -- Insert Books
# INSERT INTO book (id, title, author_id, genre, available, rental_count) VALUES
#                                                                             (1, 'Book 1', 1, 'Genre 1', true, 0),
#                                                                             (2, 'Book 2', 2, 'Genre 2', true, 0),
#                                                                             (3, 'Book 3', 3, 'Genre 3', false, 0);
#
# -- Insert Rentals
# INSERT INTO rental (id, book_id, return_date, returned) VALUES
#                                                             (1, 1, '2023-06-01', true),
#                                                             (2, 2, '2023-06-02', true),
#                                                             (3, 3, '2023-06-03', false);

-- Create rent_book Trigger
CREATE TRIGGER IF NOT EXISTS rent_book
    AFTER INSERT ON rental
    FOR EACH ROW
    UPDATE book
    SET available = false
    WHERE id = NEW.book_id;

-- Create rent_book Trigger
CREATE TRIGGER IF NOT EXISTS return_book
    AFTER Update ON rental
    FOR EACH ROW
    UPDATE book
    SET available = true
    WHERE id = NEW.book_id;

-- Create rent_book Trigger
CREATE TRIGGER IF NOT EXISTS count_rentals
    AFTER INSERT ON rental
    FOR EACH ROW
    UPDATE book
    SET rental_count = rental_count + 1
    WHERE id = NEW.book_id;