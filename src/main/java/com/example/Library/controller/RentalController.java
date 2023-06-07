package com.example.Library.controller;

import com.example.Library.model.Rental;
import com.example.Library.repo.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1")

public class RentalController {
    @Autowired
    private RentalRepository rentalRepository;

    // rental Endpoint
    @PostMapping("/rental")
    public Rental rentBook(@RequestBody Rental rental) {
        Rental newRental = rentalRepository.save(rental);
        return (newRental);
    }

    @GetMapping("/rental/{id}")
        public Optional<Rental> getRentalById(@PathVariable Integer id) {
        Optional<Rental> rental  = rentalRepository.findById(id);
        return rental;
    }

//    @PatchMapping("/rental")
//    public String returnRental(Integer rental_id) {
//        Rental rental = rentalRepository.findById(rental_id).orElseThrow(() -> new RuntimeException("Rental not found"));
//        rental.setReturned(true);
//        rentalRepository.save(rental);
//    }

    @DeleteMapping("/rental/{id}")
        public String deleteRentalById(@PathVariable Integer id) {
        rentalRepository.deleteById(id);
        return ("Rental " + id+" deleted");
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
