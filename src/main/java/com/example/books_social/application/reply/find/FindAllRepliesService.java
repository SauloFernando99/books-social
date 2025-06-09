package com.example.books_social.application.reply.find;

import com.example.books_social.application.commentary.utils.ReplyResponse;
import com.example.books_social.application.reply.repository.ReplyDto;

import java.util.List;
import java.util.UUID;

public interface FindAllRepliesService {
    void findAllByCommentaryId(FindAllRepliesPresenter presenter, RequestModel request);

    public record RequestModel(UUID commentaryId) {}
    public record ResponseModel(
        UUID commentaryId,
        Integer numberOfReplies,
        List<ReplyResponse> replies
    ) {}
}
