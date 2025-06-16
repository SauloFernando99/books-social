package com.example.books_social.infrastructure.commentary;

import com.example.books_social.application.commentary.repository.CommentaryDto;

public class CommentaryDbMapper {
    public static CommentaryDocument toDocument(CommentaryDto dto) {
        return new CommentaryDocument(
            dto.commentaryId(),
                dto.bookId(),
                dto.userId(),
                dto.commentaryText(),
                dto.readPages(),
                dto.progress(),
                dto.reaction(),
                dto.isSpoiler(),
                dto.likes(),
                dto.likesList(),
                dto.createdAt()
        );
    }

    public static CommentaryDto toDto(CommentaryDocument document) {
        return new CommentaryDto(
            document.getId(),
            document.getBookId(),
            document.getUserId(),
            document.getCommentaryText(),
            document.getReadPages(),
            document.getProgress(),
            document.getReaction(),
            document.isSpoiler(),
            document.getLikes(),
            document.getLikesList(),
            document.getCreatedAt()
        );
    }
}
