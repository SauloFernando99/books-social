package com.example.books_social.application.commentary.repository;

import com.example.books_social.application.commentary.create.CreateCommentaryService;
import com.example.books_social.application.commentary.update.UpdateCommentaryService;
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

    public static Commentary updateFrom(CommentaryDto original, UpdateCommentaryService.RequestModel request) {
        CommentaryText commentaryText = new CommentaryText(request.CommentaryText() != null ? request.CommentaryText() : original.commentaryText());
        Progress progress = new Progress(request.progress() != null ? request.progress() : original.progress());
        Reaction reaction = Reaction.valueOf(request.reaction() != null ? request.reaction() : original.reaction());
        return new Commentary(
            new CommentaryId(original.commentaryId()),
            new BookId(original.bookId()),
            original.userId(),
            commentaryText,
            progress,
            reaction
        );
    }
}
