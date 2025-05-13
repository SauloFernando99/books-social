    package com.example.books_social.user;

    import com.fasterxml.jackson.annotation.JsonAutoDetect;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.EqualsAndHashCode;
    import lombok.Getter;
    import lombok.NoArgsConstructor;

    @Table(name = "users")
    @Entity(name = "User")
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(of = "id")
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String email;
        private String password;
        private String userName;
        private String userPicture;

    }
