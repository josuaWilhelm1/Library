package com.example.library.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity //Generates the DB Table
@Data // Generates Constructors, Getter and Setters
@Table(name = "book")

public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author; //Represents the associated author of the book.

    private String genre;

    private Boolean available = true; // Represents whether the book is available for rental or not.


    private int rentalCount = 0; // Represents the number of times the book has been rented.
}
