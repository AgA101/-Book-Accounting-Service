package org.pr.bookaccountingservice.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private Integer publicationYear;

    private String genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}