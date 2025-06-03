package com.example.books_social.infrastructure.reply;

import com.example.books_social.application.reply.repository.ReplyDto;
import com.example.books_social.application.reply.repository.ReplyRepository;
import com.example.books_social.infrastructure.commentary.CommentaryDbMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public List<ReplyDto> findAllByCommentaryId(UUID commentaryId) {
        return innerRepository.findAllByCommentaryId(commentaryId).stream().map(ReplyDbMapper::toDto).collect(Collectors.toList());

    }
}
