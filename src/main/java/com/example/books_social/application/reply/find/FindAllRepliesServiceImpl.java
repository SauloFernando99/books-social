package com.example.books_social.application.reply.find;

import com.example.books_social.application.account.AccountDto;
import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.application.commentary.utils.ReplyResponse;
import com.example.books_social.application.reply.repository.ReplyDto;
import com.example.books_social.application.reply.repository.ReplyRepository;
import com.example.books_social.application.shared.exceptions.EntityNotFoundException;
import com.example.books_social.infrastructure.account.AccountRepository;

import java.util.ArrayList;
import java.util.List;

public class FindAllRepliesServiceImpl implements FindAllRepliesService{
    private final ReplyRepository replyRepository;

    private final CommentaryRepository commentaryRepository;
    private final AccountRepository accountRepository;

    public FindAllRepliesServiceImpl(
        ReplyRepository replyRepository,
        CommentaryRepository commentaryRepository,
        AccountRepository accountRepository
    ) {
        this.replyRepository = replyRepository;
        this.commentaryRepository = commentaryRepository;
        this.accountRepository = accountRepository;
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

        List<ReplyResponse> replieslist = new ArrayList<>();

        for (ReplyDto reply: replies) {
            AccountDto account = accountRepository.findById(reply.userId()).orElse(null);
            assert account != null;
            String replyUserName = account.username();
            new ReplyResponse(replyUserName, reply);
            replieslist.add(new ReplyResponse(replyUserName, reply));
        }

        presenter.prepareSuccessView(
            new ResponseModel(
                request.commentaryId(),
                replies.size(),
                replieslist
            )
        );
    }
}
