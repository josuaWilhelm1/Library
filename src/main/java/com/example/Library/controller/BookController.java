package com.example.Library.controller;

import com.example.Library.model.Book;
import com.example.Library.repo.SomethingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private SomethingRepository bookRepository;

 @PostMapping("/saveBook")
    public String saveBook(@RequestBody Book book) {
        bookRepository.save(book);
        return ("Book saved");
    }

    public String deleteBookById( Integer id) {
        bookRepository.deleteById(id);
        return ("Book" +id+" deleted");
    }


    public Optional<Book> getBookById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        return book;
    }

    @GetMapping("/getAllBooks)")
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }


    public void borrowBook(Integer id) {
        Optional<Book> book= bookRepository.findById(id);
        book.ifPresent(something -> something.setAvailable(false));
    }

    public void returnBook(Integer id) {
        Optional<Book> book= bookRepository.findById(id);
        book.ifPresent(something -> something.setAvailable(true));
    }

}
