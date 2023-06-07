package com.example.Library.repo;

import com.example.Library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

//    @Query(
//            value = "SELECT * FROM Author",
//
//            nativeQuery = true)
//    List<Author> customQuery();

}

