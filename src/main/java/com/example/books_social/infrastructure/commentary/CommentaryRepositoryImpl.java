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
    public Page<CommentaryDto> findCommentaryRandomly(Pageable pageable) {
        final String seed = "4062025";

        // Busca todos os documentos
        List<CommentaryDocument> allDocs = mongoTemplate.findAll(CommentaryDocument.class);

        // Ordena os documentos pela combinação do hash do UUID com a seed
        List<CommentaryDocument> sorted = allDocs.stream()
                .sorted(Comparator.comparingLong(doc -> {
                    String combined = doc.getId().toString() + seed;
                    long hash = 1125899906842597L; // primo grande inicial
                    for (int i = 0; i < combined.length(); i++) {
                        hash = 31 * hash + combined.charAt(i);
                    }
                    return Math.abs(hash);
                }))
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), sorted.size());

        List<CommentaryDto> dtos = sorted.subList(start, end).stream()
                .map(CommentaryDbMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, sorted.size());
    }

}
