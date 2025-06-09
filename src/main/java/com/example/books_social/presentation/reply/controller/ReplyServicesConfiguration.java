package com.example.books_social.presentation.reply.controller;

import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.application.reply.create.CreateReplyServiceImpl;
import com.example.books_social.application.reply.delete.DeleteReplyServiceImpl;
import com.example.books_social.application.reply.find.FindAllRepliesServiceImpl;
import com.example.books_social.application.reply.repository.ReplyRepository;
import com.example.books_social.domain.services.UuidGeneratorService;
import com.example.books_social.infrastructure.account.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReplyServicesConfiguration {
    ReplyRepository replyRepository;
    CommentaryRepository commentaryRepository;
    AccountRepository accountRepository;
    UuidGeneratorService uuidGeneratorService;

    @Bean
    public CreateReplyServiceImpl createReplyService(
        ReplyRepository replyRepository,
        CommentaryRepository commentaryRepository,
        UuidGeneratorService uuidGeneratorService
    ) {
        return new CreateReplyServiceImpl(
          replyRepository,
          commentaryRepository,
          uuidGeneratorService
        );
    }

    @Bean
    public FindAllRepliesServiceImpl findAllRepliesService(
        ReplyRepository replyRepository,
        CommentaryRepository commentaryRepository,
        AccountRepository accountRepository
    ) {
        return new FindAllRepliesServiceImpl(
            replyRepository,
            commentaryRepository,
            accountRepository
        );
    }

    @Bean
    DeleteReplyServiceImpl deleteReplyService(
        ReplyRepository replyRepository
    ) {
        return new DeleteReplyServiceImpl(
            replyRepository
        );
    }
}
