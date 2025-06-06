package com.example.books_social.infrastructure.book;

import com.example.books_social.application.book.repository.BookDto;
import com.example.books_social.application.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final MongoBookRepository innerRepository;

    @Autowired
    public BookRepositoryImpl(MongoBookRepository repository) {
        this.innerRepository = repository;
    }

    @Override
    public void create(BookDto dto) {
        BookDocument document = BookDbMapper.toDocument(dto);
        innerRepository.save(document);
    }

    @Override
    public List<BookDto> findAllBooksByOwner(UUID ownerId) {
        return innerRepository.findAllByOwnerIdOrderByCreatedAtDesc(ownerId).stream().map(BookDbMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BookDto findById(UUID bookId) {
        return innerRepository.findById(bookId).map(BookDbMapper::toDto)
                .orElse(null);
    }

    @Override
    public void saveOrUpdate(BookDto dto) {
        BookDocument document = BookDbMapper.toDocument(dto);
        innerRepository.save(document);
    }

    @Override
    public boolean existsById(UUID id) {
        return innerRepository.existsById(id);
    }

    @Override
    public void deleteById(UUID bookId) {
        innerRepository.deleteById(bookId);
        return;
    }

    @Override
    public boolean existsByOwnerIdAndTitle(UUID ownerId, String title){
        return innerRepository.existsByOwnerIdAndTitle(ownerId, title);
    }

    @Override
    public BookDto findLongestReadBook(UUID ownerId) {
        return innerRepository.findTopByOwnerIdAndReadingStatusOrderByNumberPagesDesc(ownerId, "FINISHED")
                .map(BookDbMapper::toDto)
                .orElse(null);
    }

    @Override
    public BookDto findShortestReadBook(UUID ownerId) {
        return innerRepository.findTopByOwnerIdAndReadingStatusOrderByNumberPagesAsc(ownerId, "FINISHED")
                .map(BookDbMapper::toDto)
                .orElse(null);
    }

    @Override
    public Integer findTotalRead(UUID ownerId) {
        List<BookDocument> books = innerRepository.findAllByOwnerIdAndReadingStatus(ownerId, "FINISHED");
        return books.size();
    }

    @Override
    public Integer findReadPages(UUID ownerId) {
        List<BookDocument> books = innerRepository.findAllByOwnerIdAndReadingStatus(ownerId, "FINISHED");
        return books.stream().mapToInt(BookDocument::getNumberPages).sum();
    }

    @Override
    public String findMostReadGenre(UUID ownerId) {
        List<BookDocument> books = innerRepository.findAllByOwnerIdAndReadingStatus(ownerId, "FINISHED");

        return books.stream()
                .filter(book -> book.getGenre() != null)
                .collect(Collectors.groupingBy(BookDocument::getGenre, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
