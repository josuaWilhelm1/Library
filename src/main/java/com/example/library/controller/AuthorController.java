package com.example.library.controller;

import com.example.library.exception.AuthorHasBooksException;
import com.example.library.exception.AuthorNotFoundException;
import com.example.library.model.Author;
import com.example.library.service.AuthorService;
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
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/author")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(author));
    }

    //Unused by Frontend
    @DeleteMapping("/author/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable Long id) {
        try {
            authorService.deleteAuthorById(id);
            return ResponseEntity.ok("Author " + id + " deleted");
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error deleting author: " + e.getMessage());
        } catch (AuthorHasBooksException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error deleting author: " + e.getMessage());
        }
    }

    //Unused by Frontend
    @GetMapping("/author/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.getAuthorById(id);
        return author.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    //Unused by Frontend
    @DeleteMapping("/authors")
    public ResponseEntity<String> deleteAllAuthors() {
        try {
            authorService.deleteAllAuthors();
            return ResponseEntity.ok("All authors deleted");
        } catch (AuthorHasBooksException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error deleting authors: " + e.getMessage());
        }
    }
}
