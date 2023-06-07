package com.example.Library.controller;

import com.example.Library.model.Author;
import com.example.Library.repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @DeleteMapping("/author/{id}")
        public String deleteAuthorById(@PathVariable Long id) {
        authorRepository.deleteById(id);
        return ("Author" + id + " deleted");
    }
    @GetMapping("/author/{id}")
    public Optional<Author> getAuthorById(@PathVariable Long id) {
        System.out.println(id);

        Optional<Author> author = authorRepository.findById(id);
        return author;
    }


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

//    @GetMapping("/testCustomQuery")
//    public List<Author> testCustomQuery() {
//        List<Author> authors = authorRepository.customQuery();
//        return authors;
//    }

}
