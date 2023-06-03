package com.example.Library.repo;

import com.example.Library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SomethingRepository extends JpaRepository<Book, Integer> {


}
