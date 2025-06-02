package com.example.books_social.domain.model.book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
class BookTest {
    private Book createValidBook() {
        return new Book(
                new BookId(UUID.randomUUID()),
                UUID.randomUUID(),
                "The Hobbit",
                "J.R.R. Tolkien",
                Genre.FANTASY,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 2, 1),
                "Amazing story",
                "Bilbo",
                new Rating(5),
                new CoverUrl("http://example.com/hobbit.jpg"),
                new NumberPages(310),
                ReadingStatus.FINISHED,
                List.of(BookType.DIGITAL),
                true
        );
    }

    @Test
    void shouldCreateBookSuccessfully_WhenAllFieldsAreValid() {
        Book book = createValidBook();
        assertNotNull(book);
        assertEquals("The Hobbit", book.getTitle());
        assertEquals("J.R.R. Tolkien", book.getAuthor());
    }

    @Test
    void shouldThrowException_WhenTitleIsBlank() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Book(
                        new BookId(UUID.randomUUID()),
                        UUID.randomUUID(),
                        "   ",
                        "Author",
                        Genre.FANTASY,
                        null,
                        null,
                        null,
                        null,
                        new Rating(3),
                        null,
                        new NumberPages(100),
                        ReadingStatus.READING,
                        List.of(),
                        false
                )
        );
        assertTrue(exception.getMessage().contains("Title is required."));
    }

    @Test
    void shouldThrowException_WhenAuthorIsBlank() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Book(
                        new BookId(UUID.randomUUID()),
                        UUID.randomUUID(),
                        "Title",
                        "",
                        Genre.MYSTERY,
                        null,
                        null,
                        null,
                        null,
                        new Rating(3),
                        null,
                        new NumberPages(150),
                        ReadingStatus.WISHLIST,
                        List.of(),
                        false
                )
        );
        assertTrue(exception.getMessage().contains("Author is required."));
    }

    @Test
    void shouldSetCreatedAtAutomatically_WhenNotProvided() {
        Book book = new Book(
                new BookId(UUID.randomUUID()),
                UUID.randomUUID(),
                "Title",
                "Author",
                Genre.ROMANCE,
                null,
                null,
                null,
                null,
                new Rating(4),
                null,
                new NumberPages(200),
                ReadingStatus.FINISHED,
                List.of(),
                false
        );

        assertNotNull(book.getCreatedAt());
    }

    @Test
    void shouldCreateBookWithCustomCreatedAt() {
        LocalDateTime now = LocalDateTime.now().minusDays(10);
        Book book = new Book(
                new BookId(UUID.randomUUID()),
                UUID.randomUUID(),
                "Title",
                "Author",
                Genre.DRAMA,
                null,
                null,
                null,
                null,
                new Rating(5),
                null,
                new NumberPages(120),
                ReadingStatus.READING,
                List.of(BookType.DIGITAL),
                false,
                now
        );

        assertEquals(now, book.getCreatedAt());
    }
}