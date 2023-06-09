package com.example.Library.repo;

import com.example.Library.model.Author;
import com.example.Library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepo extends JpaRepository<Book, Long> {


    boolean existsByAuthor(Author author);
}
