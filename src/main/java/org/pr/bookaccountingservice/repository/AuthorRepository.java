package org.pr.bookaccountingservice.repository;

import org.pr.bookaccountingservice.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByBirthYear(Integer birthYear);
}