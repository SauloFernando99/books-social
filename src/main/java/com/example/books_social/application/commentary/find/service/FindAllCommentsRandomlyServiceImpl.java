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
import java.util.Optional;

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
        List<CommentaryDto> random = repository.findCommentaryRandomly();

        List<RandomCommentsResponse> randomComments = new ArrayList<>();

        for (CommentaryDto commentaryDto: random){
            Optional<AccountDto> accountDto = accountRepository.findById(commentaryDto.userId());
            String username = accountDto.map(AccountDto::username).orElse("USER NOT FOUND");
            BookDto bookDto = bookRepository.findById(commentaryDto.bookId());
            List<ReplyDto> replies = replyRepository.findAllByCommentaryId(commentaryDto.commentaryId());
            boolean isLiked = commentaryDto.likesList().contains(request.userId());

            RandomCommentsResponse commentary = new RandomCommentsResponse(
                username,
                isLiked,
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
