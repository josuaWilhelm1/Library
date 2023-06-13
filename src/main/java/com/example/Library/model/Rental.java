package com.example.Library.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity //Generates the DB Table
@Data // Generates Constructors, Getter and Setters
@Table(name = "rental")

public class Rental {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book; // Represents the associated book for the rental

    private LocalDate returnDate = LocalDate.now().plusMonths(1); //set to one month from the current date by default

    private boolean returned = false; // Represents whether the book has been returned or not.
}
