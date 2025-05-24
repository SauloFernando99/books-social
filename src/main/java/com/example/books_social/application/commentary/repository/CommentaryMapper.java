package com.example.books_social.application.commentary.repository;

import com.example.books_social.application.commentary.create.CreateCommentaryService;
import com.example.books_social.domain.model.book.BookId;
import com.example.books_social.domain.model.comentary.*;

public class CommentaryMapper {
    public static Commentary fromDto(CommentaryDto dto) {
        Reaction reaction = Reaction.valueOf(dto.reaction());

        return new Commentary(
            new CommentaryId(dto.commentaryId()),
            new BookId(dto.bookId()),
            dto.userId(),
            new CommentaryText(dto.commentaryText()),
            new Progress(dto.progress()),
            reaction,
            dto.createdAt()
        );
    }

    public static CommentaryDto toDto(Commentary commentary) {
        return new CommentaryDto(
            commentary.getCommentaryId().getValue(),
            commentary.getBookId().getValue(),
            commentary.getUserId(),
            commentary.getCommentaryText().toString(),
            commentary.getProgress().value(),
            commentary.getReaction().toString(),
            commentary.getCreatedAt()
        );
    }

    public static Commentary fromRequestModel(CommentaryId commentaryId, CreateCommentaryService.RequestModel requestModel) {
        Reaction reaction = Reaction.valueOf(requestModel.reaction());

        return new Commentary(
                commentaryId,
                new BookId(requestModel.bookId()),
                requestModel.userId(),
                new CommentaryText(requestModel.commentaryText()),
                new Progress(requestModel.progress()),
                reaction
        );
    }
}
