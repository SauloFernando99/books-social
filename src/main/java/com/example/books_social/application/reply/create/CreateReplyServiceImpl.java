package com.example.books_social.application.reply.create;

import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.application.reply.repository.ReplyMapper;
import com.example.books_social.application.reply.repository.ReplyRepository;
import com.example.books_social.application.shared.exceptions.UniquenessViolationException;
import com.example.books_social.domain.model.book.Book;
import com.example.books_social.domain.model.book.BookId;
import com.example.books_social.domain.model.reply.Reply;
import com.example.books_social.domain.model.reply.ReplyId;
import com.example.books_social.domain.services.UuidGeneratorService;

public class CreateReplyServiceImpl implements CreateReplyService{
    private final ReplyRepository replyRepository;
    private final CommentaryRepository commentaryRepository;
    private final UuidGeneratorService uuidGeneratorService;

    public CreateReplyServiceImpl(
            ReplyRepository replyRepository,
            CommentaryRepository commentaryRepository,
            UuidGeneratorService uuidGeneratorService
    ) {
        this.replyRepository = replyRepository;
        this.commentaryRepository = commentaryRepository;
        this.uuidGeneratorService = uuidGeneratorService;
    }

    @Override
    public void createReply(CreateReplyPresenter presenter, CreateReplyService.RequestModel request) {
        ReplyId replyId = new ReplyId(uuidGeneratorService.next());
        Reply reply = ReplyMapper.fromRequestModel(replyId, request);
        boolean exists = commentaryRepository.existsById(request.commentaryId());

        if (exists) {
            String message = "There's no commentary of id: " + request.commentaryId() + ".";
            presenter.prepareFailView(new UniquenessViolationException(message));
            return;
        }

        replyRepository.create(ReplyMapper.toDto(reply));

        presenter.prepareSuccessView(new CreateReplyService.ResponseModel(
                reply.getReplyId().getValue(),
                reply.getCommentaryId().getValue(),
                reply.getCreatedAt()
        ));
    }
}
