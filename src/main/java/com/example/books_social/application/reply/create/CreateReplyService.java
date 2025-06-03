package com.example.books_social.application.reply.create;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CreateReplyService {
    void createBook(
            CreateReplyPresenter createReplyPresenter,
            RequestModel request
    );

    public record RequestModel (
            UUID ownerId,
            UUID commentaryId,
            String replyText
    ) {}

    public record ResponseModel(
            UUID replyId,
            UUID commentaryId,
            LocalDateTime createdAt
    ) {}
}
