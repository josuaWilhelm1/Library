package com.example.Library.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity //Generates the DB Table
@Data // Generates Constructors, Getter and Setters
@Table(name = "author")

public class Author {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
