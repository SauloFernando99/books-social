package com.example.books_social.domain.model.commentary;

import com.example.books_social.domain.model.book.BookId;
import com.example.books_social.domain.model.comentary.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CommentaryTest {

    @Test
    void shouldCreateValidCommentaryWithDefaultCreatedAt() {
        CommentaryId commentaryId = new CommentaryId(UUID.randomUUID());
        BookId bookId = new BookId(UUID.randomUUID());
        UUID userId = UUID.randomUUID();
        CommentaryText commentaryText = new CommentaryText("Muito bom esse livro!");
        Progress progress = new Progress(75);
        Reaction reaction = Reaction.AMAZED;

        Commentary commentary = new Commentary(commentaryId, bookId, userId, commentaryText,1, progress, reaction, false);

        assertEquals(commentaryId, commentary.getCommentaryId());
        assertEquals(bookId, commentary.getBookId());
        assertEquals(userId, commentary.getUserId());
        assertEquals(commentaryText, commentary.getCommentaryText());
        assertEquals(progress, commentary.getProgress());
        assertEquals(reaction, commentary.getReaction());
        assertNotNull(commentary.getCreatedAt());
    }

    @Test
    void shouldCreateCommentaryWithProvidedCreatedAt() {
        CommentaryId commentaryId = new CommentaryId(UUID.randomUUID());
        BookId bookId = new BookId(UUID.randomUUID());
        UUID userId = UUID.randomUUID();
        CommentaryText commentaryText = new CommentaryText("Gostei do enredo.");
        Progress progress = new Progress(50);
        Reaction reaction = Reaction.DELUDED;
        LocalDateTime now = LocalDateTime.now();
        List<UUID> list = new ArrayList<>();

        Commentary commentary = new Commentary(commentaryId, bookId, userId, commentaryText,1, progress, reaction, false, 0, list, now);

        assertEquals(now, commentary.getCreatedAt());
    }

    @Test
    void shouldAllowUpdatingCommentaryFields() {
        Commentary commentary = new Commentary(
                new CommentaryId(UUID.randomUUID()),
                new BookId(UUID.randomUUID()),
                UUID.randomUUID(),
                new CommentaryText("Inicial"),
                1,
                new Progress(10),
                Reaction.AMAZED,
                false
        );

        CommentaryText newText = new CommentaryText("Texto atualizado");
        Progress newProgress = new Progress(90);
        Reaction newReaction = Reaction.AGONY;

        commentary.setCommentaryText(newText);
        commentary.setProgress(newProgress);
        commentary.setReaction(newReaction);

        assertEquals(newText, commentary.getCommentaryText());
        assertEquals(newProgress, commentary.getProgress());
        assertEquals(newReaction, commentary.getReaction());
    }
}

