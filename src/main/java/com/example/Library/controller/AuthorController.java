package com.example.Library.controller;

import com.example.Library.model.Author;
import com.example.Library.repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1")

public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    //Author Endpoint
    @PostMapping("/author")
    public Author createAuthor(@RequestBody Author author) {
        authorRepository.save(author);
        return (author);
    }

    @DeleteMapping("/author")
    public String deleteAuthorById(@PathVariable Integer id) {
        return "TBD";
    }

    //    public String deleteAuthorById(@PathVariable Integer id) {
//        authorRepository.deleteById(id);
//        return ("Author" + id + " deleted");
//    }
    @GetMapping("/author")
    public String getAuthorById(@PathVariable Integer id) {
        return "TBD";
    }
//    public Optional<Author> getAuthorById(@PathVariable Integer id) {
//        Optional<Author> author = authorRepository.findById(id);
//        return author;
//    }


    //Authors Endpoint
    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors;
    }

    @DeleteMapping("/authors")
    public String deleteAllAuthors() {
        authorRepository.deleteAll();
        return "all Authors deleted";
    }

    @GetMapping("/testCustomQuery")
    public List<Author> testCustomQuery() {
        List<Author> authors = authorRepository.customQuery();
        return authors;
    }

}
