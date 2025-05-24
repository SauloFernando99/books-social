package com.example.books_social.application.commentary.find;

import com.example.books_social.application.commentary.repository.CommentaryDto;

import java.util.List;
import java.util.UUID;

public interface FindAllCommentsService {
    void findAllByBookId(FindAllCommentsPresenter presenter, RequestModel request);

    public record RequestModel(UUID bookId) {}

    public record ResponseModel(UUID bookId, Integer numberOfComments, List<CommentaryDto> comments) {}
}
