package model;

import java.time.LocalDate;

public class Token {
    String token;
    int userId;
    LocalDate createdDate;
    LocalDate expirationDate;

    public Token(String token, int userId) {
        this.token = token;
        this.userId = userId;
        this.createdDate = LocalDate.now();
        this.expirationDate = LocalDate.now().plusMonths(1);
    }

    public String getToken() {
        return token;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}