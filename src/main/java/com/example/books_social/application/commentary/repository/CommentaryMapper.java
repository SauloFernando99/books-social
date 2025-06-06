package com.example.books_social.application.commentary.repository;

import com.example.books_social.application.commentary.create.CreateCommentaryService;
import com.example.books_social.application.commentary.update.service.UpdateCommentaryService;
import com.example.books_social.domain.model.book.BookId;
import com.example.books_social.domain.model.comentary.*;
import com.example.books_social.presentation.commentary.requests.PutRequest;

import java.util.UUID;

public class CommentaryMapper {
    public static Commentary fromDto(CommentaryDto dto) {
        Reaction reaction = Reaction.valueOf(dto.reaction());

        return new Commentary(
            new CommentaryId(dto.commentaryId()),
            new BookId(dto.bookId()),
            dto.userId(),
            new CommentaryText(dto.commentaryText()),
            dto.readPages(),
            new Progress(dto.progress()),
            reaction,
            dto.likes(),
            dto.likesList(),
            dto.createdAt()
        );
    }

    public static CommentaryDto toDto(Commentary commentary) {
        return new CommentaryDto(
            commentary.getCommentaryId().getValue(),
            commentary.getBookId().getValue(),
            commentary.getUserId(),
            commentary.getCommentaryText().toString(),
            commentary.getReadPages(),
            commentary.getProgress().value(),
            commentary.getReaction().toString(),
            commentary.getLikes(),
            commentary.getLikesList(),
            commentary.getCreatedAt()
        );
    }

    public static Commentary fromRequestModel(CommentaryId commentaryId, Integer progress, CreateCommentaryService.RequestModel requestModel) {
        Reaction reaction = Reaction.valueOf(requestModel.reaction());
        return new Commentary(
                commentaryId,
                new BookId(requestModel.bookId()),
                requestModel.userId(),
                new CommentaryText(requestModel.commentaryText()),
                requestModel.readPages(),
                new Progress(progress),
                reaction
        );
    }

    public static UpdateCommentaryService.RequestModel toUpdateRequestModel(UUID commentaryId, PutRequest request) {
        return new UpdateCommentaryService.RequestModel(
            commentaryId,
            request.commentaryText(),
            request.readPages(),
            request.reaction()
        );
    }

    public static Commentary updateFrom(CommentaryDto original, Integer progressPercentage, UpdateCommentaryService.RequestModel request) {
        CommentaryText commentaryText = new CommentaryText(request.CommentaryText() != null ? request.CommentaryText() : original.commentaryText());
        Progress progress = new Progress(progressPercentage != null ? progressPercentage : original.progress());
        Reaction reaction = Reaction.valueOf(request.reaction() != null ? request.reaction() : original.reaction());
        return new Commentary(
            new CommentaryId(original.commentaryId()),
            new BookId(original.bookId()),
            original.userId(),
            commentaryText,
            request.readPages() != null ? request.readPages() : original.readPages(),
            progress,
            reaction
        );
    }
}
