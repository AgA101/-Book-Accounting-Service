package org.pr.bookaccountingservice;

import org.pr.bookaccountingservice.controller.AuthorController;
import org.pr.bookaccountingservice.model.Author;
import org.pr.bookaccountingservice.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthorService authorService;

    @Test
    void getAllAuthors_ReturnsPage() throws Exception {
        Page<Author> page = new PageImpl<>(Collections.singletonList(new Author()));
        when(authorService.getAllAuthors(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/authors?page=0&size=10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void getAuthorById_ExistingId_ReturnsAuthor() throws Exception {
        Author author = new Author();
        author.setId(1L);
        author.setName("Лев Толстой");
        when(authorService.getAuthorById(1L)).thenReturn(java.util.Optional.of(author));

        mockMvc.perform(get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Лев Толстой"));
    }

    @Test
    void addAuthor_ValidAuthor_ReturnsCreatedAuthor() throws Exception {
        Author author = new Author();
        author.setName("Лев Толстой");
        author.setBirthYear(1828);
        when(authorService.addAuthor(any(Author.class))).thenReturn(author);

        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Лев Толстой\",\"birthYear\":1828}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Лев Толстой"));
    }

    @Test
    void updateAuthor_ExistingId_ReturnsUpdatedAuthor() throws Exception {
        Author author = new Author();
        author.setId(1L);
        author.setName("Лев Николаевич Толстой");
        Author authorDetails = new Author();
        authorDetails.setName("Лев Николаевич Толстой");
        authorDetails.setBirthYear(1828);
        when(authorService.updateAuthor(1L, authorDetails)).thenReturn(author);

        mockMvc.perform(put("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Лев Николаевич Толстой\",\"birthYear\":1828}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Лев Николаевич Толстой"));
    }

    @Test
    void deleteAuthor_ExistingId_ReturnsOk() throws Exception {
        mockMvc.perform(delete("/authors/1"))
                .andExpect(status().isOk());
    }
}