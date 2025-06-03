package com.example.books_social.application.commentary.find.service;

import com.example.books_social.application.account.AccountDto;
import com.example.books_social.application.book.repository.BookDto;
import com.example.books_social.application.book.repository.BookRepository;
import com.example.books_social.application.commentary.find.presenter.FindAllCommentsRandomlyPresenter;
import com.example.books_social.application.commentary.repository.CommentaryDto;
import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.application.commentary.utils.RandomCommentsResponse;
import com.example.books_social.application.reply.repository.ReplyDto;
import com.example.books_social.application.reply.repository.ReplyRepository;
import com.example.books_social.infrastructure.account.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class FindAllCommentsRandomlyServiceImpl implements FindAllCommentsRandomlyService {
    private final CommentaryRepository repository;
    private final BookRepository bookRepository;
    private final AccountRepository accountRepository;
    private final ReplyRepository replyRepository;

    public FindAllCommentsRandomlyServiceImpl(
        CommentaryRepository repository,
        BookRepository bookRepository,
        AccountRepository accountRepository,
        ReplyRepository replyRepository
    ) {
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.accountRepository = accountRepository;
        this.replyRepository = replyRepository;
    }


    @Override
    public void findAllCommentsRandomly(FindAllCommentsRandomlyPresenter presenter, RequestModel request) {
        Pageable pageable = PageRequest.of(request.page(), request.size());
        Page<CommentaryDto> commentaryPage = repository.findCommentaryRandomly(pageable);

        List<RandomCommentsResponse> randomComments = new ArrayList<>();

        for (CommentaryDto commentaryDto: commentaryPage){
            AccountDto accountDto = accountRepository.findById(commentaryDto.userId());
            BookDto bookDto = bookRepository.findById(commentaryDto.bookId());
            List<ReplyDto> replies = replyRepository.findAllByCommentaryId(commentaryDto.commentaryId());

            RandomCommentsResponse commentary = new RandomCommentsResponse(
                accountDto.username(),
                bookDto,
                commentaryDto,
                replies
            );

            randomComments.add(commentary);
        }

        presenter.prepareSuccessView(
            new ResponseModel(
                randomComments
            )
        );
    }
}
