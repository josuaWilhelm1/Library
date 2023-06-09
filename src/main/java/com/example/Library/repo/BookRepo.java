package com.example.Library.repo;

import com.example.Library.model.Author;
import com.example.Library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepo extends JpaRepository<Book, Long> {


    boolean existsByAuthor(Author author);


    List<Book> findByAuthorAndAvailable(Author author, boolean b);

    List<Book> findByGenreAndAvailable(String genre, boolean b);

    List<Book> findByAvailable(boolean b);
}
