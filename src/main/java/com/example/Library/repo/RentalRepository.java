package com.example.Library.repo;

import com.example.Library.model.Book;
import com.example.Library.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    boolean existsByBook(Book book);
}
