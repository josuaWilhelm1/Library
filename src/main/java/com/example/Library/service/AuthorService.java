package com.example.Library.service;

import com.example.Library.exception.AuthorHasBooksException;
import com.example.Library.exception.AuthorNotFoundException;
import com.example.Library.model.Author;
import com.example.Library.repo.AuthorRepository;
import com.example.Library.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepo bookRepository;


    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthorById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            if (bookRepository.existsByAuthor(author)) {
                throw new AuthorHasBooksException("Cannot delete author. Delete the associated books first.");
            }
            authorRepository.deleteById(id);
        } else {
            throw new AuthorNotFoundException("Author not found");
        }
    }
    public Optional<Author> getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return author;
        } else {
            throw new AuthorNotFoundException("Author not found");
        }
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public void deleteAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        for (Author author : authors) {
            if (bookRepository.existsByAuthor(author)) {
                throw new AuthorHasBooksException("Cannot delete all authors. Delete the associated books first.");
            }
        }
        authorRepository.deleteAll();
    }
}
