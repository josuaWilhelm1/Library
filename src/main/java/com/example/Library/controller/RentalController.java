package com.example.Library.controller;

import com.example.Library.model.Rental;
import com.example.Library.repo.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1")

public class RentalController {
    @Autowired
    private RentalRepository rentalRepository;

    // rental Endpoint
    @PostMapping("/rental")
    public Rental createRental(@RequestBody Rental rental) {
        Rental newRental = rentalRepository.save(rental);
        return (newRental);
    }

    @GetMapping("/rental")
    public String getRentalById(@PathVariable Integer id) {
        return "TBD";
    }
//        public Optional<Rental> getRentalById(@PathVariable Integer id) {
//        Optional<Rental> rental  = rentalRepository.findById(id);
//        return rental;
//    }

    @PatchMapping("/rental")
    public String updateRental(Integer book_id) {
        return "TBD";
    }

    @DeleteMapping("/rental")
    public String deleteRentalById(@PathVariable Integer id) {
        return "TBD";
    }
//        public String deleteRentalById(@PathVariable Integer id) {
//        rentalRepository.deleteById(id);
//        return ("Rental " + id+" deleted");
//    }

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
