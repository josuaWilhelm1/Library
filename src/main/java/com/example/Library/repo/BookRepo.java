package com.example.Library.repo;

import com.example.Library.model.Author;
import com.example.Library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepo extends JpaRepository<Book, Long> {

    // Unused by Frontend
    // Check if a book exists for a given author
    boolean existsByAuthor(Author author);

    // Get books by availability
    List<Book> findByAvailable(boolean b);

    // Get books by author
    List<Book> findByAuthor(Author author);

    // Get books by genre
    List<Book> findByGenre(String genre);
}
