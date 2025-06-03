package com.example.books_social.infrastructure.reply;

import com.example.books_social.application.reply.repository.ReplyDto;
import com.example.books_social.application.reply.repository.ReplyRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyRepositoryImpl implements ReplyRepository {
    private final MongoReplyRepository innerRepository;

    public ReplyRepositoryImpl(MongoReplyRepository innerRepository) {
        this.innerRepository = innerRepository;
    }

    @Override
    public void create(ReplyDto dto) {
        ReplyDocument document = ReplyDbMapper.toDocument(dto);
        innerRepository.save(document);
    }
}
