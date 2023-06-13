package com.example.Library.controller;

import com.example.Library.exception.AuthorNotFoundException;
import com.example.Library.exception.BookHasRentalsException;
import com.example.Library.exception.BookNotFoundException;
import com.example.Library.model.Book;
import com.example.Library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200") //Allow te Frontend to communicate with this Controller
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
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

    //Unused by Frontend
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

    //Unused by Frontend
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //Unused by Frontend
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

        List<Book> books = bookService.getUnavailableBooks();
        return ResponseEntity.ok(books);

    }

    @GetMapping("/books/byGenre")
    public ResponseEntity<?> getBooksByGenre(@RequestParam("genre") String genre) {

        List<Book> books = bookService.getBooksByGenre(genre);
        return ResponseEntity.ok(books);

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

    //Unused by Frontend
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
