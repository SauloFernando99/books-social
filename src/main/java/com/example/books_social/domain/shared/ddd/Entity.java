package com.example.books_social.domain.shared.ddd;

import java.util.Objects;

public abstract class Entity <T> {

    protected final Identifier<T> id;

    protected Entity (Identifier<T> id) {
        this.id = id;
    }

    public Identifier<T> getId() {
        return id;
    }

    @Override
    public final boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Entity<?> entity = (Entity<?>) other;
        return Objects.equals(id, entity.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

}
