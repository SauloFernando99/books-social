package com.example.books_social.domain.shared.valueobject;

import com.example.books_social.domain.shared.ddd.Notification;
import com.example.books_social.domain.shared.ddd.ValueObject;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Email extends ValueObject {
    private final String email;

    public Email(String email) {
        this.email = email;

        Notification notification = validate();

        if (!notification.hasNoErrors()) {
            throw new IllegalArgumentException(notification.message());
        }
    }

    public String getEmail() {
        return email;
    }

    @Override
    protected Notification validate() {
        Notification notification = new Notification();

        if (email == null || email.isEmpty()) {
            notification.addError("Email must not be empty.");
        }

        assert email != null;
        if (email.isBlank()) {
            notification.addError("Email must not be blank.");
            return notification;
        }

        if (!isValidEmailFormat(email)) {
            notification.addError("Wrong email format.");
        }

        return notification;
    }

    private boolean isValidEmailFormat(String email) {
        return isValidEmailAddress(email)
                && hasLengthBelowMaximum(email)
                && !hasRepeatedSubdomais(email)
                && !email.contains("..")
                && !email.contains("@.")
                && !email.contains(".@");
    }

    private boolean isValidEmailAddress(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[a-z.]+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    private boolean hasRepeatedSubdomais(String email) {
        String[] parts = email.split("@");
        if (parts.length == 2) {
            String[] subdomains = parts[1].split("\\.");
            Set<String> verifiedSubdomains = new HashSet<>();

            for (String subdomain : subdomains) {
                if (!verifiedSubdomains.add(subdomain)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasLengthBelowMaximum(String email) {
        String[] parts = email.split("@");
        return !(parts[0].length() > 64 || parts[1].length() > 255);
    }

    @Override
    public String toString() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email other)) return false;

        return email.equals(other.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
