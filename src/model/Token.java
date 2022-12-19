package model;

import java.sql.Date;
import java.time.LocalDate;

public class Token {
    String token;
    int userId;
    Date createdDate;
    Date expirationDate;

    public Token(String token, int userId) {
        this.token = token;
        this.userId = userId;
        this.createdDate = Date.valueOf(LocalDate.now());
        this.expirationDate = Date.valueOf(LocalDate.now().plusMonths(1));
    }

    public String getToken() {
        return token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
}