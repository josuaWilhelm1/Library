package com.example.Library.controller;

import com.example.Library.exception.BookAlreadyReturnedException;
import com.example.Library.exception.BookNotAvailableException;
import com.example.Library.exception.RentalNotFoundException;
import com.example.Library.model.Rental;
import com.example.Library.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @PostMapping("/rental")
    public ResponseEntity<?> rentBook(@RequestBody Rental rental) {
        try {
            Rental rentedBook = rentalService.rentBook(rental);
            return ResponseEntity.status(HttpStatus.CREATED).body(rentedBook);
        } catch (BookNotAvailableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Book not available for rental: " + e.getMessage());
        }
    }

    @GetMapping("/rental/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        Optional<Rental> rental = rentalService.getRentalById(id);
        return rental.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/rental/{id}")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
        try {
            Rental returnedRental = rentalService.returnBook(id);
            return ResponseEntity.ok(returnedRental);
        } catch (BookAlreadyReturnedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error returning book: " + e.getMessage());
        } catch (RentalNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error returning book: " + e.getMessage());
        }
    }

    @DeleteMapping("/rental/{id}")
    public ResponseEntity<String> deleteRentalById(@PathVariable Long id) {
        try {
            rentalService.deleteRentalById(id);
            return ResponseEntity.ok("Rental " + id + " deleted");
        } catch (RentalNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error deleting rental: " + e.getMessage());
        }
    }

    @GetMapping("/rentals")
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @DeleteMapping("/rentals")
    public ResponseEntity<String> deleteAllRentals() {
        rentalService.deleteAllRentals();
        return ResponseEntity.ok("All rentals deleted");
    }
}
