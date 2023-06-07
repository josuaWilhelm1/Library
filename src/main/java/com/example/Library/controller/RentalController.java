package com.example.Library.controller;

import com.example.Library.model.Book;
import com.example.Library.model.Rental;
import com.example.Library.repo.RentalRepository;
import com.example.Library.repo.SomethingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1")

public class RentalController {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private SomethingRepository bookRepository;


    // rental Endpoint
    @PostMapping("/rental")
    public Rental rentBook(@RequestBody Rental rental) {
        Long book_id = rental.getBook().getId();
        Optional<Book> book = bookRepository.findById(book_id);
        Boolean availability = book.map(Book::getAvailable).orElse(false);
        if (availability) {
            Rental newRental = rentalRepository.save(rental);
            return (newRental);
        } else throw new RuntimeException("Book not available");
    }

    @GetMapping("/rental/{id}")
    public Optional<Rental> getRentalById(@PathVariable Long id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        return rental;
    }

    @PatchMapping("/rental/{id}")
    public Rental returnBook(@PathVariable Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));
        rental.setReturned(true);
        Rental updatedRental = rentalRepository.save(rental);
        return updatedRental;

    }

    @DeleteMapping("/rental/{id}")
    public String deleteRentalById(@PathVariable Long id) {
        rentalRepository.deleteById(id);
        return ("Rental " + id + " deleted");
    }

    // rentals Endpoint
    @GetMapping("/rentals")
    public List<Rental> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals;
    }

    @DeleteMapping("/rentals")
    public String deleteAllRentals() {
        rentalRepository.deleteAll();
        return "all Rentals deleted";
    }


}
