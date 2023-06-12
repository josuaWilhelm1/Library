package com.example.Library.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "author")

public class Author {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
