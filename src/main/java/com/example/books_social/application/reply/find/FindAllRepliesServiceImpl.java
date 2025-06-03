package com.example.books_social.application.reply.find;

import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.application.reply.repository.ReplyDto;
import com.example.books_social.application.reply.repository.ReplyRepository;
import com.example.books_social.application.shared.exceptions.EntityNotFoundException;

import java.util.List;

public class FindAllRepliesServiceImpl implements FindAllRepliesService{
    private final ReplyRepository replyRepository;

    private final CommentaryRepository commentaryRepository;

    public FindAllRepliesServiceImpl(
        ReplyRepository replyRepository,
        CommentaryRepository commentaryRepository
    ) {
        this.replyRepository = replyRepository;
        this.commentaryRepository = commentaryRepository;
    }

    @Override
    public void findAllByCommentaryId(FindAllRepliesPresenter presenter, RequestModel request) {
        boolean exists = commentaryRepository.existsById(request.commentaryId());

        if (!exists) {
            String message = "Commentary of id: " + request.commentaryId() + "not exists.";
            presenter.prepareFailView(new EntityNotFoundException(message));
            return;
        }

        List<ReplyDto> replies = replyRepository.findAllByCommentaryId(request.commentaryId());

        presenter.prepareSuccessView(
            new ResponseModel(
                request.commentaryId(),
                replies.size(),
                replies
            )
        );
    }
}
