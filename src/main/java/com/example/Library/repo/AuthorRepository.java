package com.example.Library.repo;

import com.example.Library.model.Author;
import com.example.Library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query(
            value = "SELECT * FROM Author",

            nativeQuery = true)
    List<Author> customQuery();

}

