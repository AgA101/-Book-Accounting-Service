package org.pr.bookaccountingservice.service;

import jakarta.persistence.EntityManager;
import org.pr.bookaccountingservice.model.Book;
import org.pr.bookaccountingservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book addBook(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги не может быть пустым");
        }
        Book savedBook = bookRepository.save(book);
        entityManager.detach(savedBook);
        return bookRepository.findById(savedBook.getId())
                .orElseThrow(() -> new IllegalStateException("Ошибка при сохранении книги"));

    }

    public Book updateBook(Long id, Book bookDetails) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setPublicationYear(bookDetails.getPublicationYear());
            book.setGenre(bookDetails.getGenre());
            book.setAuthor(bookDetails.getAuthor());
            return bookRepository.save(book);
        }
        throw new IllegalArgumentException("Книга с ID " + id + " не найдена");
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Книга с ID " + id + " не найдена");
        }
        bookRepository.deleteById(id);
    }
}