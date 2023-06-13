package com.example.library.service;

import com.example.library.exception.AuthorHasBooksException;
import com.example.library.exception.AuthorNotFoundException;
import com.example.library.exception.BookHasRentalsException;
import com.example.library.model.Author;
import com.example.library.repo.AuthorRepository;
import com.example.library.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepo bookRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Unused by Frontend
    @Transactional(propagation= Propagation.REQUIRES_NEW, rollbackFor = BookHasRentalsException.class )
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

    // Unused by Frontend
    @Transactional(readOnly = true)
    public Optional<Author> getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return author;
        } else {
            throw new AuthorNotFoundException("Author not found");
        }
    }

    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Unused by Frontend
    @Transactional(propagation= Propagation.REQUIRES_NEW, rollbackFor = AuthorHasBooksException.class )
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