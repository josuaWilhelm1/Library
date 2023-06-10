package com.example.Library.controller;

import com.example.Library.exception.*;
import com.example.Library.model.Book;
import com.example.Library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {
    private final BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        try {
            Book createdBook = bookService.createBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Author not found: " + e.getMessage());
        }
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
        try {
            bookService.deleteBookById(id);
            return ResponseEntity.ok("Book " + id + " deleted");
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BookHasRentalsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        List<Book> books = bookService.getAvailableBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/unavailable")
    public ResponseEntity<?> getUnavailableBooks() {
        try {
            List<Book> books = bookService.getUnavailableBooks();
            return ResponseEntity.ok(books);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error retrieving unavailable books: " + e.getMessage());
        }
    }

    @GetMapping("/books/byGenre")
    public ResponseEntity<?> getABooksByGenre(@RequestParam("genre") String genre) {
        try {
            List<Book> books = bookService.getBooksByGenre(genre);
            return ResponseEntity.ok(books);
        } catch (GenreNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error retrieving available books: " + e.getMessage());
        }
    }

    @GetMapping("/books/byAuthor/{authorId}")
    public ResponseEntity<?> getBooksByAuthor(@PathVariable Long authorId) {
        try {
            List<Book> books = bookService.getBooksByAuthor(authorId);
            return ResponseEntity.ok(books);
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Author not found: " + e.getMessage());
        }
    }

    @DeleteMapping("/books")
    public ResponseEntity<String> deleteAllBooks() {
        try {
            bookService.deleteAllBooks();
            return ResponseEntity.ok("All books deleted");
        } catch (BookHasRentalsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
