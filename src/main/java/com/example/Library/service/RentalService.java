package com.example.Library.service;

import com.example.Library.model.Book;
import com.example.Library.model.Rental;
import com.example.Library.repo.SomethingRepository;
import com.example.Library.repo.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class RentalService {

    private final RentalRepository rentalRepository;
    private final SomethingRepository bookRepository;

    public Rental rentBook(Rental rental) {
        Long bookId = rental.getBook().getId();
        Optional<Book> book = bookRepository.findById(bookId);
        Boolean availability = book.map(Book::getAvailable).orElse(false);
        if (availability) {
            return rentalRepository.save(rental);
        } else {
            throw new RuntimeException("Book not available");
        }
    }

    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    public Rental returnBook(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
        rental.setReturned(true);
        return rentalRepository.save(rental);
    }

    public void deleteRentalById(Long id) {
        rentalRepository.deleteById(id);
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public void deleteAllRentals() {
        rentalRepository.deleteAll();
    }
}
