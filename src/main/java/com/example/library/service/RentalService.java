package com.example.library.service;

import com.example.library.exception.BookAlreadyReturnedException;
import com.example.library.exception.BookNotAvailableException;
import com.example.library.exception.RentalNotFoundException;
import com.example.library.model.Book;
import com.example.library.model.Rental;
import com.example.library.repo.BookRepo;
import com.example.library.repo.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RentalService {
    private final RentalRepository rentalRepository;
    private final BookRepo bookRepository;


    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BookNotAvailableException.class)
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

    // Unused by Frontend
    @Transactional(readOnly = true)
    public Optional<Rental> getRentalById(Long id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if (rental.isPresent()) {
            return rental;
        } else {
            throw new RentalNotFoundException("Rental not found");
        }
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {BookAlreadyReturnedException.class, RentalNotFoundException.class})
    public Rental returnBook(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RentalNotFoundException("Rental not found"));
        if (rental.isReturned()) {
            throw new BookAlreadyReturnedException("Book is already returned");
        }
        rental.setReturned(true);
        return rentalRepository.save(rental);
    }

    // Unused by Frontend
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRentalById(Long id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if (rental.isPresent()) {
            rentalRepository.deleteById(id);
        } else {
            throw new RentalNotFoundException("Rental not found");
        }
    }

    // Unused by Frontend
    @Transactional(readOnly = true)
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Rental> getOverdueRentals() {
        LocalDate currentDate = LocalDate.now();
        return rentalRepository.findByReturnDateBeforeAndReturnedFalse(currentDate);
    }

    // Unused by Frontend
    @Transactional
    public void deleteAllRentals() {
        rentalRepository.deleteAll();
    }


    @Transactional(readOnly = true)
    public List<Rental> getOngoingRentals() {
        return rentalRepository.findByReturnedFalse();
    }

    @Transactional(readOnly = true)
    public List<Rental> getReturnedRentals() {
        return rentalRepository.findByReturnedTrue();
    }
}