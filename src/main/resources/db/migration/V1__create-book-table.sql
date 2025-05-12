CREATE TABLE book (
    id BIGINT not null auto_increment,
    title VARCHAR(255) not null,
    gender VARCHAR(100) not null,
    date DATE not null,
    finished DATE not null,
    review TEXT not null,
    favorite_character VARCHAR(100) not null,
    assessment VARCHAR(50) not null,
    cover VARCHAR(255) not null,
    number_pages INT not null,
    reading_status VARCHAR(50) not null,
    book_type VARCHAR(50) not null,
    PRIMARY KEY(id)
);
