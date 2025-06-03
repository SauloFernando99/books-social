package com.example.books_social.infrastructure.commentary;

import com.example.books_social.application.commentary.repository.CommentaryDto;
import com.example.books_social.application.commentary.repository.CommentaryRepository;
import com.example.books_social.infrastructure.book.BookDbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;

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
    public Page<CommentaryDto> findCommentaryRandomly(Pageable pageable) {
        int pageSize = pageable.getPageSize();

        // Sorteia exatamente a quantidade pedida (ex: 10)
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.sample(pageSize)
        );

        List<CommentaryDocument> sampledDocuments = mongoTemplate
                .aggregate(agg, CommentaryDocument.class, CommentaryDocument.class)
                .getMappedResults();

        List<CommentaryDto> dtos = sampledDocuments.stream()
                .map(CommentaryDbMapper::toDto)
                .collect(Collectors.toList());

        // Como não temos o total de elementos, usamos o próprio tamanho como total
        return new PageImpl<>(dtos, pageable, dtos.size());
    }

}
