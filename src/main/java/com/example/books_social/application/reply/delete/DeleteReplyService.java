package com.example.books_social.application.reply.delete;

import java.util.UUID;

public interface DeleteReplyService {
    void deleteReply(
        DeleteReplyPresenter presenter,
        RequestModel request
    );

    public record RequestModel(
        UUID replyId
    ) {}

    public record ResponseModel(
        UUID replyId
    ) {}
}
