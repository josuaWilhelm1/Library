package com.example.Library.service;

import com.example.Library.exception.AuthorHasBooksException;
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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepository;
    private final AuthorRepository authorRepository;
    private final RentalRepository rentalRepository;


    public Book createBook(Book book) {
        Long author_id = book.getAuthor().getId();
        Optional<Author> author = authorRepository.findById(author_id);
        if (author.isPresent()) {
            return bookRepository.save(book);
        } else {
            throw new AuthorNotFoundException("Author not Found");
        }
    }

    public void deleteBookById(Long id) {

        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            if (rentalRepository.existsByBook(book)) {
                throw new BookHasRentalsException("Cannot delete book. Delete the associated rentals first.");
            }
            authorRepository.deleteById(id);
        } else {
            throw new AuthorNotFoundException("Author not found");
        }
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }

    public Optional<Book> getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book;
        } else {
            throw new BookNotFoundException("Book not found");
        }
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

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
