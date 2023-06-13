package com.example.Library.service;

import com.example.Library.exception.AuthorNotFoundException;
import com.example.Library.exception.BookHasRentalsException;
import com.example.Library.exception.BookNotFoundException;
import com.example.Library.model.Author;
import com.example.Library.model.Book;
import com.example.Library.repo.AuthorRepository;
import com.example.Library.repo.BookRepo;
import com.example.Library.repo.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepo bookRepository;
    private final AuthorRepository authorRepository;
    private final RentalRepository rentalRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = AuthorNotFoundException.class)
    public Book createBook(Book book) {
        Long author_id = book.getAuthor().getId();
        Optional<Author> author = authorRepository.findById(author_id);
        if (author.isPresent()) {
            return bookRepository.save(book);
        } else {
            throw new AuthorNotFoundException("Author not Found");
        }
    }

    // Unused by Frontend
    @Transactional(propagation= Propagation.REQUIRES_NEW, rollbackFor = {BookHasRentalsException.class, BookNotFoundException.class} )
    public void deleteBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            if (rentalRepository.existsByBook(book)) {
                throw new BookHasRentalsException("Cannot delete book. Delete the associated rentals first.");
            }
            bookRepository.deleteById(id);
        } else {
            throw new BookNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }

    // Unused by Frontend
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book;
        } else {
            throw new BookNotFoundException("Book not found");
        }
    }

    // Unused by Frontend
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailable(true);
    }

    @Transactional(readOnly = true)
    public List<Book> getUnavailableBooks() {
        List<Book> books = bookRepository.findByAvailable(false);
        return books;
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthor(Long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            return bookRepository.findByAuthor(author.get());
        } else {
            throw new AuthorNotFoundException("Author not found");
        }
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksByGenre(String genre) {
        List<Book> books = bookRepository.findByGenre(genre);
        return books;
    }

    // Unused by Frontend
    @Transactional(propagation= Propagation.REQUIRES_NEW, rollbackFor = BookHasRentalsException.class )
    public void deleteAllBooks() {
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            if (rentalRepository.existsByBook(book)) {
                throw new BookHasRentalsException("Cannot delete books. Delete the associated rentals first.");
            }
        }
        bookRepository.deleteAll();
    }
}