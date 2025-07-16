package org.pr.bookaccountingservice;

import org.pr.bookaccountingservice.controller.BookController;
import org.pr.bookaccountingservice.model.Book;
import org.pr.bookaccountingservice.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void getAllBooks_ReturnsList() throws Exception {
        when(bookService.getAllBooks()).thenReturn(Collections.singletonList(new Book()));

        mockMvc.perform(get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getBookById_ExistingId_ReturnsBook() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Война и мир");
        when(bookService.getBookById(1L)).thenReturn(java.util.Optional.of(book));

        mockMvc.perform(get("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Война и мир"));
    }

    @Test
    void addBook_ValidBook_ReturnsCreatedBook() throws Exception {
        Book book = new Book();
        book.setTitle("Война и мир");
        book.setPublicationYear(1865);
        when(bookService.addBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Война и мир\",\"publicationYear\":1865,\"author\":{\"id\":1}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Война и мир"));
    }

    @Test
    void updateBook_ExistingId_ReturnsUpdatedBook() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Война и мир (обновлено)");
        when(bookService.updateBook(any(Long.class), any(Book.class))).thenReturn(book);

        mockMvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Война и мир (обновлено)\",\"publicationYear\":1865}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Война и мир (обновлено)"));
    }

    @Test
    void deleteBook_ExistingId_ReturnsOk() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isOk());
    }
}