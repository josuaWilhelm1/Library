package com.example.library.controller;

import com.example.library.exception.BookAlreadyReturnedException;
import com.example.library.exception.BookNotAvailableException;
import com.example.library.exception.RentalNotFoundException;
import com.example.library.model.Rental;
import com.example.library.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200") //Allow te Frontend to communicate with this Controller
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

    //Unused by Frontend
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

    //Unused by Frontend
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

    //Unused by Frontend
    @GetMapping("/rentals")
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/rentals/overdue")
    public ResponseEntity<List<Rental>> getOverdueRentals() {
        List<Rental> overdueRentals = rentalService.getOverdueRentals();
        return ResponseEntity.ok(overdueRentals);
    }

    @GetMapping("/rentals/ongoing")
    public ResponseEntity<List<Rental>> getOngoingRentals() {
        List<Rental> overdueRentals = rentalService.getOngoingRentals();
        return ResponseEntity.ok(overdueRentals);
    }

    @GetMapping("/rentals/returned")
    public ResponseEntity<List<Rental>> getReturnedRentals() {
        List<Rental> overdueRentals = rentalService.getReturnedRentals();
        return ResponseEntity.ok(overdueRentals);
    }

    //Unused by Frontend
    @DeleteMapping("/rentals")
    public ResponseEntity<String> deleteAllRentals() {
        rentalService.deleteAllRentals();
        return ResponseEntity.ok("All rentals deleted");
    }
}
