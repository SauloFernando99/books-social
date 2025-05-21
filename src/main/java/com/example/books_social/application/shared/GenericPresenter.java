package com.example.books_social.application.shared;

public interface GenericPresenter<T> {
    void prepareSuccessView(T response);
    void prepareFailView(Throwable throwable);
    boolean isDone();
}
