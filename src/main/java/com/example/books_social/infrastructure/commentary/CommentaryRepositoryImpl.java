package com.example.books_social.infrastructure.commentary;

import com.example.books_social.application.commentary.repository.CommentaryDto;
import com.example.books_social.application.commentary.repository.CommentaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CommentaryRepositoryImpl implements CommentaryRepository {

    private final MongoCommentaryRepository innerRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CommentaryRepositoryImpl(
        MongoCommentaryRepository repository,
        MongoTemplate mongoTemplate
    ) {
        this.innerRepository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void create(CommentaryDto dto) {
        CommentaryDocument document = CommentaryDbMapper.toDocument(dto);
        innerRepository.save(document);
    }

    @Override
    public List<CommentaryDto> findAllCommentsByBook(UUID bookId) {
        return innerRepository.findAllCommentsByBookIdOrderByCreatedAtDesc(bookId).stream().map(CommentaryDbMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CommentaryDto findCommentary(UUID commentaryId) {
        return innerRepository.findById(commentaryId).map(CommentaryDbMapper::toDto)
                .orElse(null);
    }

    @Override
    public boolean existsById(UUID commentaryId) {
        return innerRepository.existsById(commentaryId);
    }

    @Override
    public void deleteById(UUID commentaryId) {
        innerRepository.deleteById(commentaryId);
    }

    @Override
    public void saveOrUpdate(CommentaryDto dto) {
        CommentaryDocument document = CommentaryDbMapper.toDocument(dto);
        innerRepository.save(document);
    }

    @Override
    public List<CommentaryDto> findCommentaryRandomly() {
        return innerRepository.findRandomComments()
                .stream()
                .map(CommentaryDbMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllByBookId(UUID bookId) {
        innerRepository.deleteAllByBookId(bookId);
    }
}
