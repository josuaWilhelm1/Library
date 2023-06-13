package com.example.Library.repo;

import com.example.Library.model.Book;
import com.example.Library.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    // Unused by Frontend
    // Check if a rental exists for a given book
    boolean existsByBook(Book book);

    // Get overdue rentals based on the return date and not returned yet
    List<Rental> findByReturnDateBeforeAndReturnedFalse(LocalDate currentDate);

    // Get ongoing rentals (not returned)
    List<Rental> findByReturnedFalse();

    // Get returned rentals
    List<Rental> findByReturnedTrue();
}
