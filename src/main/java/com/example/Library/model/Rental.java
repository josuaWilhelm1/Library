package com.example.Library.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "rental")

public class Rental {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDate returnDate = LocalDate.now().minusMonths(1) ;

    private boolean returned = false;

    // Constructors, getters, and setters are automatically generated by Lombok
}
