package com.example.Library.service;

import com.example.Library.exception.BookAlreadyReturnedException;
import com.example.Library.exception.BookNotAvailableException;
import com.example.Library.exception.RentalNotFoundException;
import com.example.Library.model.Book;
import com.example.Library.model.Rental;
import com.example.Library.repo.BookRepo;
import com.example.Library.repo.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class RentalService {

    private final RentalRepository rentalRepository;
    private final BookRepo bookRepository;

    public Rental rentBook(Rental rental) {
        Long bookId = rental.getBook().getId();
        Optional<Book> book = bookRepository.findById(bookId);
        Boolean availability = book.map(Book::getAvailable).orElse(false);
        if (availability) {
            return rentalRepository.save(rental);
        } else {
            throw new BookNotAvailableException("Book is already rented");
        }
    }


    public Optional<Rental> getRentalById(Long id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if (rental.isPresent()) {
            return rental;
        } else {
            throw new RentalNotFoundException("Rental not found");
        }
    }

    public Rental returnBook(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RentalNotFoundException("Rental not found"));

        if (rental.isReturned()) {
            throw new BookAlreadyReturnedException("Book is already returned");
        }

        rental.setReturned(true);
        return rentalRepository.save(rental);
    }



    public void deleteRentalById(Long id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if (rental.isPresent()) {
            rentalRepository.deleteById(id);
        } else {
            throw new RentalNotFoundException("Rental not found");
        }
    }
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
    public List<Rental> getOverdueRentals() {
        LocalDate currentDate = LocalDate.now();
        return rentalRepository.findByReturnDateBeforeAndReturnedFalse(currentDate);
    }
    public void deleteAllRentals() {
        rentalRepository.deleteAll();
    }


}
