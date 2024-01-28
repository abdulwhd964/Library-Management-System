package com.library.management.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @Column(unique = true)
    private String title;

    @Column(unique = true)
    private String author;
    private Date publicationYear;

    @Column(unique = true)
    private String isbn;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Borrowing> borrowings = new HashSet<>();
}
