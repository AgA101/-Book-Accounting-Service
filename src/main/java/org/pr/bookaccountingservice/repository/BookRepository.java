package org.pr.bookaccountingservice.repository;

import org.pr.bookaccountingservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByPublicationYear(Integer publicationYear);
}