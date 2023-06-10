package com.example.Library.repo;

import com.example.Library.model.Author;
import com.example.Library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepo extends JpaRepository<Book, Long> {


    boolean existsByAuthor(Author author);
    

    List<Book> findByAvailable(boolean b);

    List<Book> findByAuthor(Author author, boolean b);

    List<Book> findByGenre(String genre, boolean b);
}
