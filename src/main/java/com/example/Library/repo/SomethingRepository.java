package com.example.Library.repo;

import com.example.Library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SomethingRepository extends JpaRepository<Book, Long> {


}
