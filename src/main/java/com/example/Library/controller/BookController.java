package com.example.Library.controller;

import com.example.Library.model.Book;
import com.example.Library.repo.SomethingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping(path = "/v1")

public class BookController {
    @Autowired
    private SomethingRepository bookRepository;

    // Book  endpoint
    @PostMapping("/book")
    public Book createBook(@RequestBody Book book) {
        Book newBook = bookRepository.save(book);
        return (newBook);
    }

    @DeleteMapping("/book/{id}")
    public String deleteBookById(@PathVariable Integer id) {
        bookRepository.deleteById(id);
        return ("Book" + id + " deleted");
    }

    @GetMapping("/book/{id}")
    public Optional<Book> getBookById(@PathVariable Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        return book;
    }

    //Books Endpoint
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    @DeleteMapping("/books")
    public String deleteAllBooks() {
        bookRepository.deleteAll();
        return "all Books deleted";
    }

}
