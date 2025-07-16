package org.pr.bookaccountingservice.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer birthYear;

}