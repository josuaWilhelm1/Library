package com.example.Library.repo;

import com.example.Library.model.Book;
import com.example.Library.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    boolean existsByBook(Book book);
    List<Rental> findByReturnDateBeforeAndReturnedFalse(LocalDate currentDate);
}
