package com.example.books_social.infrastructure.commentary;

import com.example.books_social.application.commentary.repository.CommentaryDto;

public class CommentaryDbMapper {
    public static CommentaryDocument toDocument(CommentaryDto dto) {
        return new CommentaryDocument(
            dto.commentaryId(),
                dto.bookId(),
                dto.userId(),
                dto.commentaryText(),
                dto.progress(),
                dto.reaction(),
                dto.createdAt()
        );
    }

    public static CommentaryDto toDto(CommentaryDocument document) {
        return new CommentaryDto(
            document.getId(),
            document.getBookId(),
            document.getUserId(),
            document.getCommentaryText(),
            document.getProgress(),
            document.getReaction(),
            document.getCreatedAt()
        );
    }
}
